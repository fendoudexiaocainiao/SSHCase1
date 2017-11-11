package com.wanghao.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wanghao.domain.Clients;
import com.wanghao.service.ClientsService;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller(value = "clientsAction")
@Scope(value = "prototype")
@Namespace(value = "/clients")
@ParentPackage(value = "struts-default")
public class ClientsAction extends ActionSupport implements ModelDriven<Clients> {
	private Clients clients = new Clients();

	private File cImg = null;
	private String cImgContentType = null;
	private String cImgFileName = null;

	public File getcImg() {
		return cImg;
	}

	public void setcImg(File cImg) {
		this.cImg = cImg;
	}

	public String getcImgContentType() {
		return cImgContentType;
	}

	public void setcImgContentType(String cImgContentType) {
		this.cImgContentType = cImgContentType;
	}

	public String getcImgFileName() {
		return cImgFileName;
	}

	public void setcImgFileName(String cImgFileName) {
		this.cImgFileName = cImgFileName;
	}

	@Autowired
	@Qualifier(value = "clientsService")
	private ClientsService clientsService = null;

	@Override
	public Clients getModel() {
		return clients;
	}

	@Action(value = "findAll", results = {@Result(name = SUCCESS, location = "/customerList.jsp"), @Result(name = ERROR, location = "/error.jsp")})
	public String findAll() {
		List<Clients> clientsList = clientsService.findAll();
		ActionContext.getContext().getValueStack().set("clientList", clientsList);
		return SUCCESS;
	}

	@Action(value = "addClient", results = {@Result(name = SUCCESS, type = "redirect", location = "/clients/findAll"), @Result(name = ERROR, location = "/error.jsp")})
	public String addClient() {
		try {
			String realPath = ServletActionContext.getServletContext().getRealPath("/upload")+"/"+cImgFileName;
			File file = new File(realPath);
			FileUtils.copyFile(cImg, file);
			clients.setcImgsrc(realPath.substring(realPath.lastIndexOf("upload")-1).replace("\\","/"));
			clientsService.addClients(clients);
		} catch (IOException e) {
			addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	@Action(value = "delClients",results = {@Result(name = SUCCESS, type = "redirect", location = "/clients/findAll"),@Result(name = ERROR, location = "/error.jsp")})
	public String delClients(){
		clientsService.delClients(clients.getcId());
		return SUCCESS;
	}
}
