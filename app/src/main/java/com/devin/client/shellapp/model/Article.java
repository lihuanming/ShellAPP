package com.devin.client.shellapp.model;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO 
	 * 烘培文章
	 */
	private static final long serialVersionUID = 6669703263313451390L;

	private Integer id;

	private String title;

	private String context;

	private String images;

	private int imageInt;

	public int getImageInt() {
		return imageInt;
	}

	public void setImageInt(int imageInt) {
		this.imageInt = imageInt;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", context=" + context + ", images=" + images + ", created="
				+ created + ", updated=" + updated + ", itemId=" + itemId + ", summary=" + summary + ", collect="
				+ collect + ", click=" + click + ", status=" + status + "]";
	}

	private String created;

	private Date updated;

	private Long itemId;

	private String summary;

	private Long collect;

	private Long click;

	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getCollect() {
		return collect;
	}

	public void setCollect(Long collect) {
		this.collect = collect;
	}

	public Long getClick() {
		return click;
	}

	public void setClick(Long click) {
		this.click = click;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}