package fi.kaicao.webapp.controller;

import fi.kaicao.webapp.client.RestClient;
import fi.kaicao.ws.api.soap.MessageSOAPService;
import fi.kaicao.ws.api.soap.impl.MessageSOAPServiceImpl;
import fi.kaicao.ws.dto.MessageDTO;
import fi.kaicao.ws.dto.MessageListDTO;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView listMessages(
            HttpServletRequest request
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            MessageListDTO messageList =
                    restClient.listMessages("1", "XML");
            mav.addObject("messageList", messageList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.setViewName("/messageBoard/list");
        return mav;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "createMessage"
    )
    public
    @ResponseBody
    MessageDTO createMessage(
            @RequestBody MessageDTO message
    ) {
        try {
            MessageSOAPService messageSOAPService = new MessageSOAPServiceImpl();
            messageSOAPService.createMessage(message);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return message;
    }
}
