package fi.kaicao.ws.fault;

import fi.kaicao.ws.util.Constants;

import javax.xml.ws.WebFault;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 11:12 PM
 * To change this template use File | Settings | File Templates.
 */
@WebFault(name="GenericWebFault")
public class GenericWebFault extends RuntimeException implements FaultDetails {

    private static final long serialVersionUID = 1L;

    public GenericWebFault() {
        super();
    }

    public GenericWebFault(String msg) {
        super(Constants.MESSAGE_FAULT_GENERIC_WEB + ". " + msg);
    }

    public GenericWebFault(String msg, Throwable throwable) {
        super(Constants.MESSAGE_FAULT_GENERIC_WEB + ". " + msg, throwable);
    }

    @Override
    public String getCode() {
        return Constants.CODE_FAULT_GENERIC_WEB;
    }

}
