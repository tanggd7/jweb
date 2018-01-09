package net.core.entity;

/**
 * 异常返回实体
 *
 * @Author 汤国栋
 * @Date 2017-08-07 16:02
 */
public class ExceptionEntity {

    private int code;
    private String message;
    private String cause;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
