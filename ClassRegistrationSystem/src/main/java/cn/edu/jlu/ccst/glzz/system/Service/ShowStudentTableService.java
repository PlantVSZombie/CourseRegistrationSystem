package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.generated.DAO.TakesDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.TeachesDao;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Takes;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ShowStudentTableService {
    @Resource
    TeachesDao teachesDao;
    @Resource
    TakesDao takesDao;

    public List<Map<String,Object>> getStudentTable(String professor_id, int limit, int page, String course_id,
                                                    String title,Integer sec_id,String semester,Integer year,
                                                    Double credits,String student_name){
        Query query_1=new Query();
        query_1.join("natural join section join takes join student join course ");
        query_1.sql("takes.student_id = student.student_id and takes.class_id = section.class_id and " +
               // "section.class_id = teaches.class_id and " +
                        "section.course_id = course.course_id");
        query_1.eq("professor_id",professor_id);
        query_1.page(page,limit);
        if(title!=null&&!title.equals("")){
            query_1.eq("title",title);
        }
        if(year!=null){
            query_1.eq("year",year);
        }
        if(semester!=null&&!semester.equals("")){
            query_1.eq("semester",semester);
        }
        if(course_id!=null&&!course_id.equals("")){
            query_1.eq("course.course_id",course_id);
        }
        if(sec_id!=null){
            query_1.eq("sec_id",sec_id);
        }
        if(credits!=null){
            query_1.eq("credits",credits);
        }
        if(student_name!=null&&!student_name.equals("")){
            query_1.eq("student_name",student_name);
        }

        if(professor_id!=null&&((title!=null&&!title.equals(""))||(year!=null)||(semester!=null&&!semester.equals(""))||
                (course_id!=null&&!course_id.equals(""))||(sec_id!=null)||(credits!=null)||
                (student_name!=null&&!student_name.equals("")))){
            List<String> column = Arrays.asList("course.course_id","title","sec_id","semester","year","credits","student.student_id",
                    "student_name","grade");
            List<Map<String,Object>> course_table=teachesDao.listMap(column,query_1);
            return course_table;
        }
        else {
            List<String> column = Arrays.asList("distinct course.course_id", "title", "sec_id", "semester", "year", "credits");
            List<Map<String, Object>> course_table = teachesDao.listMap(column, query_1);
            return course_table;
        }
    }

    public void correctGrade(Integer sec_id,String student_id,String grade){
        Query query = new Query();
        query.join(" natural join section ");
        query.eq("sec_id",sec_id);
        query.eq("student_id",student_id);
        Takes takes = takesDao.getByQuery(query);
        takes.setGrade(grade);
        takes.setStatus("scored");
        takesDao.updateByQuery(takes,query);
    }


}
