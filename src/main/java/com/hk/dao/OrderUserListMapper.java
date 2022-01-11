package com.hk.dao;

import com.hk.pojo.Order;
import com.hk.pojo.User;

import java.util.List;

public interface OrderUserListMapper {
    /**
     *  多对一 order->user->orderDetail
     * @return
     */
    public List<Order> findOrderUserDetailListMapper();

    /**
     * 一对多 user->order->orderdetail->item
     */
    public List<User> findUserOrderDetailItemListMapper();

    /**
     * 多对一 分步查询 且 延迟加载
     * @return
     */
    public List<Order> findOrderUserListLazyLoading();

    /**
     * 一对多分步查询
     */
    public User findUserOrder(Integer id);


}
