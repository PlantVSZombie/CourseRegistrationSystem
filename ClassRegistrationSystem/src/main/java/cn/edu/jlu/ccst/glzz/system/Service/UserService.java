package cn.edu.jlu.ccst.glzz.system.Service;



import cn.edu.jlu.ccst.glzz.system.generated.DAO.StudentDao;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;
import com.gitee.fastmybatis.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    StudentDao studentDao;

    public List<Student> getLine(){
        return studentDao.list(new Query());
    }
}
