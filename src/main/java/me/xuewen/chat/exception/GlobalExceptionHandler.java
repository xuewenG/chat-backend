package me.xuewen.chat.exception;

import me.xuewen.chat.entity.Response;
import me.xuewen.chat.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static String getLog(Exception e, HttpServletRequest httpServletRequest) {
        return "log";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Response handleException(Exception e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(e, httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("请求失败");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("不支持的 HTTP 方法"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("不支持的 HTTP 方法");
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    Response handleDatabaseException(DataAccessException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("数据库操作错误"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("数据库操作错误");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("缺少参数"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        String userMessage = e.getBindingResult().getAllErrors()
                .stream()
                .map(cv -> cv == null ? "null" : cv.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResultUtil.error(userMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    Response handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("缺少参数"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        String userMessage = e.getConstraintViolations()
                .stream()
                .map(cv -> cv == null ? "null" : cv.getMessage())
                .collect(Collectors.joining(", "));
        return ResultUtil.error(userMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(new BaseException("请求体不可读"), httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("请求体不可读");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    Response handleAccessDeniedException(AccessDeniedException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(GlobalExceptionHandler.getLog(e, httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error("权限不足");
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    Response handleBusinessException(BaseException e, HttpServletRequest httpServletRequest) {
        LOGGER.error(getLog(e, httpServletRequest));
        LOGGER.error(e.getMessage(), e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }
}
