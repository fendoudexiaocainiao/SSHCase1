package com.wanghao.service;

import com.wanghao.domain.Orders;
import com.wanghao.domain.PageBean;

import java.util.List;

public interface OrdersService {
	List<Orders> findOrders(String cId, PageBean pageBean);
}
