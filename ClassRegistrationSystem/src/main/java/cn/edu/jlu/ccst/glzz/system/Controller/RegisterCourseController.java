package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Service.RegisterCourseService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
@RestController
public class RegisterCourseController {
    String editting_id;
    @Resource
    RegisterCourseService registerCourseService;

    @RequestMapping(value = "/student/flowstate",produces="application/json;charset=UTF-8")
    public Result getStudentFlowState()  {
        boolean isend=registerCourseService.flowIsEnd("student");
        return Result.ok(isend);
    }

    @RequestMapping(value = "/professor/flowstate",produces="application/json;charset=UTF-8")
    public Result getProfessorFlowState()  {
        boolean isend=registerCourseService.flowIsEnd("professor");
        return Result.ok(isend);
    }


    @RequestMapping(value = "/admin/courseControl.json",produces="application/json;charset=UTF-8")
    public Result saveFlow(HttpSession session,Integer year,String semester,String type,String start_date,String end_date) throws IOException {
//        System.out.println(start_date);
        List<Map<String,Object>> flow= registerCourseService.saveFlow(year,semester,type,start_date,end_date);
        System.out.println(flow);
        return Result.ok();
    }


    @RequestMapping(value = "/admin/endup.json",produces="application/json;charset=UTF-8")
    public Result endFlow(HttpSession session,Integer year,String semester,String type,String start_date,String end_date) throws IOException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date=df.format(new Date());
        registerCourseService.endFlow(type,date);
//        System.out.println(registerCourseService.flowIsEnd("student"));
//        System.out.println(registerCourseService.flowIsEnd("professor"));

        return Result.ok();

    }


    @RequestMapping(value="/admin/add_student.json",produces="application/json;charset=UTF-8")
    public void addStudent(HttpSession session,String student_name,String dept_name,String password){
        registerCourseService.addStudent(student_name,dept_name,password);

    }


    @RequestMapping(value="/admin/add_professor.json",produces="application/json;charset=UTF-8")
    public void addProfessor(HttpSession session,String professor_name,String dept_name,String password){
        registerCourseService.addProfessor(professor_name,dept_name,password);
    }


    @RequestMapping(value="/admin/edit_student.json",produces="application/json;charset=UTF-8")
    public void editStudent(HttpSession session,String student_id,String student_name,String dept_name,String password){
        if(student_id!=null){
            editting_id=student_id;
        }else{
            registerCourseService.editStudent(editting_id,student_name,dept_name,password);
        }
    }


    @RequestMapping(value="/admin/edit_professor.json",produces="application/json;charset=UTF-8")
    public void editProfessor(HttpSession session,String professor_id,String professor_name,String dept_name,String password){
//        System.out.println(professor_id);
        if(professor_id!=null){
            editting_id=professor_id;
        }else{
            registerCourseService.editProfessor(editting_id,professor_name,dept_name,password);
        }
    }


    @RequestMapping(value="/admin/delete_student.json",produces="application/json;charset=UTF-8")
    public void deleteStudent(HttpSession session,String student_id){
        registerCourseService.deleteStudent(student_id);
    }


    @RequestMapping(value="/admin/delete_professor.json",produces="application/json;charset=UTF-8")
    public void deleteProfessor(HttpSession session,String professor_id){
        registerCourseService.deleteProfessor(professor_id);
    }



    @RequestMapping(value = "/admin/information.json",produces="application/json;charset=UTF-8")
    public JSONObject getStudentInfo(int limit,int page,String searchParams){


        System.out.println(searchParams);
        JSONObject jsonObject;
        if(searchParams!=null){
            jsonObject=JSONObject.parseObject(searchParams);
        }else {
            jsonObject=new JSONObject();
        }

        System.out.println(jsonObject);
        List<Map<String,Object>> studentInfo= registerCourseService.getStudentInfo(jsonObject.getString("student_id"),jsonObject.getString("student_name"),jsonObject.getString("dept_name"),limit,page);
        System.out.println(studentInfo);


        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",registerCourseService.getStudentInfoCount(jsonObject.getString("student_id"),jsonObject.getString("student_name"),jsonObject.getString("dept_name")));
        jsonUtil.put("data",studentInfo);
        jsonUtil.put("code",0);
        System.out.println(jsonObject);
        return jsonUtil.getJsonObject();
    }



    @RequestMapping(value = "/admin/professor_information.json",produces="application/json;charset=UTF-8")
    public JSONObject getProfessorInfo(int limit,int page,String searchParams){
        System.out.println(searchParams);
        JSONObject jsonObject;
        if(searchParams!=null){
            jsonObject=JSONObject.parseObject(searchParams);
        }else {
            jsonObject=new JSONObject();
        }

        List<Map<String,Object>> professorInfo= registerCourseService.getProfessorInfo(jsonObject.getString("professor_id"),jsonObject.getString("professor_name"),jsonObject.getString("dept_name"),limit,page);

        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",registerCourseService.getProfessorInfoCount(jsonObject.getString("professor_id"),jsonObject.getString("professor_name"),jsonObject.getString("dept_name")));
        jsonUtil.put("data",professorInfo);
        jsonUtil.put("code",0);
        return jsonUtil.getJsonObject();

    }







}
