package fi.kaicao.core.service;

import fi.kaicao.core.domain.Message;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/20/13
 * Time: 9:25 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MessageManager {

    public Message saveMessage(Message message);

    public List<Message> findAllMessages();
}
