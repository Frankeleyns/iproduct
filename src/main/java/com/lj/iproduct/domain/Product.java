package com.lj.iproduct.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
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
	

}
