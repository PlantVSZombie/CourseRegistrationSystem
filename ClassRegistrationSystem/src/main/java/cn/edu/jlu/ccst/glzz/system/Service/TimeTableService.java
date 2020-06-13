package cn.edu.jlu.ccst.glzz.system.Service;


import cn.edu.jlu.ccst.glzz.system.generated.DAO.TakesDao;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TimeTableService {
    @Resource
    TakesDao takesDao;


    public List<Map<String,Object>> getTimeTable(String student_id){
        com.gitee.fastmybatis.core.query.Query query=new Query();
        query.join("natural join section").join("natural join course").join("natural join sec_time_place");
        query.join("natural join classroom").join("natural join time_slot");
        query.eq("student_id",student_id);
        List<String> column = Arrays.asList("title as class_name","time_id","start_time","end_time","building","room_number");
        List<Map<String,Object>> timetable=takesDao.listMap(column,query);
        return timetable;
    }

}
