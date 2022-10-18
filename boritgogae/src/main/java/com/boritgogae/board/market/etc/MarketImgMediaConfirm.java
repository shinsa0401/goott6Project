package com.boritgogae.board.market.etc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MarketImgMediaConfirm {
	private static Map<String, MediaType> mediaMap; 
	
	static {
		
		mediaMap = new HashMap<>();
		
		//이미지 파일 형식
		mediaMap.put("image/jpg", MediaType.IMAGE_JPEG);
		mediaMap.put("image/gif", MediaType.IMAGE_GIF);
		mediaMap.put("image/png", MediaType.IMAGE_PNG);
		mediaMap.put("image/jpeg", MediaType.IMAGE_JPEG);
		
	}
	
	/**
	 * @methodName : getMediaType
	 * @author : hsy
	 * @Date : 2022. 10. 12. :
	 * @입력 : param : 파일 확장자
	 * @returnType : MediaType
	 */
	public static MediaType getMediaType(String contentType) {
		return mediaMap.get(contentType);
	}
	
}
