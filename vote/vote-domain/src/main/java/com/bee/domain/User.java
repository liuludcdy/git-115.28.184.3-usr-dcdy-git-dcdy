package com.bee.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class User implements Serializable {

	private Long userId;
	
	private String mobile;
	
	private String nick;
	
	private String job;
	
	private String email;
	
	private String loginName;
	
	private String loginPass;
	
	private String userIcon;
	
	private MultipartFile mf;

	private String birthYear;
	
	private String birthMonth;
	
	private String birthDay;
	
	private String companyName;
	
	private Integer gender;
	
	private String weibo;
	
	private String weixin;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public MultipartFile getMf() {
		return mf;
	}

	public void setMf(MultipartFile mf) {
		this.mf = mf;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", mobile=" + mobile + ", nick="
				+ nick + ", job=" + job + ", email=" + email + ", loginName="
				+ loginName + ", loginPass=" + loginPass + ", userIcon="
				+ userIcon + ", mf=" + mf + ", birthYear=" + birthYear
				+ ", birthMonth=" + birthMonth + ", birthDay=" + birthDay
				+ ", companyName=" + companyName + ", gender=" + gender
				+ ", weibo=" + weibo + ", weixin=" + weixin + "]";
	}

}
