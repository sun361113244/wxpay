package com.mt.po;

public class ApiReturn
{
    private Integer returnCode;
    private String msg;
    private Object obj;

    public Integer getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getObj()
    {
        return obj;
    }

    public void setObj(Object obj)
    {
        this.obj = obj;
    }
}
