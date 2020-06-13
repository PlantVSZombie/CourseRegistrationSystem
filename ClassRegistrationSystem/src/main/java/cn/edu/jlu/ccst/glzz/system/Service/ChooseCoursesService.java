package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Util.CurrentTime;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.SectionDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.TakesDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.TeachesDao;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ChooseCoursesService {
    @Resource
    TeachesDao teachesDao;
    TakesDao takesDao ;

    public List<Map<String,Object>> getSelectCourses(String student_id,int limit,int page,String class_name,Integer year,String semester){
        Query query=new Query();
        query.join("natural join professor").join("natural join section").join("natural join sec_time_place").join("natural join course").join("natural join classroom").join("natural join time_slot");
        query.eq("year", CurrentTime.getYear()).eq("semester",CurrentTime.getSemester());
        query.page(page,limit);
        if(class_name!=null&&!class_name.equals("")){
            query.like("title",class_name);
        }
        if(year!=null){
            query.eq("year",year);
        }
        if(semester!=null&&!semester.equals("")){
            query.eq("semester",semester);
        }
        List<String> column = Arrays.asList("class_id","course_id","professor_name","title as class_name","credits","course.dept_name","sec_capacity","building","room_number","day","start_time","end_time");
        List<Map<String,Object>> selectCourses=teachesDao.listMap(column,query);

        return selectCourses;
    }


}
