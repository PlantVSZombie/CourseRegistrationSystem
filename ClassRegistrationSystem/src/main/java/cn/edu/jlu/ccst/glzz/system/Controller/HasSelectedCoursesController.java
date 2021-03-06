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
public class HasSelectedCoursesController {
    @Resource
    HasSelectedCoursesService hasSelectedCoursesService;




    @RequestMapping(value = "/student/selected_major_courses.json", produces = "application/json;charset=UTF-8")
    public JSONObject getSelectedMajorCourse(int limit, int page, HttpSession session, String searchParams) throws IOException {

        List<Map<String, Object>> zhuList = hasSelectedCoursesService.getZhuList(limit,page,session,searchParams);

        JsonUtil jsonUtil = new JsonUtil(200, "");
        jsonUtil.put("count", zhuList.size());
        jsonUtil.put("data", zhuList);
        jsonUtil.put("code", 0);

        return jsonUtil.getJsonObject();
    }

    @RequestMapping(value = "/student/selected_alternative_courses.json", produces = "application/json;charset=UTF-8")
    public JSONObject getSelectedAlternativeCourse(int limit, int page, HttpSession session, String searchParams) throws IOException {

        List<Map<String, Object>> beiList =  hasSelectedCoursesService.getBeiList(limit,page,session,searchParams);
        JsonUtil jsonUtil = new JsonUtil(200, "");
        jsonUtil.put("count", beiList.size());
        jsonUtil.put("data", beiList);
        jsonUtil.put("code", 0);

        return jsonUtil.getJsonObject();
    }

    @RequestMapping(value = "/student/delete_course.json", produces = "application/json;charset=UTF-8")
    public Result deleteCourse(HttpSession session, int class_id){
        User user=(User)session.getAttribute("user");
        Student student=(Student)user.getPerson();
        hasSelectedCoursesService.delete(student.getStudentId(),class_id);
        return Result.ok("删除成功");
    }
}