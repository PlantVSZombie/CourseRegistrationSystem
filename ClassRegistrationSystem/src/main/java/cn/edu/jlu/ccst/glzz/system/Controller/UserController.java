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
@RequestMapping("/")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name){
//        HashMap<String,Object> hm= new HashMap<>();
//        hm.put("students",userService.getLine());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("student",userService.getLine());
        return jsonObject.toJSONString();
    }
}
