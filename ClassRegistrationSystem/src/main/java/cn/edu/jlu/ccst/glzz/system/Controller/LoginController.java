package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    private UserService userService;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name){
//        HashMap<String,Object> hm= new HashMap<>();
//        hm.put("students",userService.getLine());
        System.out.println("test");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("student",userService.getLine());
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(String userid,String password,String usertype){
        switch (usertype){
            case "学生":
                break;
            case "教授":
                break;
            case "管理员":
                break;
            default:
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("resultCode",200);
        return jsonObject.toJSONString();
    }

}
