package com.wanghao.web.action;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.wanghao.domain.Clients;
import com.wanghao.domain.Orders;
import com.wanghao.domain.PageBean;
import com.wanghao.service.OrdersService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("ordersAction")
@Scope("prototype")
@Namespace("/orders")
@ParentPackage("struts-default")
public class OrdersAction extends ActionSupport{
	@Autowired
	private OrdersService ordersService = null;

	@Action(value = "findOrders")
	public void findOrders(){
		HttpServletRequest request = ServletActionContext.getRequest();
		PageBean<Clients> pageBean = new PageBean<>();
		pageBean.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		pageBean.setCurrentRecords(Integer.parseInt(request.getParameter("currentCount")));
		pageBean.setTarget(new Clients());
		pageBean.getTarget().setcId(request.getParameter("cId"));

		ordersService.findOrders(pageBean);
		String s = JSONObject.toJSONString(pageBean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
