package com.hep.memberapi.common;

import java.util.Map;

/**
 * @Title
 * @Description: 用于封装调用Member系统接口返回的结果信息
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/25 14:12
 */
public class MemberApiResultEntity {
    public static final Integer STATUS_FAILED = 0;
    public static final Integer STATUS_SUCCESS = 1;
    private Integer status;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess(){
        return STATUS_SUCCESS.equals(getStatus());
    }
}
