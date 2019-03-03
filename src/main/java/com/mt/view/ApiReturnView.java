package com.mt.view;

import com.alibaba.fastjson.JSON;
import com.mt.po.ApiReturn;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ApiReturnView extends AbstractView
{
    private final static String CONTENT_TYPE = "application/json";

    private final static String CharacterEncoding = "utf-8";

    public ApiReturnView()
    {
        super();
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
    {
        ApiReturn apiReturn = (ApiReturn) model.get("ApiReturn");

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(String.format("\"result\": \"%s\" ," , apiReturn.getReturnCode().toString()));
        if(apiReturn.getReturnCode() != null)
            sb.append(String.format("\"msg\": \"%s\" ," , apiReturn.getMsg()));
        if(apiReturn.getObj() != null)
            sb.append(String.format("\"data\": %s ," , JSON.toJSONString(apiReturn.getObj())));

        sb.deleteCharAt(sb.length() - 1 );
        sb.append("}");
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CharacterEncoding);
        response.getWriter().write(sb.toString());
        response.getWriter().close();
    }
}
