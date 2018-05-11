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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.iproduct.domain.Product;
import com.lj.iproduct.service.ProductService;
import com.lj.iproduct.utils.MyPage;

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
	//@ResponseBody
	public String product(Model m){
	    List list = productService.findAll();
	    m.addAttribute("product", list);
	   // Map<String,Object> map = new HashMap<>();
	    //map.put("list", list);
		return "product";
	}
	
	@GetMapping("/products")
	public String products(Model m,
			@RequestParam(value = "page",defaultValue = "1",required = false) int page,
			@RequestParam(value = "pagesize",defaultValue = "5",required = false) int pagesize){
		MyPage<Product>  product = productService.PagefindAll(page, pagesize);
		m.addAttribute("product",product);
		//Map<String,Object> map = new HashMap<String,Object>();
		//map.put("product", product);
		//return map;
		return "products";
		
	}
	
}
