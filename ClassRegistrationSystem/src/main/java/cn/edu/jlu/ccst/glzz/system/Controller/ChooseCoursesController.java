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
        List<Map<String,Object>> selectCoursesList=chooseCoursesService.getSelectCourses(student.getStudentId(),limit,page,jsonObject.getString("class_name"),jsonObject.getInteger("year"),jsonObject.getString("semester"));
        List<Map<String,Object>> ansList=new ArrayList<>();
        //将相同class_id的合并
        for(Map<String,Object> it:selectCoursesList){
            boolean newone=true;
            //找在不在ansList里
            for(Map<String,Object> ansit:ansList)
            {
                if(ansit.get("class_id")==it.get("class_id")){
                    newone=false;
                    String tem=(String)ansit.get("course_place");
                    tem+="<br>";
                    tem+=(String)it.get("building")+" "+(String)it.get("room_number");
                    ansit.put("course_place",tem);
                    String timetem=(String)ansit.get("course_time");
                    timetem+="<br>";
                    int i_day=(int)it.get("day");
                    String s_day=null;
                    switch (i_day){
                        case 1:s_day="周一";
                            break;
                        case 2:s_day="周二";
                            break;
                        case 3:s_day="周三";
                            break;
                        case 4: s_day="周四";
                            break;
                        case 5: s_day="周五";
                            break;
                        case 6:s_day="周六";
                            break;
                        case 7:s_day="周日";
                            break;
                    }

                    String start_time2=(it.get("start_time").toString());
                    start_time2=start_time2.substring(0,5);
                    String end_time2=(it.get("end_time").toString());
                    end_time2=end_time2.substring(0,5);
                    timetem+=s_day+' '+start_time2+'-'+end_time2;
                    ansit.put("course_time",timetem);
                    break;
                }
            }
            if(newone){
                Object day=it.remove("day");
                Object start_time=it.remove("start_time");
                Object end_time=it.remove("end_time");
                Object building=it.remove("building");
                Object room_number=it.remove("room_number");
                int i_day=(int)day;
                String s_day=null;
                switch (i_day){
                    case 1:s_day="周一";
                    break;
                    case 2:s_day="周二";
                    break;
                    case 3:s_day="周三";
                    break;
                    case 4: s_day="周四";
                    break;
                    case 5: s_day="周五";
                    break;
                    case 6:s_day="周六";
                    break;
                    case 7:s_day="周日";
                    break;
                }

                String start_time2=(start_time.toString());
                start_time2=start_time2.substring(0,5);
                String end_time2=(end_time.toString());
                end_time2=end_time2.substring(0,5);
                it.put("course_place",(String)building+' '+(String)room_number);
                it.put("course_time",s_day+' '+start_time2+'-'+end_time2);
                ansList.add(it);
            }

        }
        List<Map<String, Object>> zhulist = new ArrayList<>();
        List<Map<String, Object>> beilist = new ArrayList<>();
        beilist=hasSelectedCoursesController.getBeiList(limit,page,session,searchParams);
        zhulist=hasSelectedCoursesController.getZhuList(limit,page,session,searchParams);

        for(Map<String,Object> ansit:ansList){
            Boolean flag=true;
            for(Map<String,Object> zhuit:zhulist){
                if(ansit.get("class_id")==zhuit.get("class_id")){
                    ansit.put("state","zhu");
                    flag=false;
                    break;
                }
            }
            if(flag){
                for(Map<String,Object> beiit:beilist){
                    if(ansit.get("class_id")==beiit.get("class_id")){
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

}
