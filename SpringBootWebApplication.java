package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.test.model.Authors;
import com.test.model.Publications;
import com.test.service.AuthorsService;
import com.test.service.StorageService;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer implements CommandLineRunner{

	@Autowired
	private AuthorsService authS;
	
	@Autowired
	private StorageService storageService;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		Authors auth = new Authors();
		auth.setName("Parag");
		auth.setAuthor_id(1L);
		authS.save(auth);
		System.out.println("Dummy Record" +authS.findByName("Parag").getName());
		Publications pub = new Publications();
		pub.setPublication_id(1L);
		pub.setPublicationType("Books");
		pub.setTitle("JAVA Basics");
		pub.setYear("1990");
		
		//authS.addPublication("Parag", pub);
		//System.out.println(authS.retrievePublication("Parag", "Books").getTitle());
		
	
	}
	
	
}