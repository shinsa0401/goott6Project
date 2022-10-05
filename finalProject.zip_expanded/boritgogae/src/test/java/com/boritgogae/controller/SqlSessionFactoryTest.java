package com.boritgogae.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
		)

public class SqlSessionFactoryTest {
	@Inject
	private SqlSessionFactory sqlFactory; // root-context.xml의 SqlSessionFactory 객체 주입
	
	@Test
	public void testFactory() {
		System.out.println(sqlFactory.toString());
	}
}
