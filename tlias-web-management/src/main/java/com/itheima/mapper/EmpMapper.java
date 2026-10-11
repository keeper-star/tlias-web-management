package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */

@Mapper
public interface EmpMapper {

    // -----------------------------------原始分页查询------------------------------------------------
    /**
     * 查询总记录数
     */
//   @Select("select count(*) from emp e left join dept d on e.dept_id = d.id ")
//   public long count();

    /**
     * 查询分页数据
     */
//   @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//           "order by e.update_time desc limit #{start},#{pageSize}")
//   public List<Emp> list(Integer start, Integer pageSize);


       //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//       public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 根据条件查询员工列表
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 添加员工
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的主键 -- 主键返回
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "      values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
    * 根据ids删除员工基本信息
    */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息以及工作经历信息
     */
    Emp getById(Integer id);

    /**
     * 根据id更新员工基本信息
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     * @return
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData(); //List<Map<String, Object>>用map类型封装员工职位人数数据

    /**
     * 统计员工性别人数
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 查询所有员工信息
     */
    List<Emp> getList();

    /**
     * 根据用户名和密码查询员工信息
     * @param emp
     * @return
     */
    Emp selectByUsernameAndPassword(Emp emp);
}
