package com.lj.iproduct.web;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductForm {

	private String name;  //产品名
	private String produce;  //产品介绍
	private MultipartFile file; //上传的文件

}
