package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.generated.DAO.FlowControlDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.ProfessorDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.StudentDao;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import com.gitee.fastmybatis.core.query.Query;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class RegisterCourseService {
    @Resource
    FlowControlDao flowControlDao;
    @Resource
    StudentDao studentDao;
    @Resource
    ProfessorDao professorDao;



    public List<Map<String,Object>> saveFlow(Integer year,String semester,String type,String start_date,String end_date){
        System.out.println(type);
        Query query=new Query();
        String flow_name=year+semester;
        query.eq("type",type);
        query.eq("flow_name",flow_name);
        List<String> column = Arrays.asList("flow_id","type","flow_name","start_datetime","end_datetime");
        List<Map<String,Object>> control_flow=flowControlDao.listMap(column,query);
        if(control_flow.size()>0){
            flowControlDao.updateById(Integer.parseInt(control_flow.get(0).get("flow_id").toString()),type,semester,start_date,end_date);
        }else{
            flowControlDao.insert(flowControlDao.getMax()+1,type,flow_name,start_date,end_date);
        }
        return control_flow;

    }
    public Query getStudentInfoQuery(String student_id,String student_name,String dept_name){
        Query query=new Query();

        if(student_id!=null&&!student_id.equals("")){
            query.eq("student_id",student_id);
        }
        if(student_name!=null&&!student_name.equals("")){
            query.eq("student_name",student_name);
        }
        if(dept_name!=null&&!dept_name.equals("")){
            query.eq("dept_name",dept_name);
        }

        query.join("natural join (select student_id,sum(ifnull(cost,20)) as cost from takes natural join section natural join course group by student_id) t2");
        return query;
    }
    public List<Map<String,Object>> getStudentInfo(String student_id,String student_name,String dept_name,int limit,int page){
        Query query=getStudentInfoQuery(student_id,student_name,dept_name);
        query.page(page,limit);
        List<String> column = Arrays.asList("student_id","student_name","dept_name","cost");
        List<Map<String,Object>> studentinfo=studentDao.listMap(column,query);
        return studentinfo;
    }
    public long getStudentInfoCount(String student_id,String student_name,String dept_name){
        return studentDao.getCount(getStudentInfoQuery(student_id,student_name,dept_name));
    }





    public Query getProfessorInfoQuery(String professor_id,String professor_name,String dept_name){
        Query query=new Query();

        if(professor_id!=null&&!professor_id.equals("")){
            query.eq("professor_id",professor_id);
        }
        if(professor_name!=null&&!professor_name.equals("")){
            query.eq("professor_name",professor_name);
        }
        if(dept_name!=null&&!dept_name.equals("")){
            query.eq("dept_name",dept_name);
        }
        return query;
    }
    public List<Map<String,Object>> getProfessorInfo(String professor_id,String professor_name,String dept_name,int limit,int page){
        Query query=getProfessorInfoQuery(professor_id,professor_name,dept_name);
        query.page(page,limit);
        List<String> column = Arrays.asList("professor_id","professor_name","dept_name");
        List<Map<String,Object>> professorinfo=professorDao.listMap(column,query);
        return professorinfo;
    }

    public long getProfessorInfoCount(String professor_id,String professor_name,String dept_name){
        return professorDao.getCount(getProfessorInfoQuery(professor_id,professor_name,dept_name));
    }



    public void addStudent(String student_name,String dept_name,String password){
        int id=studentDao.getMax()+1;
        studentDao.insert(id+"",password,student_name,dept_name,0);
    }
    public void addProfessor(String professor_name,String dept_name,String password){
        int id=professorDao.getMax()+1;
        professorDao.insert(id+"",password,professor_name,dept_name);
    }


    public void editStudent(String student_id,String student_name,String dept_name,String password){

        studentDao.updateById(student_id,student_name,dept_name,password);

    }
    public void editProfessor(String professor_id,String professor_name,String dept_name,String password){
        System.out.println("edit_id"+professor_id);
        professorDao.updateById(professor_id,professor_name,dept_name,password);

    }


    public void deleteStudent(String student_id){
        studentDao.deleteStudent(student_id);
    }
    public void deleteProfessor(String professor_id){
        professorDao.deleteProfessor(professor_id);
    }
}
