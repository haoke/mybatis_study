package com.hk.dao;

import java.util.List;

public interface OrderUserListMapper {
    public List findOrderUserDetailListMapper();
    public List findUserOrderDetailItemListMapper();
    public List findOrderUserListLazyLoading();
}
