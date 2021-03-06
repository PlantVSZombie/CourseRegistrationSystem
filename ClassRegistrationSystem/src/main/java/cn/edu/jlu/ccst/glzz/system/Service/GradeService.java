package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.generated.DAO.TakesDao;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class GradeService {
    @Resource
    TakesDao takesDao;

    public Query getGradeQuery(String student_id,String class_name,Integer year,String semester){
        Query query=new Query();
        query.join("natural join section").join("natural join course");
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
        return query;
    }
    public List<Map<String,Object>> getGrade(String student_id,int limit,int page,String class_name,Integer year,String semester){
        Query query=getGradeQuery(student_id,class_name,year,semester);
        query.page(page,limit);
        List<String> column = Arrays.asList("course_id","title as class_name","year","semester","credits","grade");
        List<Map<String,Object>> grades=takesDao.listMap(column,query);
        return grades;
    }

    public  long getGradeCount(String student_id,String class_name,Integer year,String semester){
        return takesDao.getCount(getGradeQuery(student_id,class_name,year,semester));
    }
}
