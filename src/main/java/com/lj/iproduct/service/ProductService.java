package com.lj.iproduct.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.iproduct.domain.Product;
import com.lj.iproduct.domain.dao.ProductDao;
import com.lj.iproduct.utils.FileUpload;
import com.lj.iproduct.web.ProductForm;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	public void Addproduct(ProductForm productForm){
		Product product = new Product();
		product.init();
		BeanUtils.copyProperties(productForm, product, Product.class);
		product.setPhoto(FileUpload.fileUpload(productForm.getFile()));
		productDao.save(product);
	}
	
	public List<Product> findAll(){
		return productDao.findAll();
	}
	
}
