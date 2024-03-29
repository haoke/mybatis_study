package com.hk.dao;

import com.hk.plugins.Sex;
import com.hk.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
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

    //查
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
        user.setSex(Sex.MALE);
        List<User> userList = mapper.findUserByNameAndIdInBindPassword(user);  //TODO 必须使用范型  否则会报错


        userList.stream().forEach(System.out::println);

       /* for(User user1 : userList){
            System.out.println(user1);
        }*/
    }

    //增
    @Test
    public void testInsertUser() throws Exception{
        sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH); // 批量操作，速度比foreach更快
        mapper = sqlSession.getMapper(UserMapper.class);

        User user =  new User();
        user.setUsername("赵一本");
        user.setPassword("999999999");
        mapper.insertUser(user);
        System.out.println(user.getId());

        sqlSession.commit();
        sqlSession.close();
    }

    //改
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

        User user1 = new User("Blus","123",Sex.FEMALE,new Date());
        User user2 = new User("clinton","222",Sex.MALE,new Date());
        User user3 = new User("Tomclus", "111", Sex.MALE, new Date());
        User[] users = new User[] {user1, user2, user3};
        mapper.insertUserByArray(users);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testUpdateUserByArray(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = new User(3, "ironman1","1112",Sex.MALE);
        User user2 = new User(7,"ironman2","222",Sex.FEMALE);
        User user3 =new User(10,"ironman3","444",Sex.FEMALE);

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
        System.out.println(userMap);
        if (userMap != null) {
            for (Map.Entry<String, Object> entry : userMap.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
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

        Map<String, Object> allUser = mapper.findAllUser();

        System.out.println(allUser);
        /*for(Object u: allUser.values()){
            System.out.println(u);
        }*/

    }

    //调用存储过程
    @Test
    public void  getAllUserByProcedure(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List allUserByProcedure = mapper.getAllUserByProcedure(1, 1000);
        System.out.println(allUserByProcedure);
    }

    @Test
    public void logtest(){

        logger.info("----------普通信息打印级别------------");
        logger.debug("----------调试打印级别------------");
        logger.error("----------错误查看打印级别------------");
    }

    @Test
    public void findUserByListOrArray(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List idList = new ArrayList();
        idList.add(1); idList.add(3); idList.add(7);
        List<User> userByListOrArray = mapper.findUserByListOrArray(idList);
        System.out.println(userByListOrArray);
    }

}