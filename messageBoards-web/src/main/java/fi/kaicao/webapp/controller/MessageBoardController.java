package fi.kaicao.webapp.controller;

import fi.kaicao.webapp.client.RestClient;
import fi.kaicao.ws.api.soap.MessageSOAPService;
import fi.kaicao.ws.api.soap.impl.MessageSOAPServiceImpl;
import fi.kaicao.ws.dto.MessageDTO;
import fi.kaicao.ws.dto.MessageListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/23/13
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/messageBoard")
public class MessageBoardController extends BaseFormController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageBoardController.class);

    @Autowired
    private RestClient restClient;

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView listMessages() {
        ModelAndView mav = new ModelAndView();

        MessageSOAPService messageSOAPService = new MessageSOAPServiceImpl();
        try {
            MessageListDTO messageList =
                    //messageSOAPService.listMessages();
                    restClient.listMessages("1", "XML");
            mav.addObject(messageList);

            for (MessageDTO message : messageList.getMessageList()) {
                System.out.println(message.toString());
            }


            /*
            MessageDTO message = new MessageDTO();
            message.setTitle("Test from web 3");
            message.setSender("MacBook3");
            message.setContent("Test content from web module3");
            message.setUrl("http://localhost:9000/äöå");
            messageSOAPService.createMessage(message);
            */
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.setViewName("/messageBoard/list");
        return mav;
    }
}
