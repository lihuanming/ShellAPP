package com.devin.client.shellapp.model;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by yatu on 2016-05-16.
 */
//广告图片
public class Image implements Serializable{
	private Integer id;

	private String image;

	private Date created;

	private String type;

	private String ids;

	private int status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}