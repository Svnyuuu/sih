package com.ahu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;// 业务状态码-成功1-失败
    private String message;// 提示信息
    private T data;// 响应数据

    // 快速返回操作成功响应结果
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Result success() {
        return new Result(0, "操作成功", null);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Result success(String message) {
        return new Result(0, message, null);
    }

    public static <E> Result<E> success(String message, E data) {
        return new Result<>(0, message, data);
    }

    // 快速返回操作失败响应结果
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Result error(String message) {
        return new Result(1, message, null);
    }

}
