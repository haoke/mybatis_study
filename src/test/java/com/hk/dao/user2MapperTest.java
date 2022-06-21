package com.hk.dao;

import com.hk.pojo.user2Example;
import junit.framework.TestCase;
import org.junit.Test;

public class user2MapperTest extends TestCase {

    @Test
    public void testSelectByExample() {
        user2Example user2Example = new user2Example();
        com.hk.pojo.user2Example.Criteria criteria = user2Example.createCriteria();
        criteria.andIdBetween(1,20);
        
    }

    public void testSelectByPrimaryKey() {
    }
}