package ru.osokin.farmerregistry.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.osokin.farmerregistry.dto.AreaDTO;
import ru.osokin.farmerregistry.entity.Area;
import ru.osokin.farmerregistry.util.exceptions.AreaNotFoundException;
import ru.osokin.farmerregistry.repositories.AreasRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AreasService {

    private final AreasRepository areasRepository;
    private final ModelMapper modelMapper;

    public List<AreaDTO> findAll() {
        List<Area> all = areasRepository.findAll();

        return all.stream()
                .filter(a -> !a.getIsArchive())
                .map(this::areaToDto)
                .collect(Collectors.toList());
    }

    private AreaDTO areaToDto(Area area) {
        return modelMapper.map(area, AreaDTO.class);
    }

    public Area findById(Long id) {
        return areasRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Район не найден"));
    }

    @Transactional
    public void archive(Long id) {
        Area area = areasRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Район не найден"));
        area.setIsArchive(true);
        areasRepository.save(area);
    }

    @Transactional
    public void addArea(Area area) {
        areasRepository.save(area);
    }

    @Transactional
    public void deleteById(Long id) {
        areasRepository.deleteById(id);
    }

    @Transactional
    public void updateArea(Long id, Area area) {
        Area areaToUpdate = areasRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Район не найден"));

        areaToUpdate.setName(area.getName());
        areaToUpdate.setCode(area.getCode());
        areaToUpdate.setIsArchive(area.getIsArchive());

        areasRepository.save(areaToUpdate);
    }
}
