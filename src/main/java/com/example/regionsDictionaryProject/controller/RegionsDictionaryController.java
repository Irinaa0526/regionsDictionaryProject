package com.example.regionsDictionaryProject.controller;

import com.example.regionsDictionaryProject.model.RegionsDictionary;
import com.example.regionsDictionaryProject.mapper.RegionsDictionaryMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions_dictionary")
public class RegionsDictionaryController {

    private RegionsDictionaryMapper regionsDictionaryMapper;

    public RegionsDictionaryController(RegionsDictionaryMapper regionsDictionaryMapper) {
        this.regionsDictionaryMapper = regionsDictionaryMapper;
    }

    @GetMapping("/all")
    public List<RegionsDictionary> getAll() {
        return regionsDictionaryMapper.findAll();
    }

    @GetMapping("/create")
    public List<RegionsDictionary> create(@RequestParam int id, @RequestParam String name, @RequestParam String shortName) {
        RegionsDictionary regionsDictionary = new RegionsDictionary(id, name, shortName);
        regionsDictionaryMapper.create(regionsDictionary);
        return regionsDictionaryMapper.findAll();
    }

    @GetMapping("/update")
    public List<RegionsDictionary> update(@RequestParam int id, @RequestParam String name, @RequestParam String shortName) {
        regionsDictionaryMapper.update(id, name, shortName);
        return regionsDictionaryMapper.findAll();
    }

    @GetMapping("/delete/{id}")
    public List<RegionsDictionary> delete(@PathVariable int id) {
        regionsDictionaryMapper.delete(id);
        return regionsDictionaryMapper.findAll();
    }
}
