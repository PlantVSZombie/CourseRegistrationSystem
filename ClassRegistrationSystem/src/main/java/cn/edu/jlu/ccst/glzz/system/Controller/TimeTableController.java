package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.TimeTableService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class TimeTableController {
    @Resource
    TimeTableService timeTableService;

    @RequestMapping(value = "/student/timetable.json",produces="application/json;charset=UTF-8")
    public JSONObject getTimeTable( HttpSession session)  {
        User user=(User)session.getAttribute("user");
        Student student=(Student)user.getPerson();
        JSONObject jsonObject;

        List<Map<String,Object>> timeTable=timeTableService.getTimeTable(student.getStudentId());


        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",timeTable.size());
        jsonUtil.put("data",timeTable);
        jsonUtil.put("code",0);

        return jsonUtil.getJsonObject();
    }
}
