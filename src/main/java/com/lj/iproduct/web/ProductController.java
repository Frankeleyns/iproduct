package com.lj.iproduct.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
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
		return "redirect:/";
	}
		
	@GetMapping("/")
	public String products(Model m,
			@RequestParam(value = "page",defaultValue = "1",required = false) int page,
			@RequestParam(value = "pagesize",defaultValue = "5",required = false) int pagesize){
		MyPage<Product>  pages = productService.PagefindAll(page, pagesize);
		m.addAttribute("pages",pages);
		return "products";
	}
	
	@GetMapping("/product/{id}/delete")
	@ResponseBody
	public String delete(@PathVariable String id){
		productService.delete(id);
		return "success";
	}
	
	@PostMapping("/product/deletes")
	@ResponseBody
	public String deletes( String json_id){
		if(null != json_id){
			System.out.println("======json_id:"+json_id);
			List<String> ids = JSONObject.parseArray(json_id,String.class);
			for(String id:ids){
				productService.delete(id);
			}
			return "success";
		}
		else
			return null;
	}
	
	
}
