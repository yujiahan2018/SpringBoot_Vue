package SpringBoot.controller;


import SpringBoot.logger.MyLogger;
import SpringBoot.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
//整个Controller cors解决
@CrossOrigin
public class MyController {
    @Autowired
    MyService myService;

//    public User selectUser(@RequestParam("id") int id)
//            throws InterruptedException, URISyntaxException {
//
//        return myService.selectUser(id);
//    }


    //    登录
//    @CrossOrigin
    /**
     *    前端传来的数据如何处理
     *    传给前端的数据如何处理（转成json）
     *
     */

    @RequestMapping("/data")
    public Map selectUser(@RequestBody String jsonMsg,
//                           @RequestParam("id") int id,
//                           @RequestParam("password") String password,
                          HttpServletResponse hsrp,
                          HttpSession session,
                          ModelAndView modelAndView)
            throws InterruptedException, URISyntaxException, IOException, JSONException {
//        System.out.print(id);


        MyLogger.loggerInfo(jsonMsg);
        //处理json数据
        JSONObject jsonObject = new JSONObject(jsonMsg);
        MyLogger.loggerInfo(jsonObject);
        MyLogger.loggerInfo(jsonObject.getJSONObject("data"));
        MyLogger.loggerInfo(jsonObject.getJSONObject("data").getString("id"));
        MyLogger.loggerInfo(jsonObject.getJSONObject("data").getInt("id"));

        int id = jsonObject.getJSONObject("data").getInt("id");

//        MyLogger.loggerInfo(jsonMsg.data);
//        MyLogger.loggerInfo(id);
//        MyLogger.loggerInfo(password);
        MyLogger.loggerInfo(myService.selectUser(id));

        String returnMsg = "{" +
                "HTTP-Status-Code" + ":" + "200," +
                "HTTP-Status" + ":" + "ok" +
                "}";

        HashMap hashMap = new HashMap<>();
        hashMap.put("HTTP-Status-Code","200");
        hashMap.put("HTTP-Status","OK");
//        JSONObject jsonObject1 = new JSONObject(returnMsg);
//        MyLogger.loggerInfo(jsonObject1);


//        if (id == myService.selectUser(id).id &&
//                Objects.equals(password, myService.selectUser(id).password)) {
//            session.setAttribute("login","true");
//            MyLogger.loggerInfo("登录成功");
//            modelAndView.setViewName("redirect:/ok.html");
//            MyLogger.loggerInfo(session.getAttribute("login"));
//
//            return myService.selectUser(jsonMsg.data.id);
//
//        }
//
////        return myService.selectUser(id);
//        else {
////            hsrp.sendRedirect("http://localhost:8080/");
//
//        return myService.selectUser(id);
        return hashMap;
//        return returnMsg;
//
//        }
    }

    @RequestMapping("/Error")
    public String Error(){
//        System.out.print(id);
        return "error!";
    }

}
