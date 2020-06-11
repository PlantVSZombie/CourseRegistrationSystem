package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@RestController
public class RegisterCourseController {

    @RequestMapping(value = "/admin/courseControl.json",produces="application/json;charset=UTF-8")
    public Result getCourse(HttpSession session,Integer year) throws IOException {
        System.out.println(year);

        return Result.ok();
    }
}
