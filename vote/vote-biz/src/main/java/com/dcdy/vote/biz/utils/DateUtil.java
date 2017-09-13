package com.dcdy.vote.biz.utils;

import java.util.Date;

public class DateUtil {
	
	public static String getDiffDate(Date dt) {
		long l = (new Date().getTime()-dt.getTime())/1000;
		if(l<60) {
			return "1分钟前";
		} else if(l<60*60) {
			return (l/60)+"分钟前";
		} else if(l<60*60*24) {
			return (l/(60*60))+"小时前";
		} else if(l<60*60*24*30) {
			return (l/(60*60*24))+"天前";
		} else if(l<60*60*24*30*12) {
			return (l/(60*60*24*30))+"月前";
		} else {
			return(l/(60*60*24*30*12))+"年前";
		}
	}
}
