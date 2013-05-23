package fi.kaicao.webapp.client;

import fi.kaicao.ws.dto.MessageDTO;
import fi.kaicao.ws.dto.MessageListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/23/13
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@PropertySource("classpath:webservices.properties")
public class RestClient {

    private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    public String createMessage(MessageDTO message) {
       /*
        LOG.debug("RESTClient POST: createMessage(" + message.toString() + ")");
        String restBaseUrl = env.getProperty("REST_BASE_URL");
        String createMessageMethod = env.getProperty("CREATE_MESSAGE_METHOD");

        StringBuffer restCall = new StringBuffer();
        restCall.append(restBaseUrl).append(createMessageMethod);

        return restTemplate.postForEntity(
                restCall.toString(),
                message,
                String.class
        ).getBody();
        */
        return null;
    }

    public MessageListDTO listMessages(String version) {

        LOG.debug("RESTClient GET: listMessages(version=" + version + ")");
        String restBaseUrl = env.getProperty("REST_BASE_URL");
        String listMessagesMethod = env.getProperty("LIST_MESSAGES_METHOD");

        StringBuffer restCall = new StringBuffer();
        restCall.append(restBaseUrl).append(listMessagesMethod)
                .append("/").append(version);

        return restTemplate.getForEntity(restCall.toString(), MessageListDTO.class).getBody();

    }

    public MessageListDTO listMessages(String version, String format) {

        LOG.debug("RESTClient GET: listMessages(version=" + version + ", format=" + format + ")");
        String restBaseUrl = env.getProperty("REST_BASE_URL");
        String listMessagesMethod = env.getProperty("LIST_MESSAGES_METHOD");

        StringBuffer restCall = new StringBuffer();
        restCall.append(restBaseUrl).append(listMessagesMethod)
                .append("/").append(version)
                .append("/").append(format);

        return restTemplate.getForEntity(restCall.toString(), MessageListDTO.class).getBody();

    }
}
