package com.boritgogae.domain;

import java.util.List;

public class OptionVo {
	private String mainCategory;
	private String middleCategory;
	private String subCategory;
	private String materialOption;
	private String sizeOption;
	private String colorOption;
	private String weightOption;
	private String etcOption;
	private String materialSubOption;
	private String sizeSubOption;
	private String colorSubOption;
	private String weightSubOption;
	private String etcSubOption;
	
	public OptionVo(String prodNo) {
		String[] prodNoLst = prodNo.split("");
		
		switch (prodNoLst[0]) {
		case "C":
			this.mainCategory = "고양이";
			break;
		case "D":
			this.mainCategory = "강아지";
		case "A":
			this.mainCategory = "공용";
		} 
		
		switch (prodNoLst[1]) {
		case "F":
			this.middleCategory = "음식";
			switch (prodNoLst[2]) {
			case "1":
				this.subCategory = "사료";
				break;

			case "2":
				this.subCategory = "간식";
				break;
			}
			break;
		case "C":
			this.middleCategory = "위생";
			switch (prodNoLst[2]) {
			case "1":
				this.subCategory = "고양이 모래";
				break;

			case "2":
				this.subCategory = "화장실";
				break;
			case "3":
				this.subCategory = "화장실 용품";
				break;
			}
			break;
		case "B":
			this.middleCategory = "미용";
			switch (prodNoLst[2]) {
			case "1":
				this.subCategory = "목욕용품";
				break;
			case "2":
				this.subCategory = "미용용품";
				break;
			case "3":
				this.subCategory = "건강/의약외품";
				break;
			}
			break;
		case "T":
			this.middleCategory = "장난감";
			switch (prodNoLst[2]) {
			case "1":
				this.subCategory = "놀이용품";
				break;
			}
			break;
		case "L":
			this.middleCategory = "리빙";
			switch (prodNoLst[2]) {
			case "1":
				this.subCategory = "하우스/방석/철장";
				break;
			case "2":
				this.subCategory = "이동장/이동가방";
				break;
			case "3":
				this.subCategory = "건강/식기";
				break;
			case "4":
				this.subCategory = "줄/패션용품";
				break;
			case "5":
				this.subCategory = "스크래쳐/캣타워";
				break;
			}
			break;
		case "W":
			this.middleCategory = "외출";
			switch (prodNoLst[2]) {
			case "1":
				this.subCategory = "놀이용품";
				break;
			case "2":
				this.subCategory = "줄/외출용품";
				break;
			case "3":
				this.subCategory = "패션/소품";
				break;
			}
			break;
		case "E":
			this.middleCategory = "기타";
			break;
		}
		
		switch (prodNoLst[2]) {
		case "0":
			this.subCategory = "기타";
			break;
		} 
		
		if (prodNoLst[7].equals("W")) {
			this.weightOption = "용량";
			switch (prodNoLst[8]+prodNoLst[9]) {
			case "01":
				this.weightSubOption = "1kg 미만";
				break;
			case "02":
				this.weightSubOption = "1kg ~ 5kg";
				break;
			case "03":
				this.weightSubOption = "6kg ~ 10kg";
				break;
			case "04":
				this.weightSubOption = "10kg 이상";
				break;
			case "00":
				this.weightSubOption = "성격없음";
				break;
			}
		}
		
		if (prodNoLst[10].equals("M")) {
			this.materialOption = "주재료";
			switch (prodNoLst[11]+prodNoLst[12]) {
			case "01":
				this.materialSubOption = "가금류";
				break;
			case "02":
				this.materialSubOption = "어패류";
				break;
			case "03":
				this.materialSubOption = "육류";
				break;
			case "04":
				this.materialSubOption = "기타";
				break;
			case "00":
				this.materialSubOption = "성격없음";
				break;
			}
		}
		
		if (prodNoLst[13].equals("S")) {
			this.sizeOption = "크기";
			switch (prodNoLst[14]+prodNoLst[15]) {
			case "01":
				this.sizeSubOption = "S";
				break;
			case "02":
				this.sizeSubOption = "M";
				break;
			case "03":
				this.sizeSubOption = "L";
				break;
			case "00":
				this.sizeSubOption = "성격없음";
				break;
			}
			
		}
		if (prodNoLst[16].equals("C")) {
			this.colorOption = "색상";
			switch (prodNoLst[17]+prodNoLst[18]) {
			case "01":
				this.colorSubOption = "빨강";
				break;
			case "02":
				this.colorSubOption = "주황";
				break;
			case "03":
				this.colorSubOption = "노랑";
				break;
			case "04":
				this.colorSubOption = "초록";
				break;
			case "05":
				this.colorSubOption = "파랑";
				break;
			case "06":
				this.colorSubOption = "남색";
				break;
			case "07":
				this.colorSubOption = "보라";
				break;
			case "08":
				this.colorSubOption = "흰색";
				break;
			case "09":
				this.colorSubOption = "검정";
				break;
			case "10":
				this.colorSubOption = "기타";
				break;
			case "00":
				this.colorSubOption = "성격없음";
				break;
			}
		}
		
		
		if (prodNoLst[19].equals("E")) {
			this.etcOption = "기타";
			switch (prodNoLst[20]+prodNoLst[21]) {
			case "00":
				this.etcSubOption = "성격없음";
				break;
			}
		}
		
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getMiddleCategory() {
		return middleCategory;
	}

	public void setMiddleCategory(String middleCategory) {
		this.middleCategory = middleCategory;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getMaterialOption() {
		return materialOption;
	}

	public void setMaterialOption(String materialOption) {
		this.materialOption = materialOption;
	}

	public String getSizeOption() {
		return sizeOption;
	}

	public void setSizeOption(String sizeOption) {
		this.sizeOption = sizeOption;
	}

	public String getColorOption() {
		return colorOption;
	}

	public void setColorOption(String colorOption) {
		this.colorOption = colorOption;
	}

	public String getWeightOption() {
		return weightOption;
	}

	public void setWeightOption(String weightOption) {
		this.weightOption = weightOption;
	}

	public String getEtcOption() {
		return etcOption;
	}

	public void setEtcOption(String etcOption) {
		this.etcOption = etcOption;
	}

	public String getMaterialSubOption() {
		return materialSubOption;
	}

	public void setMaterialSubOption(String materialSubOption) {
		this.materialSubOption = materialSubOption;
	}

	public String getSizeSubOption() {
		return sizeSubOption;
	}

	public void setSizeSubOption(String sizeSubOption) {
		this.sizeSubOption = sizeSubOption;
	}

	public String getColorSubOption() {
		return colorSubOption;
	}

	public void setColorSubOption(String colorSubOption) {
		this.colorSubOption = colorSubOption;
	}

	public String getWeightSubOption() {
		return weightSubOption;
	}

	public void setWeightSubOption(String weightSubOption) {
		this.weightSubOption = weightSubOption;
	}

	public String getEtcSubOption() {
		return etcSubOption;
	}

	public void setEtcSubOption(String etcSubOption) {
		this.etcSubOption = etcSubOption;
	}

	@Override
	public String toString() {
		return "OptionVo [mainCategory=" + mainCategory + ", middleCategory=" + middleCategory + ", subCategory="
				+ subCategory + ", materialOption=" + materialOption + ", sizeOption=" + sizeOption + ", colorOption="
				+ colorOption + ", weightOption=" + weightOption + ", etcOption=" + etcOption + ", materialSubOption="
				+ materialSubOption + ", sizeSubOption=" + sizeSubOption + ", colorSubOption=" + colorSubOption
				+ ", weightSubOption=" + weightSubOption + ", etcSubOption=" + etcSubOption + "]";
	}
}
