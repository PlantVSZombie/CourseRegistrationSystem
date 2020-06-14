package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.ChooseProfessorCourseService;
import cn.edu.jlu.ccst.glzz.system.Service.ShowStudentTableService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ChooseProfessorCourseController {

    @Resource
    ChooseProfessorCourseService chooseProfessorCourseService;
    @Resource
    ShowProfessorChosenCourseController showProfessorChosenCourseController;

    @RequestMapping(value="/professor/professor_choose_course.json",produces="application/json;charset=UTF-8")
    public JSONObject getProfessor_accessCourse(int limit, int page, HttpSession session, String searchParams)throws IOException {
        User user=(User)session.getAttribute("user");
        Professor professor=(Professor)user.getPerson();
        JSONObject jsonObject;
        if(searchParams!=null){
            jsonObject=JSONObject.parseObject(searchParams);
        }else {
            jsonObject=new JSONObject();

        }
        List<Map<String,Object>> professor_access_course=chooseProfessorCourseService.getProfessor_accessCourse(professor.getProfessorId(),
                limit, page, CurrentTime.getSemester(), CurrentTime.getYear());           //教授选课目录


        List<Map<String, Object>> chosen_list = new ArrayList<>();
        //chosen_list=hasSelectedCoursesController.getZhuList(limit,page,session,searchParams);
        chosen_list=showProfessorChosenCourseController.getChosenList(limit,page,session,searchParams);

        for(Map<String,Object> ansit:professor_access_course){
            Boolean flag=true;
            for(Map<String,Object> zhuit:chosen_list){
                if((int)ansit.get("class_id")==(int)zhuit.get("class_id")){
                    ansit.put("state","chosen");
                    flag=false;
                    break;
                }
            }
            if(flag){
                ansit.put("state","untaken");
            }
        }


        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",professor_access_course.size());
        jsonUtil.put("data",professor_access_course);
        jsonUtil.put("code",0);

        return jsonUtil.getJsonObject();
    }


    @RequestMapping(value="/professor/add_professor_course",produces="application/json;charset=UTF-8")
    public Result add_professor_course(HttpSession session, int class_id){
        User user=(User)session.getAttribute("user");
        Professor professor=(Professor)user.getPerson();
        chooseProfessorCourseService.add_professor_course(professor.getProfessorId(),class_id);
        return Result.ok();

    }

    @RequestMapping(value = "/professor/cancel_professor_course", produces = "application/json;charset=UTF-8")
    public Result deleteCourse(HttpSession session, int class_id){
        User user=(User)session.getAttribute("user");
        Professor professor=(Professor) user.getPerson();
        chooseProfessorCourseService.delete(professor.getProfessorId(),class_id);
        return Result.ok("删除成功");
    }

}
