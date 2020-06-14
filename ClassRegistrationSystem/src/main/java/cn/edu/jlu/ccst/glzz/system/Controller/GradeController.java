package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.GradeService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.Util.ReadFileUtil;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class GradeController {
    @Resource
    GradeService gradeService;


    @RequestMapping(value = "/student/grade.json",produces="application/json;charset=UTF-8")
    public JSONObject getGrade(int limit,int page,HttpSession session,String searchParams)  {
        User user=(User)session.getAttribute("user");
        Student student=(Student)user.getPerson();
        JSONObject jsonObject;
        if(searchParams!=null){
            jsonObject=JSONObject.parseObject(searchParams);
        }else {
            jsonObject=new JSONObject();

        }
        List<Map<String,Object>> grades=gradeService.getGrade(student.getStudentId(),limit,page,jsonObject.getString("class_name"),jsonObject.getInteger("year"),jsonObject.getString("semester"));


        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",gradeService.getGradeCount(student.getStudentId(),jsonObject.getString("class_name"),jsonObject.getInteger("year"),jsonObject.getString("semester")));
        jsonUtil.put("data",grades);
        jsonUtil.put("code",0);

        return jsonUtil.getJsonObject();
    }
}
