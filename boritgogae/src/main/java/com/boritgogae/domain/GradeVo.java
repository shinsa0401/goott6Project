package com.boritgogae.domain;

public class GradeVo {
	private String grad;
	private int gradeValue;
	private float reservePoint;
	
	public GradeVo() {
		super();
	}

	public GradeVo(String grad, int gradeValue, float reservePoint) {
		super();
		this.grad = grad;
		this.gradeValue = gradeValue;
		this.reservePoint = reservePoint;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public int getGradeValue() {
		return gradeValue;
	}

	public void setGradeValue(int gradeValue) {
		this.gradeValue = gradeValue;
	}

	public float getReservePoint() {
		return reservePoint;
	}

	public void setReservePoint(float reservePoint) {
		this.reservePoint = reservePoint;
	}

	@Override
	public String toString() {
		return "GradeVo [grad=" + grad + ", gradeValue=" + gradeValue + ", reservePoint=" + reservePoint + "]";
	}
}
