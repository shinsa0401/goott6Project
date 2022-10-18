package com.boritgogae.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.boritgogae.domain.MemberVo;

@Repository
public class AdminDAOImpl implements AdminDAO {

   @Inject
   private SqlSession ses;
   
   private static String ns = "com.boritgogae.AdminMapper";
   
   @Override
   public List<MemberVo> getMembers() throws Exception {
      return ses.selectList(ns + ".getMembers");
   }

}