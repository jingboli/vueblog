package cc.lijingbo.vueblog.domain;


public class ResponseWrapper {
    private String code;
    private String msg;
    private Object data;

    public static ResponseWrapper succ(Object data) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode("0");
        responseWrapper.setData(data);
        responseWrapper.setMsg("success");
        return responseWrapper;
    }

    public static ResponseWrapper succ(String msg, Object data) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode("0");
        responseWrapper.setData(data);
        responseWrapper.setMsg(msg);
        return responseWrapper;
    }

    public static ResponseWrapper fail(String msg) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode("1");
        responseWrapper.setData(null);
        responseWrapper.setMsg(msg);
        return responseWrapper;
    }

    public static ResponseWrapper fail(String msg, Object data) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setCode("1");
        responseWrapper.setData(data);
        responseWrapper.setMsg(msg);
        return responseWrapper;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
