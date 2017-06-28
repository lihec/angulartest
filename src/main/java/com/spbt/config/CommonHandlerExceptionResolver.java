package com.spbt.config;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.spbt.exception.ServiceException;
import com.spbt.util.RequestUtil;
import com.spbt.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一处理异常
 */
public class CommonHandlerExceptionResolver extends SimpleMappingExceptionResolver {
    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(CommonHandlerExceptionResolver.class);

    /**
     * 统一处理异常
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        boolean isAjax = RequestUtil.isAjaxRequest(request);
        String errorMsg = BaseController.UNKOWNEXCEPTION;
        if (ex instanceof ServiceException) {
            errorMsg = ((ServiceException) ex).getErrorMsg();
        }
        logger.error(errorMsg, ex);
        if (isAjax) {
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put(BaseController.RESULT, "fail");
            jsonMap.put("rescode", BaseController.FAIL_CODE);
            jsonMap.put(BaseController.MSG, errorMsg);
            jsonMap.put(BaseController.DATA, Collections.EMPTY_MAP);
            return new ModelAndView(new FastJsonJsonView(), jsonMap);
        } else {
            String viewName = determineViewName(ex, request);
            if (viewName != null) {
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                ModelAndView modelAndView = getModelAndView(viewName, ex, request);
                modelAndView.addObject(BaseController.ERRORKEY, errorMsg);
                return modelAndView;
            } else {
                return null;
            }
        }

    }

}
