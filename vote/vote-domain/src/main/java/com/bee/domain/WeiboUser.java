package com.bee.domain;

import java.io.Serializable;

public class WeiboUser implements Serializable {
	
	private String screen_name;
	
	private String description;
	
	private String profile_image_url;
	
	private String gender;
	
	private String url;

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "WeiboUser [screen_name=" + screen_name + ", description="
				+ description + ", profile_image_url=" + profile_image_url
				+ ", gender=" + gender + ", url=" + url + "]";
	}
	
}
