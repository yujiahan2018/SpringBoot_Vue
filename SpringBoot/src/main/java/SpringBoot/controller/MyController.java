package SpringBoot.controller;


import SpringBoot.bean.User;
import SpringBoot.service.MyService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;

@RestController
//整个Controller cors解决
//@CrossOrigin
public class MyController {
    @Autowired
    MyService myService;

//    Controller局部cors解决
//    @CrossOrigin
    @RequestMapping("/data")
    public User selectUser(@RequestParam("id") int id, HttpServletResponse hsrp )
            throws InterruptedException, URISyntaxException {
//        System.out.print(id);

        if (id == 1) {
//            Thread.sleep(5000);
//            MyLogger.loggerInfo("1s后唤醒程序");
//            Thread.sleep(1000);

            Cookie cookie = new Cookie("userName","测试参数");
            hsrp.addCookie(cookie);

        }

        return myService.selectUser(id);
    }

//    转发&重定向
//    @GetMapping("/Error")
//    public ModelAndView Error(){
//        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName("forward:/Error.html");//转发
////        modelAndView.setViewName("redirect:/Error.html");//重定向
//
//        return modelAndView;
//    }


    @RequestMapping("/Error")
    public String Error(){
        return "/Error.html";
    }

}
