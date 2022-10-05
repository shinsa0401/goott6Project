package com.boritgogae.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
		)
public class DataSourceTest {
	@Inject
	private DataSource ds; // root-context.xml에서 dataSource 객체를 주입
	
	@Test // J4Unit에 의해 자동 호출되어 테스트 되는 메서드
	public void testConnection() throws SQLException {
		Connection con = ds.getConnection();
		
		System.out.println(con.toString());
	}

}
