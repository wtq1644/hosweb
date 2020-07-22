package com.hliedu.core.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回的JSON对象Bean
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Getter
@Setter
public class ResultBean {

    public final static String SUCCESS = "success";
    public final static String ERROR = "error";

    //-1，错误代码
    public static String ERRORCODE = "-1";

    public ResultBean(){
        setSysRecode(SUCCESS);
        setMsg("操作成功");
    }

    public ResultBean(Object dataObj){
        setSysRecode(SUCCESS);
        setMsg("操作成功");
        setDataObj(dataObj);
    }

    public ResultBean(String errorCode , String msg){
        setSysRecode(ERROR);
        setErrorCode(errorCode);
        setMsg(msg);
    }

    public ResultBean(String errorCode, String msg, Object dataObj){
        setSysRecode(ERROR);
        setErrorCode(errorCode);
        setMsg(msg);
        setDataObj(dataObj);
    }

    /**
     * 判断当前对象是否是成功对象
     * @return
     */
    public boolean isSuccess(){
        return SUCCESS.equals(sysRecode);
    }

    //错误码，业务
    private String errorCode;

    //返回状态，系统：只有error和success两种状态
    private String sysRecode;

    //消息提示
    private String msg;

    //返回数据
    private Object dataObj;
}