package net.core.exception;

/**
 * 系统异常
 *
 * @author 汤国栋
 * @date 2016年11月23日 下午1:42:10
 */
public class SystemException extends RuntimeException {

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
