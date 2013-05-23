package fi.kaicao.rest.api;

import fi.kaicao.core.domain.Message;
import fi.kaicao.core.service.MessageManager;
import fi.kaicao.rest.dto.MessageDTO;
import fi.kaicao.rest.dto.MessageListDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 12:23 AM
 * Rest Service API, use Spring MVC to generate RESTful, support json and xml view
 */
@RequestMapping("/messageRestService/**")
@Controller
public class MessageRestService {

    private final static Logger LOG = LoggerFactory.getLogger(MessageRestService.class);

    @Autowired
    private MessageManager messageManager;

    @RequestMapping(value = "/message/createMessage", method = RequestMethod.POST)
    public @ResponseBody
    String createMessage(Message message) {
        LOG.debug("Save message: \n" + message);
        ModelAndView mav = new ModelAndView();
        if (message != null) {
            messageManager.saveMessage(message);

            return "Success";
        }
        return "Fail";
    }


    /**
     * Messages returned by the second version should return all 4 fields. The
     * second version also takes a parameter which defines the format in  which
     * the response is returned (supported formats could be e.g. JSON and XML).
     * @return
     */
    @RequestMapping(value = "/message/listMessages/{format}", method = RequestMethod.GET)
    public ModelAndView listMessages(@PathVariable String format) {
        if (StringUtils.equalsIgnoreCase(format, "json")) {
            LOG.debug("List messages in JSON format");
            return new ModelAndView("jsonView", "messages", convertJsonDTO(messageManager.findAllMessages()));
        } else if (StringUtils.equalsIgnoreCase(format, "xml")) {
            LOG.debug("List messages in XML format");
            return new ModelAndView("jaxbView", "messages", convertXMLDTO(messageManager.findAllMessages()));
        }
        return null; //TODO return some info if format parameter is wrong, and exception handling based on format
    }

    private List<MessageDTO> convertJsonDTO(List<Message> messages) {
        List<MessageDTO> messageDTOs = new ArrayList<MessageDTO>();
        if (CollectionUtils.isNotEmpty(messages)) {
            for (Message message : messages) {
                messageDTOs.add(new MessageDTO(message));
            }
        }
        return messageDTOs;
    }

    private MessageListDTO convertXMLDTO(List<Message> messages) {
        List<MessageDTO> messageDTOs = new ArrayList<MessageDTO>();
        if (CollectionUtils.isNotEmpty(messages)) {
            for (Message message : messages) {
                messageDTOs.add(new MessageDTO(message));
            }
        }
        return new MessageListDTO(messageDTOs);
    }
}
