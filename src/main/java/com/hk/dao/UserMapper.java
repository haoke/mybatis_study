package com.hk.dao;

import com.hk.pojo.User;
import com.hk.pojo.UserQueryVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author haoke
 */
public interface UserMapper {

    /**
     * 使用bind 和 like concat('%'+?+'%')模糊查询拼装, in 多项查询,
     * sql: select * from user where username like ?  /  like concat('%'+?+'%')
     * @param user  pojo参数
     * @return user
     */
      List<User> findUserByNameAndIdInBindPassword(User user);

    /**
     * insert user
     * sql: insert user
     * @param user pojo参数
     */
     void insertUser(User user);

    User findUserById(Integer  id);

    List<User> findUserByBeanName(User user);

    //1. 1 个参数
    List<User> findUserByName(String username);

    //  两个参数
    User findUserByUnameAndPassword(String username, String password);

    //4. 参数是map
    User findUserByIdAndNameMap(Map<String, Object> map);

    //5.  传入参数 是Annotation的。 @Param    建议此种方式 。
    Map<String, Object> findUserByIdAndNameParam(@Param("id")int userid, @Param("username") String uname);

    //6.传入参数是 List或Array ， mybatis仍是将List/Array 放入Map中， List以List为键，Array的 以Array为键

    /**
     *  查询单行数据 返回值为 Map, 以属性列 为 键， 以属性值 为 值
     * @param id
     * @description xx
     * @return
     */
    Map<String,Object> findUserByIdMap(int id);

    /**
     * //查询多行数据返回Map， 需要用@MapKey 指定Map的Key
     *
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @MapKey("id")
    Map<String, Object> findAllUser();
}
