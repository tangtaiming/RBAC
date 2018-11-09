package com.system.util.base;

import com.rbac.application.action.vo.ResponseVo;

/**
 * @auther ttm
 * @date 2018/8/19 0019
 **/
public class ResultUtils {

    /**
     * 成功
     */
    private static final Integer CODE_200 = 200;

    /**
     * 失败
     */
    private static final Integer CODE_400 = 400;

    /**
     * 异常
     */
    private static final Integer CODE_500 = 500;

    /**
     * 成功消息
     */
    private static final String MSG_200 = "请求成功";

    /**
     * 失败消息
     */
    private static final String MSG_400 = "失败";

    /**
     * 异常消息
     */
    private static final String MSG_500 = "系统异常";

    /**
     * 请求成功
     * @return
     */
    public static String success() {
        return success(null);
    }

    /**
     * 请求成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> String success(T data) {
        return JsonUtils.toJson(new ResponseVo(CODE_200, MSG_200, data));
    }

    /**
     * 请求失败
     * @return
     */
    public static String fail() {
        return fail(MSG_400);
    }

    /**
     * 请求失败
     * @param msg
     * @return
     */
    public static String fail(String msg) {
        return JsonUtils.toJson(new ResponseVo(CODE_400, msg, null));
    }

}
