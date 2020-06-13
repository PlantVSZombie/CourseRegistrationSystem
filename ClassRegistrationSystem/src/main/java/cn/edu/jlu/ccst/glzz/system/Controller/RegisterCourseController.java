package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Service.RegisterCourseService;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@RestController
public class RegisterCourseController {
    @Resource
    RegisterCourseService registerCourseService;

    @RequestMapping(value = "/admin/courseControl.json",produces="application/json;charset=UTF-8")
    public Result saveFlow(HttpSession session,Integer year,String semester,String type,String start_date,String end_date) throws IOException {
        System.out.println(year);
        List<Map<String,Object>> flow= registerCourseService.saveFlow(year,semester,type,start_date,end_date);
        System.out.println(flow);
        return Result.ok();
    }
    @RequestMapping(value = "/admin/information.json",produces="application/json;charset=UTF-8")
    public JSONObject getStudentInfo(int limit,int page,HttpSession session,String searchParams) throws IOException {
        JSONObject jsonObject;
//        if(searchParams!=null){
//            jsonObject=JSONObject.parseObject(searchParams);
//        }else {
//            jsonObject=new JSONObject();
//        }
        List<Map<String,Object>> studentInfo= registerCourseService.getStudentInfo(limit,page);
//        System.out.println(studentInfo);

        jsonObject =new JSONObject();
        jsonObject.put("count",studentInfo.size());
        jsonObject.put("msg","");

        jsonObject.put("code",0);
        jsonObject.put("data",studentInfo);


//
//        JsonUtil jsonUtil=new JsonUtil(200,"");
//        jsonUtil.put("count",studentInfo.size());
//        jsonUtil.put("data",studentInfo);
//        jsonUtil.put("code",0);
        System.out.println(jsonObject);

        return jsonObject;
    }







}
