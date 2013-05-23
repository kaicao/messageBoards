package fi.kaicao.ws.fault;

import fi.kaicao.ws.util.Constants;

import javax.xml.bind.UnmarshalException;
import javax.xml.ws.WebFault;

/**
 * Created with IntelliJ IDEA.
 * User: kai
 * Date: 5/21/13
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
@WebFault(name = "SchemaValidationFault")
public class SchemaValidationFault extends RuntimeException implements FaultDetails {

    private static final long serialVersionUID = 1L;

    public SchemaValidationFault(UnmarshalException exception) {
        super(Constants.MESSAGE_FAULT_SCHEMA_VALIDATION + ". " + exception.getCause().getMessage(), exception);
    }

    @Override
    public String getCode() {
        return Constants.CODE_FAULT_SCHEMA_VALIDATION;
    }
}