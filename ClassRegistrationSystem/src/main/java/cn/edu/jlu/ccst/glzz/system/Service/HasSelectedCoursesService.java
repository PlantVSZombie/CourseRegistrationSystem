package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Util.CurrentTime;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.SectionDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.TakesDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.TeachesDao;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.alibaba.fastjson.JSONObject;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class HasSelectedCoursesService {
    @Resource
    TakesDao takesDao ;


    public List<Map<String,Object>> getHasSelectedCourses(String student_id,int limit,int page,String class_name,Integer year,String semester,int ismajor){
        Query query=new Query();
        query.join("natural join teaches").join("natural join professor").join("natural join section").join("natural join sec_time_place").join("natural join course").join("natural join classroom").join("natural join time_slot");
        query.eq("year", CurrentTime.getYear()).eq("semester",CurrentTime.getSemester()).eq("ismajor",ismajor);
        query.page(page,limit);
        query.eq("student_id",student_id);
        if(class_name!=null&&!class_name.equals("")){
            query.like("title",class_name);
        }
        if(year!=null){
            query.eq("year",year);
        }
        if(semester!=null&&!semester.equals("")){
            query.eq("semester",semester);
        }
        List<String> column = Arrays.asList("class_id","course_id","professor_name","title as class_name","credits","course.dept_name","sec_capacity","building","room_number","day","start_time","end_time","ismajor","time_id");
        List<Map<String,Object>> selectCoursesList=takesDao.listMap(column,query);
        //将相同class_id的合并
        List<Map<String, Object>> zhuList = new ArrayList<>();
        for (Map<String, Object> it : selectCoursesList) {
            boolean newone = true;
            //找在不在ansList里
            for (Map<String, Object> ansit : zhuList) {
                if (ansit.get("class_id") == it.get("class_id")) {
                    newone = false;
                    String tem = (String) ansit.get("course_place");
                    tem += "<br>";
                    tem += (String) it.get("building") + " " + (String) it.get("room_number");
                    ansit.put("course_place", tem);
                    String timetem = (String) ansit.get("course_time");
                    timetem += "<br>";
                    int i_day = (int) it.get("day");
                    String s_day = null;
                    switch (i_day) {
                        case 1:
                            s_day = "周一";
                            break;
                        case 2:
                            s_day = "周二";
                            break;
                        case 3:
                            s_day = "周三";
                            break;
                        case 4:
                            s_day = "周四";
                            break;
                        case 5:
                            s_day = "周五";
                            break;
                        case 6:
                            s_day = "周六";
                            break;
                        case 7:
                            s_day = "周日";
                            break;
                    }

                    String start_time2 = (it.get("start_time").toString());
                    start_time2 = start_time2.substring(0, 5);
                    String end_time2 = (it.get("end_time").toString());
                    end_time2 = end_time2.substring(0, 5);
                    timetem += s_day + ' ' + start_time2 + '-' + end_time2;
                    ansit.put("course_time", timetem);
                    ((HashSet<Integer>)ansit.get("time_id")).add((int)(it.get("time_id")));
                    break;
                }
            }
            if (newone) {
                Object day = it.remove("day");
                Object start_time = it.remove("start_time");
                Object end_time = it.remove("end_time");
                Object building = it.remove("building");
                Object room_number = it.remove("room_number");
                Object time_id=it.remove("time_id");
                it.put("time_id",new HashSet<Integer>());
                ((HashSet<Integer>)it.get("time_id")).add((int)time_id);
                int i_day = (int) day;
                String s_day = null;
                switch (i_day) {
                    case 1:
                        s_day = "周一";
                        break;
                    case 2:
                        s_day = "周二";
                        break;
                    case 3:
                        s_day = "周三";
                        break;
                    case 4:
                        s_day = "周四";
                        break;
                    case 5:
                        s_day = "周五";
                        break;
                    case 6:
                        s_day = "周六";
                        break;
                    case 7:
                        s_day = "周日";
                        break;
                }

                String start_time2 = (start_time.toString());
                start_time2 = start_time2.substring(0, 5);
                String end_time2 = (end_time.toString());
                end_time2 = end_time2.substring(0, 5);
                it.put("course_place", (String) building + ' ' + (String) room_number);
                it.put("course_time", s_day + ' ' + start_time2 + '-' + end_time2);
                zhuList.add(it);
            }

        }
        return zhuList;
    }

    public void delete(String student_id,int class_id){
        Query query=new Query();
        query.eq("student_id",student_id).eq("class_id",class_id);
        takesDao.deleteByQuery(query);
    }



    public List<Map<String, Object>> getZhuList(int limit, int page, HttpSession session, String searchParams) throws IOException {
        User user = (User) session.getAttribute("user");
        Student student = (Student) user.getPerson();
        JSONObject jsonObject;
        if (searchParams != null) {
            jsonObject = JSONObject.parseObject(searchParams);
        } else {
            jsonObject = new JSONObject();
        }
        return getHasSelectedCourses(student.getStudentId(), limit, page, jsonObject.getString("class_name"), jsonObject.getInteger("year"), jsonObject.getString("semester"),1);


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
        return getHasSelectedCourses(student.getStudentId(), limit, page, jsonObject.getString("class_name"), jsonObject.getInteger("year"), jsonObject.getString("semester"),0);
    }
}
