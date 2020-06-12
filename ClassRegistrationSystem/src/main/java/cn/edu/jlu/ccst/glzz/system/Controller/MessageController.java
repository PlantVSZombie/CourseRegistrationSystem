package cn.edu.jlu.ccst.glzz.system.Controller;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Service.MessageService;
import cn.edu.jlu.ccst.glzz.system.Util.JsonUtil;
import cn.edu.jlu.ccst.glzz.system.Util.Result;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Message;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MessageController {
    @Resource
    MessageService messageService;

    @RequestMapping(value = {"/student/messages.json","/professor/messages.json","/admin/messages.json"},produces="application/json;charset=UTF-8")
    public JSONObject getMessages(HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Message> messages=messageService.getMessages(user);

        JsonUtil jsonUtil=new JsonUtil(200,"");
        jsonUtil.put("count",messages.size());
        jsonUtil.put("data",messages);
        jsonUtil.put("code",0);

        return jsonUtil.getJsonObject();
    }

    @RequestMapping(value = {"/student/set_has_read","/professor/set_has_read","/admin/set_has_read"},produces="application/json;charset=UTF-8")
    public Result setHasRead(HttpSession session,int message_id){
        User user=(User)session.getAttribute("user");
        messageService.setHasRead(user,message_id);
        return Result.ok();

    }
}
