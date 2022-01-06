package com.hk.dao;

import com.hk.pojo.User;

import java.util.List;

public interface OrderUserListMapper {
    /**
     * order user orderDetail   union search
     * @return
     */
    public List findOrderUserDetailListMapper();
    public List findUserOrderDetailItemListMapper();

    /**
     * 多对一 分步查询 且 延迟加载
     * @return
     */
    public List findOrderUserListLazyLoading();

    /**
     * 一对多分步查询
     */
    public User findUserOrder(Integer id);


}
