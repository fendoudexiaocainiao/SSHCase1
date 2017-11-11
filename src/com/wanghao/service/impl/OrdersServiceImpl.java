package com.wanghao.service.impl;

import com.wanghao.dao.OrdersDao;
import com.wanghao.domain.Orders;
import com.wanghao.domain.PageBean;
import com.wanghao.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersDao ordersDao = null;

	@Override
	public List<Orders> findOrders(String cId, PageBean pageBean) {
		return null;
	}
}
