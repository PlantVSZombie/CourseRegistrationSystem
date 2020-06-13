package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.GradeService;
import cn.edu.jlu.ccst.glzz.system.Service.ShowStudentTableService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Professor;
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
public class ShowStudentTableController {

    @Resource
    ShowStudentTableService showStudentTableService;

    @RequestMapping(value="/professor/student_table.json",produces="application/json;charset=UTF-8")
    public JSONObject getStudentTable(int limit, int page, HttpSession session, String searchParams)throws IOException {
        User user=(User)session.getAttribute("user");
        Professor professor=(Professor)user.getPerson();
        JSONObject jsonObject;
        if(searchParams!=null){
            jsonObject=JSONObject.parseObject(searchParams);
        }else {
            jsonObject=new JSONObject();

        }
        List<Map<String,Object>> student_table=showStudentTableService.getStudentTable(professor.getProfessorId(),limit, page,
                jsonObject.getString("course_id"),jsonObject.getString("title"),jsonObject.getInteger("class_id"),
                jsonObject.getString("semester"),jsonObject.getInteger("year"),jsonObject.getDouble("credits"),
                jsonObject.getString("student_name"));
        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",student_table.size());
        jsonUtil.put("data",student_table);
        jsonUtil.put("code",0);

        return jsonUtil.getJsonObject();
    }


    @RequestMapping(value="/professor/correctGrade.json",produces="application/json;charset=UTF-8")
    public Result correctGrade(HttpSession session,int class_id,String student_id,String grade)throws IOException{
        User user=(User)session.getAttribute("user");

        showStudentTableService.correctGrade(class_id,student_id,grade);


        return Result.ok();
    }
}
