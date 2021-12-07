package com.hk.dao;

import com.hk.pojo.User;
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
     * sql: select * from user where username like ? /concat('%'+?+'%')/in
     *
     * @param user  a javabean parameter
     * @return List<user>
     */
    List<User> findUserByNameAndIdInBindPassword(User user);

    /**
     * insert user
     * sql: insert user
     * @param user pojo参数
     */
    void insertUser(User user);

    /**
     * update user
     * @param user  javabean parameter
     */
    void updateUser(User user);

    /**
     * find user by id
     * @param id user id
     * @return user
     */
    User findUserById(Integer id);


    //传入参数的几种情况

    /**
     * 1. 1 个参数的情况
     *      #{}
     *      ${}
     * @param username  search by username
     * @return    List<User>
     */
    List<User> findUserByName(String username);

    /**
     *  2. 两个参数情况
     *      *  #{}:    arg0, arg1...argn /  param1,param2...paramn
     *      *  ${}:    param1,param2...paramn
     * @param username  用户名
     * @param password  密码
     * @return  User
     */
    User findUserByUnameAndPassword(String username, String password);

    /**
     *  3.  参数是 javabean
     * @param user  javabean
     * @return  List<User>
     */
    List<User> findUserByBeanName(User user);

    /**
     * 4. 参数是map, 直接以map的key来获取value
     * @param map  数据包装到Map后，作为参数传入
     * @return  User
     */
    User findUserByIdAndNameMap(Map<String, Object> map);

    /**
     * 5.  传入参数 是Annotation的。 @Param  建议使用此种方式传参 。
     * @param userid 用户ID
     * @param uname 用户名
     * @return  Map
     */
    User findUserByIdAndNameParam(@Param("id") int userid, @Param("username") String uname);

    //6.传入参数是 List或Array ， mybatis仍是将List/Array 放入Map中， List以List为键，Array的 以Array为键


    //数据返回的几种情况
    /**
     * 查询单行数据 返回值包装为Map, 以属性列 为 键， 以属性值 为 值
     * @param id user id
     * @return  map
     */
    Map<String, Object> findUserByIdMap(int id);

    /**
     * //查询多行数据返回Map， 需要用@MapKey 指定Map的Key
     *
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @MapKey("id")
    Map<String, Object> findAllUser();
}
