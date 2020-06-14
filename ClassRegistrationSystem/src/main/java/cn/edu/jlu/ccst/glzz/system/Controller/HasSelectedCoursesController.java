package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.ChooseCoursesService;
import cn.edu.jlu.ccst.glzz.system.Service.HasSelectedCoursesService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
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



    public List<Map<String, Object>> getZhuList(int limit, int page, HttpSession session, String searchParams) throws IOException{
        User user = (User) session.getAttribute("user");
        Student student = (Student) user.getPerson();
        JSONObject jsonObject;
        if (searchParams != null) {
            jsonObject = JSONObject.parseObject(searchParams);
        } else {
            jsonObject = new JSONObject();
        }
        return hasSelectedCoursesService.getHasSelectedCourses(student.getStudentId(), limit, page, jsonObject.getString("class_name"), jsonObject.getInteger("year"), jsonObject.getString("semester"),1);


    }

    public List<Map<String, Object>> getBeiList(int limit, int page, HttpSession session, String searchParams) throws IOException {
        User user = (User) session.getAttribute("user");
        Student student = (Student) user.getPerson();
        JSONObject jsonObject;
        if (searchParams != null) {
            jsonObject = JSONObject.parseObject(searchParams);
        } else {
            jsonObject = new JSONObject();
        }
        return hasSelectedCoursesService.getHasSelectedCourses(student.getStudentId(), limit, page, jsonObject.getString("class_name"), jsonObject.getInteger("year"), jsonObject.getString("semester"),0);
    }

    @RequestMapping(value = "/student/selected_major_courses.json", produces = "application/json;charset=UTF-8")
    public JSONObject getSelectedMajorCourse(int limit, int page, HttpSession session, String searchParams) throws IOException {

        List<Map<String, Object>> zhuList = getZhuList(limit,page,session,searchParams);

        JsonUtil jsonUtil = new JsonUtil(200, "");
        jsonUtil.put("count", zhuList.size());
        jsonUtil.put("data", zhuList);
        jsonUtil.put("code", 0);

        return jsonUtil.getJsonObject();
    }

    @RequestMapping(value = "/student/selected_alternative_courses.json", produces = "application/json;charset=UTF-8")
    public JSONObject getSelectedAlternativeCourse(int limit, int page, HttpSession session, String searchParams) throws IOException {

        List<Map<String, Object>> beiList =  getBeiList(limit,page,session,searchParams);
        JsonUtil jsonUtil = new JsonUtil(200, "");
        jsonUtil.put("count", beiList.size());
        jsonUtil.put("data", beiList);
        jsonUtil.put("code", 0);

        return jsonUtil.getJsonObject();
    }


}