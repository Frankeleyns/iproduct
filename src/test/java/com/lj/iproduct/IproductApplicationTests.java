package com.lj.iproduct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IproductApplicationTests {

	@Value(value = "${upload.path}")
	private String filePath;
	@Test
	public void contextLoads() {
		System.out.println("==="+filePath);
	}

}
