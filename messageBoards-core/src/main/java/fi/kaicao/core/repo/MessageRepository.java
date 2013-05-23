package fi.kaicao.core.repo;

import fi.kaicao.core.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/20/13
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}
