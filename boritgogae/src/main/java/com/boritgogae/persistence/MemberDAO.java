package com.boritgogae.persistence;

import java.util.List;

import com.boritgogae.domain.DeliveryInfoVo;
import com.boritgogae.domain.MemberVo;

public interface MemberDAO {
	/**
	 * @methodName : getMemInfo
	 * @author : kjy
	 * @date : 2022. 10. 19.
	 * @입력 param : memberId
	 * @returnType : MemberVo
	 **/
	public MemberVo getMemInfo(String memberId);
	
	/**
	 * @methodName : getMemAddrs
	 * @author : kjy
	 * @date : 2022. 10. 21.
	 * @입력 param : memberId
	 * @returnType : List<DeliveryInfoVo>
	 **/
	public List<DeliveryInfoVo> getMemAddrs(String memberId);
}
