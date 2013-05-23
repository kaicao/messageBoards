package fi.kaicao.ws.dto.converter;

import fi.kaicao.core.domain.Message;
import fi.kaicao.ws.dto.MessageDTO;
import fi.kaicao.ws.dto.MessageListDTO;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/23/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class MessageDTOConverter {

    public static List<MessageDTO> convertMessagesToMessageDTOs(List<Message> messages) {
        List<MessageDTO> messageDTOs = new ArrayList<MessageDTO>();
        if (CollectionUtils.isNotEmpty(messages)) {
            for (Message message : messages) {
                messageDTOs.add(new MessageDTO(message));
            }
        }
        return messageDTOs;
    }

    public static MessageListDTO convertMessageListDTO(List<Message> messages) {
        List<MessageDTO> messageDTOs = convertMessagesToMessageDTOs(messages);
        return new MessageListDTO(messageDTOs);
    }
}
