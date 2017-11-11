package com.wanghao.service;

import com.wanghao.domain.Clients;
import com.wanghao.domain.PageBean;

public interface OrdersService {
	PageBean<Clients> findOrders(PageBean<Clients> pageBean);
}
