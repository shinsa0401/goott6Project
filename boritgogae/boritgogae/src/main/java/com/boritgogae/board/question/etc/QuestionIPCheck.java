package com.boritgogae.board.question.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class QuestionIPCheck {
	private static String ipAddr;
	
	public static String getIpAddr() {
		
		// URL 주소 객체 생성
		try {
			URL ipcheckURL = new URL("https://checkip.amazonaws.com");
			
			// 파일이나 전문 // reader 문을연다 -- 미완
			BufferedReader in = new BufferedReader(new InputStreamReader(ipcheckURL.openStream()));
			ipAddr = in.readLine();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return ipAddr;
	}
}
