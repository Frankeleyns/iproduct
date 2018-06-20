package com.lj.iproduct.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
    @Id
	private String id;
	private String name;      //产品名
	private String produce;   //产品介绍
	private String photo;     //产品照片
	private Timestamp pubtime;//发布时间

	public void init(){
		this.id = UUID.randomUUID().toString().replaceAll("-","");
		this.pubtime = new Timestamp(System.currentTimeMillis());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Timestamp getPubtime() {
		return pubtime;
	}

	public void setPubtime(Timestamp pubtime) {
		this.pubtime = pubtime;
	}

}
