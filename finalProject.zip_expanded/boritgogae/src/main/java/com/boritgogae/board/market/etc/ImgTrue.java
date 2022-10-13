package com.boritgogae.board.market.etc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class ImgTrue {
	private static Map<String, MediaType> media; 
	
	static {
		
		media = new HashMap<>();
		
		//이미지 파일 형식
		media.put("imge/jpg", MediaType.IMAGE_JPEG);
		media.put("imge/gif", MediaType.IMAGE_GIF);
		media.put("imge/png", MediaType.IMAGE_PNG);
		media.put("imge/jpeg", MediaType.IMAGE_JPEG);
		
	}
	
	/**
	 * @methodName : whatMediaType
	 * @author : hsy
	 * @Date : 2022. 10. 12. :
	 * @입력 : param : 파일 확장자
	 * @returnType : MediaType
	 */
	public static MediaType whatMediaType(String contentType) {
		
		return media.get(contentType);
	}
	
}
