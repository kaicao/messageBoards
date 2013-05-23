package fi.kaicao.ws.fault;

import fi.kaicao.ws.util.Constants;

import javax.xml.ws.WebFault;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
@WebFault(name = "ValidationFault")
public class ValidationFault extends RuntimeException implements FaultDetails {

    private static final long serialVersionUID = 1L;

    public ValidationFault(String message) {
        super(message);
    }

    public ValidationFault(String message, Throwable cause) {
        super(message);
    }

    @Override
    public String getCode() {

        return Constants.CODE_FAULT_VALIDATION;
    }

}
