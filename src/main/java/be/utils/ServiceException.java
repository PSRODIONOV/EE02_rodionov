package be.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends Exception {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceException.class);

    public static final String ERROR_USER_BALANCE = "Insufficient funds in the account";
    public static final String ERROR_USER_REGISTRATION = "User with this login already exists";
    public static final String ERROR_USER_LOGIN = "Invalid login or password";
    public static final String ERROR_FLOWERSTOCKSERVICE = "Invalid value for 'quantity'";

    public ServiceException(String message){
        super(message);
        LOG.error(message);
    }
}
