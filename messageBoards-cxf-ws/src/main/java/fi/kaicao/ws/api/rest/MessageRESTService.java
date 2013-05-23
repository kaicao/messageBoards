package fi.kaicao.ws.api.rest;

import fi.kaicao.core.service.MessageManager;
import fi.kaicao.ws.dto.MessageDTO;
import fi.kaicao.ws.fault.ConnectionFault;
import fi.kaicao.ws.fault.GenericWebFault;
import fi.kaicao.ws.fault.ValidationFault;
import fi.kaicao.ws.dto.converter.MessageDTOConverter;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/22/13
 * Time: 9:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("messageRESTService")
@Path("/messageBoard")
public class MessageRESTService {

    private final static Logger LOG = LoggerFactory.getLogger(MessageRESTService.class);

    @Autowired
    private MessageManager messageManager;

    /**
     * CreateMessage receives a message in the request and persists it in the
     * application. The message should have 4 fields: title, content, sender and url.
     * @param message
     * @return String
     * @throws fi.kaicao.ws.fault.ValidationFault
     *              This fault indicates that an error has occurred while
     *              performing a validate operation.
     * @throws fi.kaicao.ws.fault.GenericWebFault
     *              Indicates an unknown fault that occurred while trying to
     *              invoke a service.
     * @throws fi.kaicao.ws.fault.ConnectionFault
     *              This fault indicates that an error has occurred while trying
     *              to connect to a service.
     */
    @POST
    @Path("/createMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createMessage(
            MessageDTO message
    ) throws ValidationFault, GenericWebFault, ConnectionFault {
        LOG.debug("Create message: \n" + message.getMessage());
        try {
            LOG.info("Request received");

            messageManager.saveMessage(message.getMessage());

            LOG.info("Message created");
            return "Success";
        } catch (RuntimeException e) {
            LOG.error("Unable to create message", e);
            throw e;
        }
    }

    @GET
    @Path("/listMessages/{version}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMessages(
            @PathParam("version")
            String version
    ) throws ValidationFault, GenericWebFault, ConnectionFault {
        //Contain only title, sender, content

        ObjectMapper mapper = new ObjectMapper();
        // first, construct filter provider to exclude all properties but 'title', 'sender', 'content' bind it as 'messageFilter'
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("messageFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("title", "sender", "content")
                );
        try {
            // and then serialize using that filter provider:
            String json = mapper.filteredWriter(filters).writeValueAsString(
                    MessageDTOConverter.convertMessageListDTO(messageManager.findAllMessages())
            );
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new GenericWebFault("Not able to convert Messages to JSON", e);
        }

    }

    /**
     * Messages returned by the second version should return all 4 fields (title, sender, content, url).
     * The second version also takes a parameter which defines the format in  which
     * the response is returned (supported formats could be e.g. JSON and XML).
     * @param version
     * @param format JSON or XML
     * @return Response contains all the messages with specified type
     * @throws fi.kaicao.ws.fault.ValidationFault
     *              This fault indicates that an error has occurred while
     *              performing a validate operation.
     * @throws fi.kaicao.ws.fault.GenericWebFault
     *              Indicates an unknown fault that occurred while trying to
     *              invoke a service.
     * @throws fi.kaicao.ws.fault.ConnectionFault
     *              This fault indicates that an error has occurred while trying
     *              to connect to a service.
     */
    @GET
    @Path("/listMessages/{version}/{format}")
    public Response listMessages(
            @PathParam("version") String version,
            @PathParam("format") String format
    ) throws ValidationFault, GenericWebFault, ConnectionFault {
        if (StringUtils.equalsIgnoreCase(format, "JSON")) {
            LOG.debug("List messages in JSON format");
            ObjectMapper mapper = new ObjectMapper();
            // first, construct filter provider to exclude all properties but 'title', 'sender', 'content', 'url' bind it as 'messageFilter'
            FilterProvider filters = new SimpleFilterProvider()
                    .addFilter("messageFilter",
                            SimpleBeanPropertyFilter.filterOutAllExcept("title", "sender", "content", "url")
                    );
            try {
                // and then serialize using that filter provider:
                String json = mapper.filteredWriter(filters).writeValueAsString(
                        MessageDTOConverter.convertMessageListDTO(messageManager.findAllMessages())
                );
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            } catch (Exception e) {
                throw new GenericWebFault("Not able to convert Messages to JSON", e);
            }

        } else if (StringUtils.equalsIgnoreCase(format, "XML")) {
            LOG.debug("List messages in XML format");
            return Response.ok(
                    MessageDTOConverter.convertMessageListDTO(messageManager.findAllMessages()))
                        .type(MediaType.APPLICATION_XML_TYPE)
                    .build();
        }
        return null;
    }

}
