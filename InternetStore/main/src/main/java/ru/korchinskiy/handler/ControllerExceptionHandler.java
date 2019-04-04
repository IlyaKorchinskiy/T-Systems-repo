package ru.korchinskiy.handler;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static Logger logger = Logger.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({SQLException.class, HibernateException.class})
    public String databaseError(Exception ex) {
        logger.error("Exception: ", ex);
        return "databaseError";
    }

    @ExceptionHandler(Exception.class)
    public String unknownError(Exception ex) {
        logger.error("Exception: ", ex);
        return "unknownError";
    }
}
