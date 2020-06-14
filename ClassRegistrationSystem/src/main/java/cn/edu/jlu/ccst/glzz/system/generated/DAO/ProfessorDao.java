package cn.edu.jlu.ccst.glzz.system.generated.DAO;

import cn.edu.jlu.ccst.glzz.system.generated.Model.Professor;

import com.gitee.fastmybatis.core.mapper.CrudMapper;
import org.apache.ibatis.annotations.*;



public interface ProfessorDao extends CrudMapper<Professor, String> {
    @Insert("insert into professor (professor_id,password,professor_name,dept_name)values(#{professor_id},#{password},#{professor_name},#{dept_name})")
    int insert(@Param("professor_id") String professor_id, @Param("password") String password, @Param("professor_name") String professor_name, @Param("dept_name") String dept_name);
    @Select("select max(professor_id) from professor")
    int getMax();
    @Select("select professor_id from professor where password = #{password},professor_name = #{professor_name},dept_name = #{dept_name}")
    int searchAndReturnID(@Param("password") String password,@Param("professor_name") String professor_name,@Param("dept_name") String dept_name);
    @Delete("delete from professor where professor_id = #{professor_id}")
    int deleteProfessor(@Param("professor_id") String professor_id);
    @Update("update professor set professor_name = #{professor_name},dept_name = #{dept_name},password = #{password} where professor_id = #{professor_id}")
    int updateById(@Param("professor_id") String professor_id, @Param("professor_name") String professor_name, @Param("dept_name") String dept_name, @Param("password") String password);

}
