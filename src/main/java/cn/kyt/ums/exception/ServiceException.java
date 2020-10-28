package cn.kyt.ums.exception;

import org.springframework.core.NestedRuntimeException;

public class ServiceException extends NestedRuntimeException {

    /**
     *
     */
    private String message;

    private static final long serialVersionUID = -871158005964778467L;

    public ServiceException(String msg, Throwable ex) {
        super(msg, ex);
        this.message = msg;
    }

    public ServiceException(String msg) {
        super(msg);
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }




}
