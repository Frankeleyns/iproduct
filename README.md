# SpringBoot2.0文件上传

## 1.添加依赖

```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.mariadb.jdbc</groupId>
			<artifactId>mariadb-java-client</artifactId>
		</dependency>
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.35</version>
		</dependency>
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.6</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

## 2.SpringBoot配置
```
server.port=8088

# Thymeleaf Setting
spring.thymeleaf.cache=false
spring.thymeleaf.mode=HTML

# ===============
# = DataBase Setting
# ===============

# Connection url for the database "iproduct"
spring.datasource.url = jdbc:mariadb://127.0.0.1:3306/iproduct?useUnicode=true&amp;characterEncoding=utf-8&autoReconnect=true&zeroDateTimeBehavior=round

# DataBase Username and password
spring.datasource.username = root
spring.datasource.password = 123456

# Keep the connection alive if idle for a long time (needed in production)
spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1

# =============
# = JPA/Hibernate Setting
# =============

# Show or not log for each sql query
spring.jpa.show-sql = true

# Hibernate ddl auto (create, create-drop, update): with "update" the database
# schema will be automatically updated accordingly to java entities found in
# the project
spring.jpa.hibernate.ddl-auto = update

# Naming strategy
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy

# Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

# Spring 2.0.0 file upload setting
spring.servlet.multipart.max-file-size=-1

spring.http.multipart.maxRequestSize=100Mb
```

## 3.实现图片上传
### (1)实体
```
@Entity
@Table(name = "product")
public class Product {
	
    @Id
	private String id;
	private String name;      //产品名
	private String produce;   //产品介绍
	private String photo;     //产品照片

	public void init(){
		this.id = UUID.randomUUID().toString().replaceAll("-","");
	}
	
	//getters and setters...
	}
```

### （2）控制器
 这里需要3个方法一个是显示图片上传的网页的方法，一个是图片上传功能的方法，还有一个是显示上传图片的方法
```
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
	public String product(Model m){
	    List list = productService.findAll();
	    m.addAttribute("product", list);
		return "product";
	}
```
 这个ProductForm是两个需要表单提交的参数被封装了起来，
```
public class ProductForm {

	private String name;  //产品名
	private String produce;  //产品介绍
	private MultipartFile file; //上传的文件
	
	//getters and setters...
	}
```

### (3) 业务层实现
```
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
```
 图片上传的方法被我封装成一个FileUpload类了，
```
public class FileUpload {

	public static String fileUpload(MultipartFile File){
		
		        //文件源文件名
				String fileName = new Date().getTime()+File.getOriginalFilename();
				System.out.println("源文件名------>"+fileName);
				//文件上传路径
				String filePath = "E:/images/";
				System.out.println("文件上传路径------->"+ filePath);
				File file = new File(filePath);
				if(!file.exists()){
				 file.mkdirs();	
				}
				
				try {
					//将上传文件内容通过I/O流写入到新文件
					FileOutputStream out = new FileOutputStream(filePath+fileName);
					out.write(File.getBytes());
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				
				
				return "http://localhost:8088/images/"+fileName;
	}
	
}
``` 

### (4)虚拟路径与跨域设置
虚拟路径
```
@Configuration
public class PhotoConfig implements WebMvcConfigurer {

	 public void addResourceHandlers(ResourceHandlerRegistry registry) {   
		  /**
		 * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
		 *这是图片的物理路径  "file:/+本地图片的地址"
		 * @Date： Create in 14:08 2017/12/20
		 */     registry.addResourceHandler("/images/**").addResourceLocations("file:/E:/images/");
		       
		    }
}

跨域设置
```
//跨域访问配置
@Configuration  
public class CrosConfig implements WebMvcConfigurer{  

    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowCredentials(true)  
                .allowedMethods("GET", "POST", "DELETE", "PUT")  
                .maxAge(3600);  
    }

}
```

## 5.网页
（1）文件上传网页
```
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form enctype="multipart/form-data" method="post" th:action="@{/addpro}">
<p>
商品名：   <input type="text" name="name"/>
</p>
商品介绍：
<p> 
   <textarea rows="15" cols="70" name="produce" id="textarea"></textarea>
</p>
<p>
 图片:<input type="file" name="file"/>
 </p>
    <input type="submit" value="上传"/>
    </form>
</body>
</html>
```
(2)显示文件网页
```
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div th:each="pro:${product}">
<p>
<h1 th:text="${pro?.name}"></h1>
</p>
商品介绍:<p th:text="${pro?.produce}"></p>
<img th:src="@{${pro?.photo}}" style="width:500px;height:450px;" />
</div>
</body>
</html>
```



