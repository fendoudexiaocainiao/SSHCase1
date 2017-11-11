package com.wanghao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients", schema = "sshcase", catalog = "sshcase")
public class Clients {
	@Id
	@Column(name = "c_id")
	@GenericGenerator(name = "cuuid", strategy = "uuid")
	@GeneratedValue(generator = "cuuid")
	private String cId = null;
	@Column(name = "c_name")
	private String cName = null;
	@Column(name = "c_phone")
	private String cPhone = null;
	@Column(name = "c_imgsrc")
	private String cImgsrc = null;
	@OneToMany(targetEntity = Orders.class, mappedBy = "clients", cascade = {CascadeType.ALL})
	private List<Orders> orders = null;

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public String getcImgsrc() {
		return cImgsrc;
	}

	public void setcImgsrc(String cImgsrc) {
		this.cImgsrc = cImgsrc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Clients clients = (Clients) o;

		if (getcId() != null ? !getcId().equals(clients.getcId()) : clients.getcId() != null) return false;
		if (getcName() != null ? !getcName().equals(clients.getcName()) : clients.getcName() != null) return false;
		if (getcPhone() != null ? !getcPhone().equals(clients.getcPhone()) : clients.getcPhone() != null) return false;
		return getcImgsrc() != null ? getcImgsrc().equals(clients.getcImgsrc()) : clients.getcImgsrc() == null;
	}

	@Override
	public int hashCode() {
		int result = getcId() != null ? getcId().hashCode() : 0;
		result = 31 * result + (getcName() != null ? getcName().hashCode() : 0);
		result = 31 * result + (getcPhone() != null ? getcPhone().hashCode() : 0);
		result = 31 * result + (getcImgsrc() != null ? getcImgsrc().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Clients{" +
				"cId='" + cId + '\'' +
				", cName='" + cName + '\'' +
				", cPhone='" + cPhone + '\'' +
				", cImgsrc='" + cImgsrc + '\'' +
				'}';
	}
}
