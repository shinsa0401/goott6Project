package com.boritgogae.domain;

public class ExchangeVo {
	private int exchangeNo;
	private int orderDetailNo;
	private int reasonNo;
	private String reasonContent;
	private String exchangeType;
	
	
	
	public ExchangeVo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ExchangeVo(int exchangeNo, int orderDetailNo, int reasonNo, String reasonContent, String exchangeType) {
		super();
		this.exchangeNo = exchangeNo;
		this.orderDetailNo = orderDetailNo;
		this.reasonNo = reasonNo;
		this.reasonContent = reasonContent;
		this.exchangeType = exchangeType;
	}



	public int getExchangeNo() {
		return exchangeNo;
	}



	public void setExchangeNo(int exchangeNo) {
		this.exchangeNo = exchangeNo;
	}



	public int getOrderDetailNo() {
		return orderDetailNo;
	}



	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}



	public int getReasonNo() {
		return reasonNo;
	}



	public void setReasonNo(int reasonNo) {
		this.reasonNo = reasonNo;
	}



	public String getReasonContent() {
		return reasonContent;
	}



	public void setReasonContent(String reasonContent) {
		this.reasonContent = reasonContent;
	}



	public String getExchangeType() {
		return exchangeType;
	}



	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}



	@Override
	public String toString() {
		return "ExchangeVo [exchangeNo=" + exchangeNo + ", orderDetailNo=" + orderDetailNo + ", reasonNo=" + reasonNo
				+ ", reasonContent=" + reasonContent + ", exchangeType=" + exchangeType + "]";
	}


	
	
	
}
