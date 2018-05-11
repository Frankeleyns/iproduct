package com.lj.iproduct.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.lj.iproduct.domain.User;

@Controller
public class UserController {


  
  @GetMapping("/getuser/{id}")
  @ResponseBody
  public User get(@PathVariable String id){
	  RestTemplate restTemplate = new RestTemplate(); 
	  User user = restTemplate.getForObject("http://localhost:6062/iuser/{id}", User.class, id);
	  return user;
  }
	
}
