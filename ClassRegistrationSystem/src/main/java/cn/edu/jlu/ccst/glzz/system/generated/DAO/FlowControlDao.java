package cn.edu.jlu.ccst.glzz.system.generated.DAO;

import cn.edu.jlu.ccst.glzz.system.generated.Model.FlowControl;

import com.gitee.fastmybatis.core.mapper.CrudMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * @author glzz
 */
public interface FlowControlDao extends CrudMapper<FlowControl, Integer> {
    @Update("update flow_control set type = #{type},flow_name = #{flow_name},start_datetime = #{start_datetime},end_datetime = #{end_datetime} where flow_id = #{flow_id}")
    int updateById(@Param("flow_id") int flow_id, @Param("type") String type, @Param("flow_name") String flow_name, @Param("start_datetime") String start_datetime, @Param("end_datetime") String end_datetime);
    @Insert("insert into flow_control (flow_id,type,flow_name,start_datetime,end_datetime)values(#{flow_id},#{type},#{flow_name},#{start_datetime},#{end_datetime})")
    int insert(@Param("flow_id") int flow_id,@Param("type") String type,@Param("flow_name") String flow_name,@Param("start_datetime") String start_datetime,@Param("end_datetime") String end_datetime);
    @Select("select max(flow_id) from flow_control")
    int getMax();
}
