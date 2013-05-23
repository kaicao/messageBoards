package fi.kaicao.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/20/13
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createTime;

    @Column(name = "TITLE", length = 100, nullable = true)
    private String title;

    @Column(name = "SENDER", length = 100, nullable = true)
    private String sender;

    @Column(name = "CONTENT", length = 300, nullable = true)
    private String content;

    @Column(name = "URL", length = 100, nullable = true)
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @PrePersist
    private void prePersist() {
        this.createTime = Calendar.getInstance().getTime();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                (title != null ? "title='" + title  + '\'': "") +
                (sender != null ? ", sender='" + sender + '\'' : "" ) +
                (content != null ? ", content='" + content + '\'' : "") +
                (url != null ? ", url='" + url + '\'' : "") +
                '}';
    }
}
