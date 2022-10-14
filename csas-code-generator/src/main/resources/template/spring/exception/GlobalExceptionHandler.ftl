package ${basePackage}.exception;

import ${basePackage}.utils.RequestUtil;
import ${basePackage}.utils.ResponseMsgUtil;
import ${basePackage}.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
* @author ${author}
* @description 统一异常处理
* @date ${date}
*/
@Slf4j
@ControllerAdvice
@RestController
@RequestMapping
public class GlobalExceptionHandler extends AbstractErrorController {

    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Value("${serverErrorPathErrorPathError}")
    private static String errorPath = "/error";




    /**
    * sql异常
    *
    * @param req
    * @param rsp
    * @param ex
    * @return
    * @throws Exception
    */
    @ExceptionHandler(SQLException.class)
    public Result<String> sqlException(HttpServletRequest req, HttpServletResponse rsp, Exception ex) {
        log.error("!!! request uri:{} from {} server exception:{}", req.getRequestURI(), RequestUtil.getIpAddress(req), ex == null ? null : ex);
        return ResponseMsgUtil.builderResponse("1002", ex.getMessage(), null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> methodNotAllowed(HttpServletRequest req, HttpServletResponse rsp, HttpRequestMethodNotSupportedException ex) {
        log.error("!!! request uri:{} from {} server exception:{}", req.getRequestURI(), RequestUtil.getIpAddress(req), ex == null ? null : ex);
        return ResponseMsgUtil.builderResponse("1002", ex.getMessage(), null);
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> missRequestParamError(HttpServletRequest req, MissingServletRequestParameterException ex) throws Exception {
        log.error("!!! request uri:{} from {} server exception:{}", req.getRequestURI(), RequestUtil.getIpAddress(req), ex == null ? null : ex);
        return ResponseMsgUtil.builderResponse("1002", ex.getMessage(), null);
    }


    /**
    * 500错误.
    *
    * @param req
    * @param rsp
    * @param ex
    * @return
    * @throws Exception
    */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> serverError(HttpServletRequest req, HttpServletResponse rsp, Exception ex) throws Exception {
        log.error("!!! request uri:{} from {} server exception:{}", req.getRequestURI(), RequestUtil.getIpAddress(req), ex == null ? null : ex);
        return ResponseMsgUtil.exception();
    }


    /**
    * 404的拦截.
    *
    * @param request
    * @param response
    * @param ex
    * @return
    * @throws Exception
    */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<String> notFound(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
        log.error("!!! request uri:{} from {} not found exception:{}", request.getRequestURI(), RequestUtil.getIpAddress(request), ex);
        return ResponseMsgUtil.builderResponse("1002", "请求的资源不存在!", null);
    }


    /**
    * 重写/error请求
    *
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "${serverErrorPathErrorPathError}")
    public Result<String> handleErrors(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NOT_FOUND) {
            return notFound(request, response, null);
        }
        return ResponseMsgUtil.exception();
    }


    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
