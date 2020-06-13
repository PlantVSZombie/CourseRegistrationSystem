package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.generated.DAO.FlowControlDao;
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
    public List<Map<String,Object>> getStudentInfo(int limit,int page){
        Query query=new Query();
        query.page(page,limit);

        List<String> column = Arrays.asList("student_id","student_name","dept_name");
        List<Map<String,Object>> studentInfo=studentDao.listMap(column,query);
        return studentInfo;
    }
}
