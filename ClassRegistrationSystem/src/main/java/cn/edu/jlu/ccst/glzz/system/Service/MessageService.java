package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Model.UserType;
import cn.edu.jlu.ccst.glzz.system.Util.ReadFileUtil;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.AdminDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.MessageDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.ProfessorDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.StudentDao;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Admin;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Message;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Professor;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {
    @Resource
    MessageDao messageDao;
    @Resource
    UserService userService;

    /**
     * 向指定用户发送信息
     * @param user_id
     * @param userType 枚举类型，UserType。xx
     * @param messageStr 消息内容
     * @return 发送是否成功，当用户不存在时返回false
     */
    public boolean sendMessage(String user_id, UserType userType,String messageStr){
        //判断用户是否存在
        if(!userService.havaUser(user_id,userType))
            return false;
        //创建消息
        Message message=new Message();
        message.setUserId(user_id);
        message.setContext(messageStr);
        switch (userType){
            case Student:
                message.setType("student");
                break;
            case Admin:
                message.setType("admin");
                break;
            case Professor:
                message.setType("professor");
                break;
        }
        message.setHasread(0);
        //保存到数据库
        messageDao.save(message);
        return true;
    }

    /**
     * 设置消息已读
     * @param user 判断是否是该用户的信息，防止越权操作
     * @param message_id
     */
    public void setHasRead(User user,int message_id){
        Query query=new Query();

        switch (user.getUserType()){
            case Student:
                Student student=(Student)user.getPerson();
                query.eq("user_id",student.getStudentId());
                query.eq("type","student");
                break;
            case Admin:
                Admin admin=(Admin) user.getPerson();
                query.eq("user_id",admin.getAdminId());
                query.eq("type","admin");
                break;
            case Professor:
                Professor professor=(Professor) user.getPerson();
                query.eq("user_id",professor.getProfessorId());
                query.eq("type","professor");

                break;
        }
        query.eq("message_id",message_id);
        Message message=messageDao.getByQuery(query);
        message.setHasread(1);
        messageDao.update(message);

    }


    public  long getMessageCount(User user){
        Query query=new Query();

        switch (user.getUserType()){
            case Student:
                Student student=(Student)user.getPerson();
                query.eq("user_id",student.getStudentId());
                query.eq("type","student");
                break;
            case Admin:
                Admin admin=(Admin) user.getPerson();
                query.eq("user_id",admin.getAdminId());
                query.eq("type","admin");
                break;

            case Professor:
                Professor professor=(Professor) user.getPerson();
                query.eq("user_id",professor.getProfessorId());
                query.eq("type","professor");
                break;

        }
        return messageDao.getCount(query);
    }
    public List<Message> getMessages(User user,int limit,int page){
        Query query=new Query();

        switch (user.getUserType()){
            case Student:
                Student student=(Student)user.getPerson();
                query.eq("user_id",student.getStudentId());
                query.eq("type","student");
                break;
            case Admin:
                Admin admin=(Admin) user.getPerson();
                query.eq("user_id",admin.getAdminId());
                query.eq("type","admin");
                break;

            case Professor:
                Professor professor=(Professor) user.getPerson();
                query.eq("user_id",professor.getProfessorId());
                query.eq("type","professor");
                break;

        }
        query.page(page,limit);
        return messageDao.list(query);
    }
}
