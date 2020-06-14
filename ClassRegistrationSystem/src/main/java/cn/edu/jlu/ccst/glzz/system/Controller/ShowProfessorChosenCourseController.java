package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.ChooseProfessorCourseService;
import cn.edu.jlu.ccst.glzz.system.Service.ShowProfessorChosenCourseService;
import cn.edu.jlu.ccst.glzz.system.Util.CurrentTime;
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
public class ShowProfessorChosenCourseController {

    @Resource
    ShowProfessorChosenCourseService showProfessorChosenCourseService;


    public List<Map<String, Object>> getChosenList(int limit, int page, HttpSession session, String searchParams) throws IOException{
        User user = (User) session.getAttribute("user");
        Professor professor=(Professor)user.getPerson();
        JSONObject jsonObject;
        if (searchParams != null) {
            jsonObject = JSONObject.parseObject(searchParams);
        } else {
            jsonObject = new JSONObject();
        }
        return showProfessorChosenCourseService.showProfessor_chosenCourse(professor.getProfessorId(),
                limit, page, CurrentTime.getSemester(),Integer.valueOf(CurrentTime.getYear()));

    }

    @RequestMapping(value="/professor/professor_has_chosen.json",produces="application/json;charset=UTF-8")
    public JSONObject showProfessor_chosenCourse(int limit, int page, HttpSession session, String searchParams)throws IOException {
        User user=(User)session.getAttribute("user");
        Professor professor=(Professor)user.getPerson();
        JSONObject jsonObject;
        if(searchParams!=null){
            jsonObject=JSONObject.parseObject(searchParams);
        }else {
            jsonObject=new JSONObject();

        }
        List<Map<String,Object>> student_table=showProfessorChosenCourseService.showProfessor_chosenCourse(professor.getProfessorId(),
                limit, page, CurrentTime.getSemester(),Integer.valueOf(CurrentTime.getYear()));
        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",student_table.size());
        jsonUtil.put("data",student_table);
        jsonUtil.put("code",0);

        return jsonUtil.getJsonObject();
    }


}
