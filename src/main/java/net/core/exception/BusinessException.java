package net.core.exception;

/**
 * 业务异常
 *
 * @Author 汤国栋
 * @Date 2017-05-10 15:55
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
