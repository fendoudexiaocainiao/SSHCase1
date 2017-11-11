package com.wanghao.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "sshcase", catalog = "sshcase")
public class Orders {
	@Id
	@Column(name = "o_id")
	@GenericGenerator(name = "ouuid", strategy = "uuid")
	@GeneratedValue(generator = "ouuid")
	private String oId = null;
	@Column(name = "o_receiver_address")
	private String oReceiverAddress = null;
	@Column(name = "o_price")
	private Integer oPrice = null;
	@ManyToOne(targetEntity = Clients.class, cascade = {CascadeType.ALL})
	@JoinColumn(name = "o_c_id")
	private Clients clients = null;

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public String getoReceiverAddress() {
		return oReceiverAddress;
	}

	public void setoReceiverAddress(String oReceiverAddress) {
		this.oReceiverAddress = oReceiverAddress;
	}

	public Integer getoPrice() {
		return oPrice;
	}

	public void setoPrice(Integer oPrice) {
		this.oPrice = oPrice;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Orders orders = (Orders) o;

		if (getoId() != null ? !getoId().equals(orders.getoId()) : orders.getoId() != null) return false;
		if (getoReceiverAddress() != null ? !getoReceiverAddress().equals(orders.getoReceiverAddress()) : orders.getoReceiverAddress() != null)
			return false;
		return getoPrice() != null ? getoPrice().equals(orders.getoPrice()) : orders.getoPrice() == null;
	}

	@Override
	public int hashCode() {
		int result = getoId() != null ? getoId().hashCode() : 0;
		result = 31 * result + (getoReceiverAddress() != null ? getoReceiverAddress().hashCode() : 0);
		result = 31 * result + (getoPrice() != null ? getoPrice().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Orders{" +
				"oId='" + oId + '\'' +
				", oReceiverAddress='" + oReceiverAddress + '\'' +
				", oPrice=" + oPrice +
				'}';
	}
}
