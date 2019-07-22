package be.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends Exception {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceException.class);

    public static final String ERROR_USER_BALANCE = "Insufficient funds in the account";

    public ServiceException(String message){
        super(message);
        LOG.error(message);
    }
}
