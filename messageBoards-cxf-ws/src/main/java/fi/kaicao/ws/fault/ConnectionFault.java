package fi.kaicao.ws.fault;

import fi.kaicao.ws.util.Constants;

import javax.xml.ws.WebFault;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 11:24 PM
 * To change this template use File | Settings | File Templates.
 */
@WebFault(name = "ConnectionFault")
public class ConnectionFault extends RuntimeException implements FaultDetails {

    private static final long serialVersionUID = 1L;


    public ConnectionFault(String message) {
        super(message);
    }
    public ConnectionFault(String message, Throwable cause) {
        super(message);
    }

    @Override
    public String getCode() {

        return Constants.CODE_FAULT_DATABASE_CONNECTION;
    }
}

