package com.lj.iproduct.web;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {

	private String name;  //产品名
	private String produce;  //产品介绍
	private MultipartFile file; //上传的文件

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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
