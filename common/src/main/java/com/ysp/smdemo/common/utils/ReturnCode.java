/**
 *
 */
package com.ysp.smdemo.common.utils;

/**
 * 请求响应状态码
 */
public enum ReturnCode {

    SUCCESS(200, "操作正确"),
    /**
     *
     */
    DATA_NOT_FOUND(520, "数据不存在"),

    /**
     *
     */
    FAIL(574, "内部异常"),

    PARAMER_NOT_INVALID(501, "参数不正确"),

    TYPE_MIS_MATCH(502, "数据类型不匹配"),

    USER_NOT_FOUND(503, "用户不存在"), LOGIN_FAIL(504, "登录失败,重新登录");

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
