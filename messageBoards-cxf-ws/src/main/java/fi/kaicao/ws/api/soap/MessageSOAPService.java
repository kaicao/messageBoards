package fi.kaicao.ws.api.soap;

import fi.kaicao.ws.dto.MessageDTO;
import fi.kaicao.ws.dto.MessageListDTO;
import fi.kaicao.ws.fault.ConnectionFault;
import fi.kaicao.ws.fault.GenericWebFault;
import fi.kaicao.ws.fault.ValidationFault;
import org.apache.cxf.annotations.SchemaValidation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(name = "messageSOAPService",
            targetNamespace = "http://soap.api.ws.kaicao.fi"
)
@SchemaValidation
public interface MessageSOAPService {

    /**
     * CreateMessage receives a message in the request and persists it in the
     * application. The message should have 4 fields: title, content, sender and url.
     * @param messageDTO
     * @return String
     * @throws fi.kaicao.ws.fault.ValidationFault
     *              This fault indicates that an error has occurred while
     *              performing a validate operation.
     * @throws fi.kaicao.ws.fault.GenericWebFault
     *              Indicates an unknown fault that occurred while trying to
     *              invoke a service.
     * @throws fi.kaicao.ws.fault.ConnectionFault
     *              This fault indicates that an error has occurred while trying
     *              to connect to a service.
     */
    @WebMethod(operationName = "createMessage")
    public String createMessage(
            @WebParam(name = "messageDTO", mode = WebParam.Mode.IN)
            MessageDTO messageDTO
    ) throws ValidationFault, GenericWebFault, ConnectionFault;

    /**
     * Not implemented
     * @return
     * @throws ValidationFault
     * @throws GenericWebFault
     * @throws ConnectionFault
     */
    @WebMethod(operationName = "listMessages")
    public MessageListDTO listMessages() throws ValidationFault, GenericWebFault, ConnectionFault;

          /*
    Object listMessages(
            String version, String format
    ) throws ValidationFault, GenericWebFault, ConnectionFault;
            */
}
