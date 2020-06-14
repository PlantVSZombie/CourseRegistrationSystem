package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.ChooseCoursesService;
import cn.edu.jlu.ccst.glzz.system.Service.HasSelectedCoursesService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
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
public class ChooseCoursesController {

    @Resource
    ChooseCoursesService chooseCoursesService;
    @Resource
    HasSelectedCoursesService hasSelectedCoursesService;
    @Resource
    HasSelectedCoursesController hasSelectedCoursesController;

    @RequestMapping(value = "/student/select_courses.json",produces="application/json;charset=UTF-8")
    public JSONObject getSelectCourse(int limit, int page, HttpSession session, String searchParams) throws IOException {
        User user=(User)session.getAttribute("user");
        Student student=(Student)user.getPerson();
        JSONObject jsonObject;
        if(searchParams!=null){
            jsonObject=JSONObject.parseObject(searchParams);
        }else {
            jsonObject=new JSONObject();
        }
        List<Map<String,Object>> ansList=chooseCoursesService.getSelectCourses(student.getStudentId(),limit,page,jsonObject.getString("class_name"),jsonObject.getInteger("year"),jsonObject.getString("semester"));

        List<Map<String, Object>> zhulist = new ArrayList<>();
        List<Map<String, Object>> beilist = new ArrayList<>();
        beilist=hasSelectedCoursesController.getBeiList(limit,page,session,searchParams);
        zhulist=hasSelectedCoursesController.getZhuList(limit,page,session,searchParams);

        for(Map<String,Object> ansit:ansList){
            Boolean flag=true;
            for(Map<String,Object> zhuit:zhulist){
                if((int)ansit.get("class_id")==(int)zhuit.get("class_id")){
                    ansit.put("state","zhu");
                    flag=false;
                    break;
                }
            }
            if(flag){
                for(Map<String,Object> beiit:beilist){
                    if((int)ansit.get("class_id")==(int)beiit.get("class_id")){
                        ansit.put("state","bei");
                        flag=false;
                        break;
                    }
                }
            }
            if(flag){
                ansit.put("state","untaken");
            }
        }


//        for(Map<String,Object> it:ansList){
//            String tem=it.get("course_place").toString();
//            tem+="</pre>";
//            it.put("course_place",tem);
//            String timetem=it.get("course_time").toString();
//            timetem+="</pre>";
//            it.put("course_time",timetem);
//        }
        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",ansList.size());
        jsonUtil.put("data",ansList);
        jsonUtil.put("code",0);

        return jsonUtil.getJsonObject();

    }
    @RequestMapping(value = "/student/add_zhu",produces="application/json;charset=UTF-8")
    public Result addZhuCourses(HttpSession session, int class_id){
        User user=(User)session.getAttribute("user");
        Student student=(Student)user.getPerson();
        chooseCoursesService.addCourse(student.getStudentId(),class_id,1);
        return Result.ok();

    }
    @RequestMapping(value = "/student/add_bei",produces="application/json;charset=UTF-8")
    public Result addBeiCourses(HttpSession session, int class_id){
        User user=(User)session.getAttribute("user");
        Student student=(Student)user.getPerson();
        chooseCoursesService.addCourse(student.getStudentId(),class_id,0);
        return Result.ok();

    }
}
