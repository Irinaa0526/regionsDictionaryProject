package com.example.regionsDictionaryProject;

import com.example.regionsDictionaryProject.model.RegionsDictionary;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MappedTypes(RegionsDictionary.class)
@MapperScan("com.example.regionsDictionaryProject.repository")
@SpringBootApplication
public class RegionsDictionaryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegionsDictionaryProjectApplication.class, args);
	}

}
