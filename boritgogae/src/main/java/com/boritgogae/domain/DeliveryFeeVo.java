package com.boritgogae.domain;

public class DeliveryFeeVo {
	private String deliveryOption;
	private int deliveryFee;
	
	public DeliveryFeeVo(String deliveryOption, int deliveryFee) {
		super();
		this.deliveryOption = deliveryOption;
		this.deliveryFee = deliveryFee;
	}

	public DeliveryFeeVo() {
		super();
	}

	public String getDeliveryOption() {
		return deliveryOption;
	}

	public void setDeliveryOption(String deliveryOption) {
		this.deliveryOption = deliveryOption;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	@Override
	public String toString() {
		return "DeliveryFeeVo [deliveryOption=" + deliveryOption + ", deliveryFee=" + deliveryFee + "]";
	}
}
