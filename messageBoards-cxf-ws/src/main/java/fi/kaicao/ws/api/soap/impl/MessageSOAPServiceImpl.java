package fi.kaicao.ws.api.soap.impl;

import fi.kaicao.core.service.MessageManager;
import fi.kaicao.ws.api.soap.MessageSOAPService;
import fi.kaicao.ws.dto.MessageDTO;
import fi.kaicao.ws.dto.MessageListDTO;
import fi.kaicao.ws.fault.ConnectionFault;
import fi.kaicao.ws.fault.GenericWebFault;
import fi.kaicao.ws.fault.ValidationFault;
import fi.kaicao.ws.dto.converter.MessageDTOConverter;
import org.apache.cxf.annotations.SchemaValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("messageSOAPService")
@WebService(
        serviceName = "messageSOAPService",
        endpointInterface = "fi.kaicao.ws.api.soap.MessageSOAPService",
        targetNamespace = "http://soap.api.ws.kaicao.fi"
)
@SchemaValidation
public class MessageSOAPServiceImpl implements MessageSOAPService {

    private final static Logger LOG = LoggerFactory.getLogger(MessageSOAPServiceImpl.class);

    @Autowired
    private MessageManager messageManager;


    public String createMessage(
            @WebParam(name = "messageDTO", mode = WebParam.Mode.IN)
            MessageDTO messageDTO
    ) throws ValidationFault, GenericWebFault, ConnectionFault {
        LOG.debug("Create message: \n" + messageDTO.getMessage());
        try {
            LOG.info("Request received");

            messageManager.saveMessage(messageDTO.getMessage());

            LOG.info("Message created");
            return "Success";
        } catch (RuntimeException e) {
            LOG.error("Unable to create message", e);
            throw e;
        }
    }


    public MessageListDTO listMessages() throws ValidationFault, GenericWebFault, ConnectionFault {
        // TODO implement return xml with title, sender only
        return MessageDTOConverter.convertMessageListDTO(messageManager.findAllMessages());
    }




}
