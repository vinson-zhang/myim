package com.zt.project.im.handler;

import com.zt.project.im.enumpack.ErrorCodeEnum;
import com.zt.project.im.util.MyExceptionUtil;
import com.zt.project.im.util.ResponseInfo;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * ZhangTao
 * 2018/5/13 17:43
 * Description: 全局异常捕获
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获其它异常
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseInfo<String> handlerOtherException(Exception e){
        ResponseInfo<String> responseInfo = new ResponseInfo<String>();
        responseInfo.setCode(ErrorCodeEnum.OTHER_ERROR.getCode());
        responseInfo.setDesc(ErrorCodeEnum.OTHER_ERROR.getDesc());
        responseInfo.setResult(MyExceptionUtil.getStackTraceStr(e));
        return responseInfo;
    }

    /**
     * 捕获未知账户异常
     * @param e
     * @return
     */
    @ExceptionHandler({UnknownAccountException.class})
    public ResponseInfo handlerUnknownAccountException(Exception e){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(ErrorCodeEnum.UNKNOWN_ACCOUNT.getCode());
        responseInfo.setDesc(ErrorCodeEnum.UNKNOWN_ACCOUNT.getDesc());
        return responseInfo;
    }

    /**
     * 捕获未知账户异常
     * @param e
     * @return
     */
    @ExceptionHandler({IncorrectCredentialsException.class})
    public ResponseInfo handlerIncorrectCredentialsException(Exception e){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(ErrorCodeEnum.PASSWORD_WRONG.getCode());
        responseInfo.setDesc(ErrorCodeEnum.PASSWORD_WRONG.getDesc());
        return responseInfo;
    }


}
