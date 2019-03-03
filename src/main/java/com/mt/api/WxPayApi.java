package com.mt.api;

import com.mt.listener.DeviceListener;
import com.mt.po.ApiReturn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/WxPayApi")
public class WxPayApi
{
//    @Resource
//    private DeviceListener deviceListener;

    public ModelAndView wxpayCallBack(String msg) {

//        deviceListener

        ApiReturn apiReturn = new ApiReturn();
        apiReturn.setReturnCode(1);
        apiReturn.setMsg("success");

        ModelAndView mav = new ModelAndView("ApiReturnView");

        mav.addObject("RecApiReturn", apiReturn);
        return mav;
    }
}
