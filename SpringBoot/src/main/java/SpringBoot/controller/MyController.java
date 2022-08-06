package SpringBoot.controller;


import SpringBoot.bean.User;
import SpringBoot.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    MyService myService;

    @RequestMapping("/data")
    public User selectUser(@RequestParam("id") int id){
//        System.out.print(id);
        return myService.selectUser(id);
    }

}
