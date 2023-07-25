package com.example.regionsDictionaryProject.controller;

import com.example.regionsDictionaryProject.model.RegionsDictionary;
import com.example.regionsDictionaryProject.service.RegionsDictionaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions_dictionary")
public class RegionsDictionaryController {

    private RegionsDictionaryService regionsDictionaryService;

    public RegionsDictionaryController(RegionsDictionaryService regionsDictionaryService) {
        this.regionsDictionaryService = regionsDictionaryService;
    }

    @GetMapping("/all")
    public List<RegionsDictionary> getAll() {
        return regionsDictionaryService.getAllRegions();
    }

    @PostMapping("/create")
    public RegionsDictionary create(@RequestParam Integer id, @RequestParam String name, @RequestParam String shortName) {
        return regionsDictionaryService.createRegion(id, name, shortName);
    }

    @PostMapping("/update/{oldId}")
    public RegionsDictionary update(@PathVariable Integer oldId, @RequestParam Integer newId, @RequestParam String name, @RequestParam String shortName) {
        return regionsDictionaryService.updateRegion(oldId, newId, name, shortName);
    }

    @DeleteMapping("/delete/{id}")
    public List<RegionsDictionary> delete(@PathVariable Integer id) {
        return regionsDictionaryService.deleteRegion(id);
    }
}
