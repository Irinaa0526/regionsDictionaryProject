package com.example.regionsDictionaryProject.service;

import com.example.regionsDictionaryProject.error.BadRequestException;
import com.example.regionsDictionaryProject.error.NotFoundException;
import com.example.regionsDictionaryProject.mapper.RegionsDictionaryMapper;
import com.example.regionsDictionaryProject.model.RegionsDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class RegionsDictionaryService {
    private final static Logger logger = LoggerFactory.getLogger(RegionsDictionaryService.class);
    private RegionsDictionaryMapper regionsDictionaryMapper;

    public RegionsDictionaryService(RegionsDictionaryMapper regionsDictionaryMapper) {
        this.regionsDictionaryMapper = regionsDictionaryMapper;
    }

    public List<RegionsDictionary> getAllRegions() {
        return regionsDictionaryMapper.findAll();
    }

    public RegionsDictionary createRegion(Integer id, String name, String shortName) {
        // проверяем все ли параметры заполнены
        // id не auto increment т.к. каждый регион имеет свой конкретный код
        if (id == null || !StringUtils.hasLength(name) || !StringUtils.hasLength(shortName)) {
            logger.error("Error in createRegion: id, name and short name can't be empty");
            throw new BadRequestException("id, name and short name can't be empty");
        }

        // проверяем не привязан ли уже к указанному id регион
        RegionsDictionary regionsDictionary = regionsDictionaryMapper.findById(id);
        if (regionsDictionary != null) {
            logger.error(String.format("Error in createRegion: Regions with id '%s' already exists", id));
            throw new BadRequestException(String.format("Regions with id '%s' already exists", id));
        }

        regionsDictionary = new RegionsDictionary(id, name, shortName);
        regionsDictionaryMapper.create(regionsDictionary);
        return regionsDictionaryMapper.findById(id);
    }

    public RegionsDictionary updateRegion(Integer oldId, Integer newId, String name, String shortName) {
        // проверяем существует ли изменяемый регион
        RegionsDictionary regionsDictionaryWithOldId = regionsDictionaryMapper.findById(oldId);
        if (regionsDictionaryWithOldId == null) {
            logger.error(String.format("Error in updateRegion: Regions with id '%s' not found", oldId));
            throw new NotFoundException(String.format("Regions with id '%s' not found", oldId));
        }

        // проверяем все ли поля заполнены
        if (newId == null || !StringUtils.hasLength(name) || !StringUtils.hasLength(shortName)) {
            logger.error("id, name and short name can't be empty");
            throw new BadRequestException("id, name and short name can't be empty");
        }

        // если все новые значение равны старым значениям, то просто возвращаем объект
        if (newId == oldId && Objects.equals(name, regionsDictionaryWithOldId.getName()) &&
                Objects.equals(shortName, regionsDictionaryWithOldId.getShortName()))
            return regionsDictionaryWithOldId;

        // проверяем не привязан ли уже к указанному newId регион
        RegionsDictionary regionsDictionaryWithNewId = regionsDictionaryMapper.findById(newId);
        if (regionsDictionaryWithNewId != null) {
            logger.error(String.format("Regions with id '%s' already exists", newId));
            throw new BadRequestException(String.format("Regions with id '%s' already exists", newId));
        }

        regionsDictionaryMapper.update(oldId, newId, name, shortName);
        return regionsDictionaryMapper.findById(newId);
    }

    public List<RegionsDictionary> deleteRegion(Integer id) {
        // проверяем существует ли удаляемый регион
        RegionsDictionary regionsDictionaryWithOldId = regionsDictionaryMapper.findById(id);
        if (regionsDictionaryWithOldId == null) {
            logger.error(String.format("Regions with id '%s' not found", id));
            throw new NotFoundException(String.format("Regions with id '%s' not found", id));
        }

        regionsDictionaryMapper.delete(id);
        return regionsDictionaryMapper.findAll();
    }

}
