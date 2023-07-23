package com.example.regionsDictionaryProject;

import com.example.regionsDictionaryProject.model.RegionsDictionary;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(RegionsDictionary.class)
@MapperScan("com.example.regionsDictionaryProject.mapper")
@SpringBootApplication
public class RegionsDictionaryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegionsDictionaryProjectApplication.class, args);
	}

}
