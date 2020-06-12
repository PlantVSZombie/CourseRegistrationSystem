package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.LoginService;
import cn.edu.jlu.ccst.glzz.system.Service.UserService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.Util.ReadFileUtil;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Admin;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Professor;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private LoginService loginService;

    @RequestMapping(value = {"/user_info.json"},produces="application/json;charset=UTF-8")
    public JSONObject getUserInfo(HttpSession session){
        User user=(User)session.getAttribute("user");
        String username="";
        switch (user.getUserType()){
            case Student:
                Student student=(Student)user.getPerson();
                username=student.getStudentName();
                break;
            case Admin:
                Admin admin=(Admin) user.getPerson();
                username=admin.getAdminName();
                break;
            case Professor:
                Professor professor=(Professor) user.getPerson();
                username=professor.getProfessorName();
                break;
        }
        JsonUtil jsonUtil=new JsonUtil(200,"ok");
        jsonUtil.put("username",username);
        return jsonUtil.getJsonObject();

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Result login(String userid, String password, String usertype, HttpSession session){
        if(userid==null ||password ==null){
            return Result.build(10002,"用户名和密码不能为空" );
        }
        User user = loginService.login(userid,password,usertype);
        if(user!=null){
            session.setAttribute("user",user);
            return Result.ok();
        }
        return Result.build(1001,"用户名或密码错误");

    }

    @RequestMapping("/logout")
    public Result logout(HttpSession session){
        if(session.getAttribute("user")!=null){
            session.removeAttribute("user");
            return Result.ok();
        }else {
            return Result.build(200,"您尚未登陆");
        }

    }
}
