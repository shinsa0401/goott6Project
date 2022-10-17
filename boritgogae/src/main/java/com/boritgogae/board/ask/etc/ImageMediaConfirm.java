package com.boritgogae.board.ask.etc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class ImageMediaConfirm {
	private static Map<String, MediaType> mediaMap;
	
	{
		// 인스턴스 멤버를 초기화 하는 블럭 -> 생성자에서 할 수 있으므로 잘 사용하지 않는다.
	}
	
	static {
		// static 멤버를 초기화 하는 블럭
		mediaMap = new HashMap<>();
		
		// 이미지 파일 형식을 map에 넣어둠
		mediaMap.put("image/jpg", MediaType.IMAGE_JPEG);
		mediaMap.put("image/gif", MediaType.IMAGE_GIF);
		mediaMap.put("image/png", MediaType.IMAGE_PNG);
		mediaMap.put("image/jpeg", MediaType.IMAGE_JPEG);
		
	}
	

	public static MediaType getMediaType(String contentType) {
		
		return mediaMap.get(contentType);
	}
}
