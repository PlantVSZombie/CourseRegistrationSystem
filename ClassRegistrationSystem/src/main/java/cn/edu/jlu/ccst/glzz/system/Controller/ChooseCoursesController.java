package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.ChooseCoursesService;
import cn.edu.jlu.ccst.glzz.system.Service.HasSelectedCoursesService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Takes;
import com.alibaba.fastjson.JSONObject;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
    public Result addZhuCourses(HttpSession session, int class_id, int sec_capacity, String course_id) throws IOException {
        User user=(User)session.getAttribute("user");
        Student student=(Student)user.getPerson();
        int curmember=chooseCoursesService.countMember(class_id);
        //
        List<Map<String, Object>> zhulist=hasSelectedCoursesController.getZhuList(10,1,session,null);
        if(zhulist.size()>=4){
            return Result.ok("添加失败，主选课程最多选择4门!");
        }
        if(curmember>=sec_capacity){
            return Result.ok("添加失败，课程人数已满");
        }
        String unsatisfiedCourses=chooseCoursesService.checkPre(student.getStudentId(),course_id);
        if(!unsatisfiedCourses.equals("")){
            return Result.ok("添加失败，未满足如下前导课的要求:"+unsatisfiedCourses);
        }
        //判断时间冲突
        String timeCrashCourses= chooseCoursesService.checkTimePriblem(student.getStudentId(),class_id,zhulist);

//        for(int it:time_id){
//            for(Map<String,Object> zhuit:zhulist){
//                if(((HashSet<Integer>)zhuit.get("time_id")).contains(it)){
//                    tosay.add(zhuit.get("class_name").toString());
//                }
//            }
//        }

        if(!timeCrashCourses.equals("")){
            return Result.ok("选课失败，和已选的以下主选课程发生冲突:"+timeCrashCourses);
        }

        chooseCoursesService.addCourse(student.getStudentId(),class_id,1);
        return Result.ok("成功加入主选课程");

    }
    @RequestMapping(value = "/student/add_bei",produces="application/json;charset=UTF-8")
    public Result addBeiCourses(HttpSession session, int class_id,int sec_capacity,String course_id) throws IOException {
        User user=(User)session.getAttribute("user");
        Student student=(Student)user.getPerson();
        int curmember=chooseCoursesService.countMember(class_id);
        List<Map<String, Object>> beilist=hasSelectedCoursesController.getBeiList(10,1,session,null);
        if(beilist.size()>=2){
            return Result.ok("添加失败,备选课程最多选择2门!");
        }
        Takes tem=chooseCoursesService.findCourse(student.getStudentId(),class_id);
        if(tem==null) {
            if (curmember >= sec_capacity) {
                return Result.ok("添加失败,课程人数已满");
            }
            String unsatisfiedCourses = chooseCoursesService.checkPre(student.getStudentId(), course_id);
            if (!unsatisfiedCourses.equals("")) {
                return Result.ok("添加失败,未满足如下前导课的要求:" + unsatisfiedCourses);
            }
        }
        chooseCoursesService.addCourse(student.getStudentId(),class_id,0);
        return Result.ok("成功加入备选课程");

    }


}
