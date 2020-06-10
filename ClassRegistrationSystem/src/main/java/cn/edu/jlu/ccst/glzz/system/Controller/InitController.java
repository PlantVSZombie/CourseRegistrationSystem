package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Util.ReadFileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class InitController {

    @RequestMapping("dynamic/init.json")
    public String initJson(HttpSession session) throws IOException {
        User user=(User)session.getAttribute("user");
        switch (user.getUserType()){
            case Student:
                return ReadFileUtil.readString("static/newapi/student.json","UTF-8");
            case Admin:
                return ReadFileUtil.readString("static/newapi/admin.json","UTF-8");
            case Professor:
                return ReadFileUtil.readString("static/newapi/professor.json","UTF-8");
        }
        return null;
    }
}
