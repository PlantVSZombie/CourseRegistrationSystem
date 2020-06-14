package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Util.Result;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.*;
import cn.edu.jlu.ccst.glzz.system.generated.Model.SecTimePlace;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Takes;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Teaches;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ChooseProfessorCourseService {
    @Resource
    TeachesDao teachesDao;
    @Resource
    ProfessorDao professorDao;
    @Resource
    SecTimePlaceDao secTimePlaceDao;

    public List<Map<String,Object>> getProfessor_accessCourse(String professor_id, int limit, int page,String semester,Integer year){
        Query query_1=new Query();
        query_1.join("natural join course join section join sec_time_place join classroom join time_slot ");
        query_1.sql("section.course_id = course.course_id and section.class_id = sec_time_place.class_id and" +
                " sec_time_place.classroom_id = classroom.classroom_id and time_slot.time_id = sec_time_place.time_id");
        query_1.eq("professor_id",professor_id);
        query_1.eq("year",year);
        query_1.eq("semester",semester);
        query_1.page(page,limit);

        List<String> column = Arrays.asList("section.class_id","course.course_id","title","sec_id","semester","year","credits","dept_name",
                "cost","building","room_number","day","start_time","end_time");
        List<Map<String,Object>> professor_access_course=professorDao.listMap(column,query_1);

        List<Map<String,Object>> professor_ansList=new ArrayList<>();
        //将相同class_id的合并
        for(Map<String,Object> it:professor_access_course){
            boolean newone=true;
            //找是否在ansList里
            for(Map<String,Object> ansit:professor_ansList)
            {
                if(ansit.get("section.class_id")==it.get("section.class_id")){
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
                professor_ansList.add(it);
            }

        }
        return professor_ansList;
    }

    public void add_professor_course(String professor_id,int class_id){
        Query query = new Query();
        //query.eq("professor_id",professor_id);
        query.eq("class_id",class_id);
        //query.page(page,limit);

        //List<String> column = Arrays.asList("class_id");
        Teaches tea = teachesDao.getByQuery(query);     //判断该课程是否已被其他老师选择
        if(tea == null){
            Query query2 = new Query();
            query2.eq("class_id",class_id);
            SecTimePlace secTimePlace = secTimePlaceDao.getByQuery(query2);
            Integer the_class_time = secTimePlace.getTimeId();    //得到将选课程时间
            Query query3 = new Query();
            query3.join(" natural join teaches ");
            query3.eq("professor_id",professor_id);
            query3.eq("time_id",the_class_time);
            SecTimePlace secTimePlace1 = secTimePlaceDao.getByQuery(query3);
            if(secTimePlace1 == null){            //判读该课程是否与已选课程时间冲突s
                Teaches teaches = new Teaches();
                teaches.setProfessorId(professor_id);
                teaches.setClassId(class_id);
                teachesDao.save(teaches);
            }
            else{
                System.out.println("将选课程与已选课程时间冲突");
            }
        }
        else
        {
            int ans=teachesDao.updateByQuery(tea,query);
            System.out.println(ans);
        }
    }



}
