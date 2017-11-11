package com.wanghao.dao;

import com.wanghao.domain.Clients;
import com.wanghao.domain.PageBean; /**
 * ${DESCRIPTION}
 *
 * @author Administrator
 * @create 2017-11-10-10:09
 */
public interface OrdersDao {
	PageBean<Clients> findOrders(PageBean<Clients> pageBean);
}
