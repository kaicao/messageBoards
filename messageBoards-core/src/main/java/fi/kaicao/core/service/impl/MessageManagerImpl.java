package fi.kaicao.core.service.impl;

import fi.kaicao.core.domain.Message;
import fi.kaicao.core.repo.MessageRepository;
import fi.kaicao.core.service.MessageManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/20/13
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value="messageManager")
@Transactional
public class MessageManagerImpl implements MessageManager {

    @Inject
    private MessageRepository messageRepository;

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }
}
