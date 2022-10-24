package com.boritgogae.service;

import java.util.List;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.GradeVo;
import com.boritgogae.domain.MemberVo;

public interface MemberService {
	/**
	 * @methodName : getMemberInfo
	 * @author : kjy
	 * @date : 2022. 10. 19.
	 * @입력 param : memberId
	 * @returnType : MemberVo
	 **/
	public MemberVo getMemberInfo(String memberId);
	
	/**
	 * @methodName : getMemAddrs
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : memberId
	 * @returnType : List<DeliveryInfoVo>
	 **/
	public List<DeliveryInfoVo> getMemAddrs(String memberId);
	
	/**
	 * @methodName : getGrade
	 * @author : kjy
	 * @date : 2022. 10. 23.
	 * @입력 param : memberId
	 * @returnType : GradeVo
	 **/
	public GradeVo getGrade(String memberId);
}
