package cn.edu.jlu.ccst.glzz.system.generated.DAO;

import cn.edu.jlu.ccst.glzz.system.generated.Model.Takes;

import com.gitee.fastmybatis.core.mapper.CrudMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


public interface TakesDao extends CrudMapper<Takes, String> {
    @Select("select class_id,count(*) as num from (select * from takes where ismajor = 1) as a group by class_id")
    List<Map<String,Object>> getGroup();
    @Delete("delete from takes where class_id = #{class_id}")
    int deleteTakesItem(@Param("class_id") String class_id);
    @Select("select student_id,count(*) as num from takes group by student_id")
    List<Map<String,Object>> getStudentNumList();
    @Update("update takes set ismajor = 1 where student_id = #{student_id}")
    int addBeiXuan(@Param("student_id") String student_id );
    @Select("select student_id from takes where class_id = #{class_id}")
    List<String> getdeletedStudent(@Param("class_id") String class_id );





}
