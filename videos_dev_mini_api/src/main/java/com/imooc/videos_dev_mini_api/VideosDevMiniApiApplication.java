package com.imooc.videos_dev_mini_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SpringBootApplication
@MapperScan(basePackages = {"com.imooc.mapper"})
@ComponentScan(basePackages = {"com.imooc", "org.n3r.idworker"})
public class VideosDevMiniApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideosDevMiniApiApplication.class, args);
	}

//	public static void main(String[] args) {
//		try {
//			String str = URLEncoder.encode("Joel Adams-Please Don't Go.mp3","utf-8");
//			str = str.replaceAll("\\+","%20");
//			System.out.println("str:" + str);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
}
