package com.hk.dao;

import com.hk.pojo.Order;
import com.hk.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class UserMapperTest {
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    UserMapper mapper;

    private static Logger logger = Logger.getLogger(UserMapper.class);
    @Before
    public void init() throws IOException {
        InputStream inputstream = Resources.getResourceAsStream("SqlMapConfig.xml");
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
    }

    @Test
    public void testfindUserByNameAndIdInBindPassword() throws Exception{
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        List ids = new ArrayList();
        ids.add(11);ids.add(12);ids.add(13);ids.add(14);ids.add(15);ids.add(16);ids.add(17);
        User user =  new User();
        user.setUsername("%h%");
        user.setIds(ids);
        user.setPassword("1");
        user.setSex("m");
        List<User> userList = mapper.findUserByNameAndIdInBindPassword(user);  //TODO 必须使用范型  否则会报错



        userList.stream().forEach(System.out::println);

       /* for(User user1 : userList){
            System.out.println(user1);
        }*/
    }

    @Test
    public void testInsertUser() throws Exception{
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        User user =  new User();
        user.setUsername("赵一本");
        user.setPassword("999999999");
        mapper.insertUser(user);
        System.out.println(user.getId());

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateUser(){
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setId(1);
        user.setUsername("hulk_modify");
        mapper.updateUser(user);
        System.out.println(mapper.findUserById(1));

        sqlSession.commit();
        sqlSession.close();
    }
 /*   @Test
    public void testFindUserByNameFromUserQueryVo(){
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        UserQueryVo userQueryVo = new UserQueryVo();
        User user = new User();
        user.setUsername("%hk%");
        userQueryVo.setUser(user);

        List<User> userList = mapper.findUserByNameFromUserQueryVo(userQueryVo);
        userList.forEach(System.out::println);
    }*/

    @Test
    public void testFindUserById(){
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);
        User u =  mapper.findUserById(4);
        System.out.println(u);
    }


    /**
     * 1. 1个参数
     */
    @Test
    public void testFindUserByName(){
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        List u =  mapper.findUserByName("hhk1");
        u.stream().forEach(System.out::println);
    }
    //2. 多个参数

    @Test
    public void testFindUserByUnameAndPassword(){
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.findUserByUnameAndPassword("Bill", "0000");
        System.out.println(u);
    }

    // 3. 参数以JavaBean传入
    @Test
    public void testFindUserByBeanName(){
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User("hhk1");
        List uList =  mapper.findUserByBeanName(user);
        uList.stream().forEach(System.out::println);
    }

    //4. 参数以map传入
    @Test
    public void testFindUserByIdAndNameMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idmap",7);
        map.put("usernameMap", "hhk1");
        User u = mapper.findUserByIdAndNameMap(map);
        System.out.println(u);

    }

    //5. 参数以 @Param 传入
    @Test
    public void testFindUserByIdAndNameParam(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User  user = (User) mapper.findUserByIdAndNameParam(57, "hhkk");
        System.out.println(user);
    }

    //6. 参数为List或Array的，包装为Map，List或Array做为Key


    //批量操作
    @Test
    public void testDeleteMore(){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        String ids= "1,2,3";
        mapper.deleteMore(ids);
    }

    @Test
    public void testDeleteMore2(){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List ids = new ArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(4);
        mapper.deleteMore2(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertUserByArray(){
       sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = new User("Blus","123","2",new Date());
        User user2 = new User("clinton","222","1",new Date());
        User user3 = new User("Tomclus", "111", "1", new Date());
        User[] users = new User[] {user1, user2, user3};
        mapper.insertUserByArray(users);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testUpdateUserByArray(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = new User(3, "ironman1","1112","1");
        User user2 = new User(7,"ironman2","222","0");
        User user3 =new User(10,"ironman3","444","2");

        User[] users = new User[] {user1, user2,user3};

        mapper.updateUserByArray(users);

        sqlSession.commit();
        sqlSession.close();
    }




    //数据返回的几种情况
    // 单行数据  return map
    @Test
    public void testFindUserByIdMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> userMap = mapper.findUserByIdMap(3);
        System.out.println("================================");
       /* if (userMap != null) {
            for (Map.Entry<String, Object> entry : userMap.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }*/
        Iterator<Map.Entry<String, Object>> it = userMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            System.out.println(entry.getKey() +": "+ entry.getValue());
        }

    }

    //多行数据  return Map
    @Test
    public void testFindAllUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> allUser = mapper.findAllUser();         //TODO @Mapkey不熟悉，需要了解一下
        for(Object u: allUser.values()){
            System.out.println(u);
        }

    }

    @Test
    public void logtest(){

        logger.info("----------普通信息打印级别------------");
        logger.debug("----------调试打印级别------------");
        logger.error("----------错误查看打印级别------------");
    }

}