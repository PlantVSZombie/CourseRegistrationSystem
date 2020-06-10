package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Model.User;
import cn.edu.jlu.ccst.glzz.system.Model.UserType;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.AdminDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.ProfessorDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.StudentDao;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Admin;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Professor;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.alibaba.fastjson.JSONObject;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {
    @Resource
    StudentDao studentDao;
    @Resource
    ProfessorDao professorDao;
    @Resource
    AdminDao adminDao;

    public User login(String userid,String password,String usertype){
        User user=new User();
        switch (usertype){
            case "student":
                Student student=studentDao.getByQuery(new Query().eq("student_id",userid).eq("password",password));
                if (student==null){
                    return null;
                }
                user.setPerson(student);
                user.setUserType(UserType.Student);
                break;
            case "professor":
                Professor professor=professorDao.getByQuery(new Query().eq("professor_id",userid).eq("password",password));
                if (professor==null){
                    return null;
                }
                user.setPerson(professor);
                user.setUserType(UserType.Professor);
                break;
            case "admin":
                Admin admin=adminDao.getByQuery(new Query().eq("admin_id",userid).eq("password",password));
                if (admin==null){
                    return null;
                }
                user.setPerson(admin);
                user.setUserType(UserType.Admin);
                break;
            default:
        }

        return user;
    }

}
