package com.example.regionsDictionaryProject.controller;

import com.example.regionsDictionaryProject.model.RegionsDictionary;
import com.example.regionsDictionaryProject.service.RegionsDictionaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions_dictionary")
public class RegionsDictionaryController {

    private final RegionsDictionaryService regionsDictionaryService;

    public RegionsDictionaryController(RegionsDictionaryService regionsDictionaryService) {
        this.regionsDictionaryService = regionsDictionaryService;
    }

    @GetMapping("/")
    public List<RegionsDictionary> getAllRegions() {
        return regionsDictionaryService.getAll();
    }

    @GetMapping("/{id}")
    public RegionsDictionary getRegionById(@PathVariable Integer id) {
        return regionsDictionaryService.getById(id);
    }

    @PostMapping("/create")
    public RegionsDictionary createRegion(@RequestParam Integer id, @RequestParam String name, @RequestParam String shortName) {
        return regionsDictionaryService.create(id, name, shortName);
    }

    @PostMapping("/update/{oldId}")
    public RegionsDictionary updateRegion(@PathVariable Integer oldId, @RequestParam Integer newId, @RequestParam String name, @RequestParam String shortName) {
        return regionsDictionaryService.update(oldId, newId, name, shortName);
    }

    @DeleteMapping("/delete/{id}")
    public List<RegionsDictionary> deleteRegion(@PathVariable Integer id) {
        return regionsDictionaryService.delete(id);
    }
}
