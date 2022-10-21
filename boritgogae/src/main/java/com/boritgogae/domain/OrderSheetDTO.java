package com.boritgogae.domain;

import java.util.List;

public class OrderSheetDTO extends OrderProductDTO{
	
	private List<OrderProductDTO> orderProducts;
	

	public OrderSheetDTO(List<OrderProductDTO> orderProducts) {
		this.orderProducts = (List<OrderProductDTO>)orderProducts;
	}
	
	

	public OrderSheetDTO() {
		super();
	}



	public List<OrderProductDTO> getOrderProducts() {
		return (List<OrderProductDTO>)orderProducts;
	}

	public void setOrderProducts(List<OrderProductDTO> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "OrderSheetDTO [orderProducts=" + orderProducts + "]";
	}
	
	
}
