package cn.edu.jlu.ccst.glzz.system.generated.DAO;

import cn.edu.jlu.ccst.glzz.system.generated.Model.Student;

import com.gitee.fastmybatis.core.mapper.CrudMapper;
import org.apache.ibatis.annotations.*;


/**
 * @author glzz
 */
public interface StudentDao extends CrudMapper<Student, String> {
    @Insert("insert into student (student_id,password,student_name,dept_name,tot_cred)values(#{student_id},#{password},#{student_name},#{dept_name},#{tot_cred})")
    int insert(@Param("student_id") String student_id,@Param("password") String password, @Param("student_name") String student_name, @Param("dept_name") String dept_name, @Param("tot_cred") int tot_cred);
    @Select("select max(student_id) from student")
    int getMax();
    @Select("select student_id from student where password = #{password},student_name = #{student_name},dept_name = #{dept_name}")
    int searchAndReturnID(@Param("password") String password,@Param("student_name") String student_name,@Param("dept_name") String dept_name);
    @Delete("delete from student where student_id = #{student_id}")
    int deleteStudent(@Param("student_id") String student_id);
    @Update("update student set student_name = #{student_name},dept_name = #{dept_name},password = #{password} where student_id = #{student_id}")
    int updateById(@Param("student_id") String student_id, @Param("student_name") String student_name, @Param("dept_name") String dept_name, @Param("password") String password);

}
