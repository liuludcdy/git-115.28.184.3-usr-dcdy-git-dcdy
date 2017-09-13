package com.dcdy.vote.biz.impl;

import java.text.SimpleDateFormat;

public class BaseServiceImpl {
	
	public Long GenPrimaryKey() throws Exception {
		// TODO Auto-generated method stub
		return Long.valueOf(Long.toString(System.currentTimeMillis()-new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-01-01 00:00:00").getTime())+Integer.toString((int)(Math.random()*9000+1000)));		
	}
	
}