package com.boritgogae.board.notice.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class IPCheck {
	private static String ipAddr;
	
	public static String getIPAddr() {
		// URL 주소 객체 생성
		try {
			URL ipcheckURL = new URL("https://checkip.amazonaws.com");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(ipcheckURL.openStream()));
			ipAddr = in.readLine();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return ipAddr;
	}
}
