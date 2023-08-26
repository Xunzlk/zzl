package com.zzl.zzlblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.zzl.zzlblog.mapper")
@SpringBootApplication
public class ZzlBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzlBlogApplication.class, args);
	}

}
