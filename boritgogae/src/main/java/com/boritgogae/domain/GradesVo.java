package com.boritgogae.domain;

public class GradesVo {
	private String grade;
	private int gradeValue;
	private float reservePoint;
	
	public GradesVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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
		return "GradesVo [grade=" + grade + ", gradeValue=" + gradeValue + ", reservePoint=" + reservePoint + "]";
	}

	public GradesVo(String grade, int gradeValue, float reservePoint) {
		super();
		this.grade = grade;
		this.gradeValue = gradeValue;
		this.reservePoint = reservePoint;
	}
	
	
}
