package fi.kaicao.rest.dto;

import fi.kaicao.core.domain.Message;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "message")
@XmlType(propOrder = {
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
}
