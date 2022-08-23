package SpringBoot.controller;


import SpringBoot.bean.User;
import SpringBoot.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//整个Controller cors解决
//@CrossOrigin
public class MyController {
    @Autowired
    MyService myService;

//    Controller局部cors解决
//    @CrossOrigin
    @RequestMapping("/data")
    public User selectUser(@RequestParam("id") int id){
//        System.out.print(id);
        return myService.selectUser(id);
    }

    @RequestMapping("/Error")
    public String Error(){
//        System.out.print(id);
        return "error!";
    }

}
