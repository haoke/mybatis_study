package com.hk.dao;

import java.util.List;

public interface OrderUserListMapper {
    /**
     * order user orderDetail   union search
     * @return
     */
    public List findOrderUserDetailListMapper();
    public List findUserOrderDetailItemListMapper();
    public List findOrderUserListLazyLoading();
}
