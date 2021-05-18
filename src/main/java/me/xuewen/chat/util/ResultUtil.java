package me.xuewen.chat.util;


import me.xuewen.chat.constant.code.GlobalResponseCode;
import me.xuewen.chat.entity.Response;

/**
 * @author 葛学文
 * @date 2019/7/14 11:16
 */
public final class ResultUtil {
    private ResultUtil() {
    }

    /**
     * 接口请求成功返回
     */
    public static Response success(Object object) {
        Response response = new Response();
        response.setCode(GlobalResponseCode.SUCCESS);
        response.setMsg("请求成功");
        response.setData(object);
        return response;
    }

    /**
     * 接口请求成功返回
     */
    public static Response success() {
        Response response = new Response();
        response.setCode(GlobalResponseCode.SUCCESS);
        response.setMsg("请求成功");
        return response;
    }

    /**
     * 接口请求失败返回
     */
    public static Response error(String code, String resultMessage) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(resultMessage);
        return response;
    }

    /**
     * 接口请求失败返回
     */
    public static Response error(String resultMessage) {
        Response response = new Response();
        response.setCode(GlobalResponseCode.FAILED);
        response.setMsg(resultMessage);
        return response;
    }
}
