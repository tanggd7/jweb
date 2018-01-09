package net.core.exception;

import net.core.entity.ExceptionEntity;
import net.core.utils.JsonUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 异常统一处理
 *
 * @Author 汤国栋
 * @Date 2017-08-07 11:37
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * 对异常统一处理
     *
     * @param exception 异常对象
     * @param response  响应对象
     * @return
     */
    @ExceptionHandler
    public void processException(UnauthorizedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error(exception.getMessage(), exception);
        int code;
        if (exception instanceof UnauthorizedException) {
            code = 401;
        } else {
            code = 400;
        }
        if (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            ExceptionEntity exceptionEntity = new ExceptionEntity();
            exceptionEntity.setCode(code);
            exceptionEntity.setMessage(exception.getMessage());
            exceptionEntity.setCause(exception.getCause() == null ? exception.toString() : exception.getCause().toString());
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.write(JsonUtil.ObjectToJson(exceptionEntity));
                response.flushBuffer();
            } catch (IOException e) {
                logger.error(exception.getMessage(), e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } else {
            response.sendRedirect(code + "Page");
        }
    }

}
