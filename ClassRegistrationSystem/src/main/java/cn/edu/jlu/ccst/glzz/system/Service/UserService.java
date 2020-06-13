package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Model.UserType;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.AdminDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.ProfessorDao;
import cn.edu.jlu.ccst.glzz.system.generated.DAO.StudentDao;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Admin;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Professor;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    StudentDao studentDao;
    @Resource
    ProfessorDao professorDao;
    @Resource
    AdminDao adminDao;

    public boolean havaUser(String user_id,UserType userType){
        switch (userType){
            case Student:
                Student student=studentDao.getByQuery(new Query().eq("student_id",user_id));
                if(student!=null)
                    return true;
                break;
            case Admin:
                Admin admin=adminDao.getByQuery(new Query().eq("admin_id",user_id));
                if(admin!=null)
                    return true;
                break;
            case Professor:
                Professor professor=professorDao.getByQuery(new Query().eq("professor_id",user_id));
                if(professor!=null)
                    return true;
                break;
        }
        return false;
    }

}
