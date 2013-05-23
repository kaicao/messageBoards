package fi.kaicao.ws.dto;

import fi.kaicao.core.domain.Message;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonFilter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
@JsonFilter("messageFilter")
@JsonPropertyOrder(value={"title", "sender", "content", "url"})
@XmlRootElement(name = "message")
@XmlType(name = "messageDTO",
        propOrder = {
        "title",
        "sender",
        "content",
        "url"
})
@XmlAccessorType(FIELD)
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private String title = "";

    @XmlElement
    private String sender = "";

    @XmlElement
    private String content = "";

    @XmlElement
    private String url = "";

    public MessageDTO() {
    }

    public MessageDTO(Message message) {
        this.title = message.getTitle();
        this.sender = message.getSender();
        this.content = message.getContent();
        this.url = message.getUrl();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public Message getMessage() {
        Message message = new Message();
        message.setTitle(this.getTitle());
        message.setSender(this.getSender());
        message.setContent(this.getContent());
        message.setUrl(this.getUrl());
        return message;
    }
}
