package com.hk.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {
    private Integer id;
    private Integer userid;
    private String number;
    private Date createtime;
    private String note;
    private User user;
    private List<OrderDetail> orderDetail;
}
