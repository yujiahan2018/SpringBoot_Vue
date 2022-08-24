package SpringBoot.controller;


import SpringBoot.bean.User;
import SpringBoot.logger.MyLogger;
import SpringBoot.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.net.*;

@RestController
//整个Controller cors解决
//@CrossOrigin
public class MyController {
    @Autowired
    MyService myService;

//    Controller局部cors解决
//    @CrossOrigin
    @RequestMapping("/data")
    public User selectUser(@RequestParam("id") int id,HttpServletResponse hsrp)
            throws InterruptedException, URISyntaxException {
//        System.out.print(id);

        if (id == 1) {
            Thread.sleep(5000);
            MyLogger.loggerInfo("1s后唤醒程序");
            Thread.sleep(1000);

            Cookie cookie = new Cookie("userName","测试参数");
            cookie.setMaxAge(10000);//cookie有效期10s
            hsrp.addCookie(cookie);
        }

        return myService.selectUser(id);
    }

    @RequestMapping("/Error")
    public String Error(){
//        System.out.print(id);
        return "error!";
    }

}
