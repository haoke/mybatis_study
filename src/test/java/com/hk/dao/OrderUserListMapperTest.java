package com.hk.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrderUserListMapperTest {
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        InputStream inputstream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
    }

    @Test
    public void testFindOrderUserDetailListMapper(){
        sqlSession = sqlSessionFactory.openSession();
        OrderUserListMapper mapper = sqlSession.getMapper(OrderUserListMapper.class);
        List userList = mapper.findOrderUserDetailListMapper();
        userList.stream().forEach(System.out::println);
    }

    @Test
    public void testFindUserOrderDetailItemListMapper(){
        sqlSession = sqlSessionFactory.openSession();
        OrderUserListMapper mapper = sqlSession.getMapper(OrderUserListMapper.class);
        List userList = mapper.findUserOrderDetailItemListMapper();
        userList.stream().forEach(System.out::println);
    }

    @Test
    public void testFindOrderUserListLazyLoading(){
        sqlSession = sqlSessionFactory.openSession();
        OrderUserListMapper mapper = sqlSession.getMapper(OrderUserListMapper.class);
        List orderList = mapper.findOrderUserListLazyLoading();
        orderList.stream().forEach(System.out::println);

    }

}