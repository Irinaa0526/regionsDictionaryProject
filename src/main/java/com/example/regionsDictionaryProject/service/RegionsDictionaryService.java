package com.example.regionsDictionaryProject.service;

import com.example.regionsDictionaryProject.error.BadRequestException;
import com.example.regionsDictionaryProject.error.NotFoundException;

import com.example.regionsDictionaryProject.model.RegionsDictionary;
import com.example.regionsDictionaryProject.repository.RegionsDictionaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
@CacheConfig(cacheNames = "regions")
public class RegionsDictionaryService {
    private final static Logger logger = LoggerFactory.getLogger(RegionsDictionaryService.class);
    private final RegionsDictionaryRepository regionsDictionaryRepository;

    public RegionsDictionaryService(RegionsDictionaryRepository regionsDictionaryRepository) {
        this.regionsDictionaryRepository = regionsDictionaryRepository;
    }

    @Cacheable
    public List<RegionsDictionary> getAll() {
        logger.info("getting regions");
        return regionsDictionaryRepository.findAll();
    }

    @Cacheable
    public RegionsDictionary getById(Integer id) {
        logger.info(String.format("getting region with id %s", id));
        return regionsDictionaryRepository.findById(id);
    }

    @CacheEvict(allEntries = true)
    public RegionsDictionary create(Integer id, String name, String shortName) {
        // проверяем все ли параметры заполнены
        // id не auto increment т.к. каждый регион имеет свой конкретный код
        if (id == null || !StringUtils.hasLength(name) || !StringUtils.hasLength(shortName)) {
            logger.error("Error in createRegion: id, name and short name can't be empty");
            throw new BadRequestException("id, name and short name can't be empty");
        }

        // проверяем не привязан ли уже к указанному id регион
        RegionsDictionary regionsDictionary = regionsDictionaryRepository.findById(id);
        if (regionsDictionary != null) {
            logger.error(String.format("Error in createRegion: Regions with id '%s' already exists", id));
            throw new BadRequestException(String.format("Regions with id '%s' already exists", id));
        }

        regionsDictionary = new RegionsDictionary(id, name, shortName);
        regionsDictionaryRepository.create(regionsDictionary);
        logger.info(String.format("creating region with id %s", id));
        return regionsDictionaryRepository.findById(id);
    }

    @CacheEvict(allEntries = true)
    public RegionsDictionary update(Integer oldId, Integer newId, String name, String shortName) {
        // проверяем существует ли изменяемый регион
        RegionsDictionary regionsDictionaryWithOldId = regionsDictionaryRepository.findById(oldId);
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
        if (newId.equals(oldId) && Objects.equals(name, regionsDictionaryWithOldId.getName()) &&
                Objects.equals(shortName, regionsDictionaryWithOldId.getShortName()))
            return regionsDictionaryWithOldId;

        // проверяем не привязан ли уже к указанному newId регион
        RegionsDictionary regionsDictionaryWithNewId = regionsDictionaryRepository.findById(newId);
        if (regionsDictionaryWithNewId != null && !oldId.equals(newId)) {
            logger.error(String.format("Regions with id '%s' already exists", newId));
            throw new BadRequestException(String.format("Regions with id '%s' already exists", newId));
        }

        regionsDictionaryRepository.update(oldId, newId, name, shortName);
        logger.info(String.format("updating region with id %s", newId));
        return regionsDictionaryRepository.findById(newId);
    }

    @CacheEvict(allEntries = true)
    public List<RegionsDictionary> delete(Integer id) {
        // проверяем существует ли удаляемый регион
        RegionsDictionary regionsDictionaryWithOldId = regionsDictionaryRepository.findById(id);
        if (regionsDictionaryWithOldId == null) {
            logger.error(String.format("Regions with id '%s' not found", id));
            throw new NotFoundException(String.format("Regions with id '%s' not found", id));
        }

        regionsDictionaryRepository.delete(id);
        logger.info(String.format("deleting region with id %s", id));
        return regionsDictionaryRepository.findAll();
    }
}
