package fi.kaicao.rest.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 9:08 PM
 * Wrapper for Message list, otherwise when there is no message xml can't be generated
 */
@XmlRootElement(name="messages")
public class MessageListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // Must define XmlElement in the get method, otherwise will throw exception duplicate messageList defined
    private List<MessageDTO> messageList = new ArrayList<MessageDTO>();

    public MessageListDTO() {
    }

    public MessageListDTO(List<MessageDTO> messages) {
        this.messageList = messages;
    }

    @XmlElement(name = "message")
    public List<MessageDTO> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageDTO> messageList) {
        this.messageList = messageList;
    }
}
