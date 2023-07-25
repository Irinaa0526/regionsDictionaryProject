package com.example.regionsDictionaryProject.mapper;

import com.example.regionsDictionaryProject.model.RegionsDictionary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionsDictionaryMapper {
    @Select("select * from regions_dictionary order by name")
    List<RegionsDictionary> findAll();

    @Select("select * from regions_dictionary where id = #{id}")
    RegionsDictionary findById(Integer id);

    @Insert("insert into regions_dictionary values(#{id}, #{name}, #{shortName})")
    void create(RegionsDictionary regionsDictionary);

    @Update("update regions_dictionary set id = #{newId}, name = #{name}, shortName = #{shortName} where id = #{oldId}")
    void update(Integer oldId, Integer newId, String name, String shortName);

    @Delete("delete from regions_dictionary where id = #{id}")
    void delete(Integer id);
}
