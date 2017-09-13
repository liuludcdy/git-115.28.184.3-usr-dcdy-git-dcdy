package com.bee.domain;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class News implements Serializable {

	private Long newsId;
	
	private Integer newsType;
	
	private String newsTitle;
	
	private Long authorId;
	
	private String authorNick;
	
	private Integer agreeNum;
	
	private Integer collectNum;
	
	private String newsImage;
	
	private String newsDesc;
	
	private String tagName;
	
	private String diffTime;

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorNick() {
		return authorNick;
	}

	public void setAuthorNick(String authorNick) {
		this.authorNick = authorNick;
	}

	public Integer getAgreeNum() {
		return agreeNum;
	}

	public void setAgreeNum(Integer agreeNum) {
		this.agreeNum = agreeNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public String getNewsImage() {
		return newsImage;
	}

	public void setNewsImage(String newsImage) {
		this.newsImage = newsImage;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(String diffTime) {
		this.diffTime = diffTime;
	}

	public String getNewsDesc() {
		return newsDesc;
	}

	public void setNewsDesc(String newsDesc) {
		this.newsDesc = newsDesc;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsType=" + newsType
				+ ", newsTitle=" + newsTitle + ", authorId=" + authorId
				+ ", authorNick=" + authorNick + ", agreeNum=" + agreeNum
				+ ", collectNum=" + collectNum + ", newsImage=" + newsImage
				+ ", tagName=" + tagName + ", diffTime=" + diffTime + "]";
	}
	
}
