package com.lj.iproduct.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.iproduct.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/addpro")
	public String addproduct(){
		return "add_product";
	}
	
	@PostMapping("/addpro")
	public String addProduct(ProductForm productForm){
		productService.Addproduct(productForm);
		return "redirect:/product";
	}
	
	@GetMapping("/product")
	@ResponseBody
	public Map<String, Object> product(Model m){
	    List list = productService.findAll();
	    m.addAttribute("product", list);
	    Map<String,Object> map = new HashMap<>();
	    map.put("list", list);
		return map;
	}
	
	
	
}
