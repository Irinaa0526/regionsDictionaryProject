package com.example.regionsDictionaryProject.mapper;

import com.example.regionsDictionaryProject.model.RegionsDictionary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionsDictionaryMapper {
    @Select("select * from regions_dictionary order by name")
    List<RegionsDictionary> findAll();

    @Insert("insert into regions_dictionary values(#{id}, #{name}, #{shortName})")
    void create(RegionsDictionary regionsDictionary);

    @Update("update regions_dictionary set name = #{name}, shortName = #{shortName} where id = #{id}")
    void update(int id, String name, String shortName);

    @Delete("delete from regions_dictionary where id = #{id}")
    void delete(int id);
}
