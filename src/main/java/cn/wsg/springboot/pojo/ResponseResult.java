package cn.wsg.springboot.pojo;

public class ResponseResult<T> {

    private final int code;
    private final String message;
    private final T data;

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
