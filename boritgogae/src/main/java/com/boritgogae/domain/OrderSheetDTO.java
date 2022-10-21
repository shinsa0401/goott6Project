package com.boritgogae.domain;

import java.util.List;

public class OrderSheetDTO {
	private List<OrderProductDTO> orderProducts;
	

	public OrderSheetDTO(List<OrderProductDTO> orderProducts) {
		super();
		this.orderProducts = orderProducts;
	}
	
	

	public OrderSheetDTO() {
		super();
	}



	public List<OrderProductDTO> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductDTO> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "OrderSheetDTO [orderProducts=" + orderProducts + "]";
	}
	
	
}
