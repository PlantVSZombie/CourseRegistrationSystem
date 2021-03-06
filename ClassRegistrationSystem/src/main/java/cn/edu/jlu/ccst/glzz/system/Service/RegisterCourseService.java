package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Model.UserType;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.FlowControlDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.ProfessorDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.StudentDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.TakesDao;
import org.apache.ibatis.annotations.Result;
import cn.edu.jlu.ccst.glzz.system.generated.Model.FlowControl;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import com.gitee.fastmybatis.core.query.Query;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RegisterCourseService {
    @Resource
    MessageService messageService;
    @Resource
    FlowControlDao flowControlDao;
    @Resource
    StudentDao studentDao;
    @Resource
    ProfessorDao professorDao;
    @Resource
    TakesDao takesDao ;
    public void selectBeiXuan(){
        List<Map<String,Object>> StudentNumList=takesDao.getStudentNumList();
        for(int i=0;i<StudentNumList.size();i++){
            Integer num=Integer.parseInt(String.valueOf(StudentNumList.get(i).get("num")));
            if(num<4){
                takesDao.addBeiXuan(StudentNumList.get(i).get("student_id").toString());
            }
        }

    }
    public void deleteUnQuaClass(){
        List<String> deleteList=new ArrayList<>();
        List<Map<String,Object>> course_group=takesDao.getGroup();
        for(int i=0;i<course_group.size();i++){

            Integer num=Integer.parseInt(String.valueOf(course_group.get(i).get("num")));
            if(num<3){
                deleteList.add(course_group.get(i).get("class_id").toString());
            }
        }
        System.out.println(deleteList);
        for(int i=0;i<deleteList.size();i++){
            String class_id=deleteList.get(i);
            List<String> stuList=takesDao.getdeletedStudent(class_id);
            for(int j=0;j<stuList.size();j++){
                String s_id=stuList.get(i);
                messageService.sendMessage(s_id, UserType.Student,"你的课程class_id:"+class_id+"已被取消");

            }


            takesDao.deleteTakesItem(class_id);

        }
    }





    public void endFlow(String type,String end_date){
        flowControlDao.updateByType(type,end_date);

    }
    public Boolean flowIsEnd(String type){
        System.out.println(type);
        Query query=new Query();
        query.eq("type",type).addSort("end_datetime");
        List<FlowControl> flowControls=flowControlDao.list(query);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        int i=flowControls.get(flowControls.size()-1).getEndDatetime().compareTo(new Date());
        System.out.println(i);
        if(i<0){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    public FlowControl getFlowControl(String type){
         return flowControlDao.getByQuery(new Query().eq("type",type));
    }



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

        query.join("left join student_cost t2 on t.student_id=t2.student_id");
        return query;
    }
    public List<Map<String,Object>> getStudentInfo(String student_id,String student_name,String dept_name,int limit,int page){

        Query query=getStudentInfoQuery(student_id,student_name,dept_name);
        query.page(page,limit);
        List<String> column = Arrays.asList("t.student_id","student_name","dept_name","ifnull(cost,0) as cost");
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
