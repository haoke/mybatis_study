package com.hk.dao;

import com.hk.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserMapperTest {
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    UserMapper mapper;

    @Before
    public void init() throws IOException {
        InputStream inputstream = Resources.getResourceAsStream("SqlMapConfig.xml");
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);

    }

    @Test
    public void testFindUserById() throws Exception{
         sqlSession = sqlSessionFactory.openSession();
         mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(4);
        System.out.println(user);
    }

    @Test
    public void findUserByNameAndIdIn() throws Exception{
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        List ids = new ArrayList();
        ids.add(11);ids.add(12);ids.add(13);ids.add(14);ids.add(15);ids.add(16);ids.add(17);
        User user =  new User();
        user.setUsername("%h%");
        user.setIds(ids);
        user.setPassword("1");
        List<User> userList = mapper.findUserByNameAndIdIn(user);  //TODO 必须使用范型  否则会报错

        for(User user1 : userList){
            System.out.println(user1);
        }
    }

    @Test
    public void testFindUserByName2() throws Exception{
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        User user =  new User();
        user.setUsername("王");
        List<User> userList = mapper.findUserByName2(user);

        for(User user1 : userList){
            System.out.println(user1);
        }
    }

    @Test
    public void testFindUserByNameUseBind() throws Exception{
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        User user =  new User();
        user.setUsername("王");
        List<User> userList = mapper.findUserByNameUseBind(user);

        for(User user1 : userList){
            System.out.println(user1);
        }
    }

    @Test
    public void testInsertUser() throws Exception{
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        User user =  new User();
        user.setUsername("赵六");
        user.setPassword("123123");
        mapper.insertUser(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateUser() throws Exception{
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        User user =  new User();
        user.setId(52);
        user.setUsername("张三+李四");
        mapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    public void testDeleteUser(){
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteUser(2);
        sqlSession.commit();
        sqlSession.close();
    }


}