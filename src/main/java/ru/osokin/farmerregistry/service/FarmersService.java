package ru.osokin.farmerregistry.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.osokin.farmerregistry.dto.FarmerDTO;
import ru.osokin.farmerregistry.entity.Area;
import ru.osokin.farmerregistry.entity.Farmer;
import ru.osokin.farmerregistry.repositories.AreasRepository;
import ru.osokin.farmerregistry.util.exceptions.FarmerNotFoundException;
import ru.osokin.farmerregistry.repositories.FarmersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmersService {

    private final FarmersRepository farmersRepository;
    private final AreasRepository areasRepository;
    private final ModelMapper modelMapper;

    public List<FarmerDTO> findAll() {
        return farmersRepository.findAll().stream()
                .filter(f -> !f.getIsArchive())
                .map(f -> modelMapper.map(f, FarmerDTO.class))
                .collect(Collectors.toList());
    }


    public Farmer findById(Long id) {
        return farmersRepository.findById(id).orElseThrow(() -> new FarmerNotFoundException("Фермер не найден"));
    }

    @Transactional
    public void registerFarmer(FarmerDTO farmerDTO) {
        Farmer farmer = convertFromDto(farmerDTO);
        farmersRepository.save(farmer);
    }

    private Farmer convertFromDto(FarmerDTO dto) {
        Farmer farmer = modelMapper.map(dto, Farmer.class);
        Area registrationArea = areasRepository.findById(dto.getRegistrationAreaId()).get();
        farmer.setRegistrationArea(registrationArea);
        return farmer;
    }

    @Transactional
    public void update(Long id, FarmerDTO farmerDTO) {
        Farmer farmerToUpdate = farmersRepository.findById(id).orElseThrow(() -> new FarmerNotFoundException("Такого фермера не существует"));
        Area registrationArea = areasRepository.findById(farmerDTO.getRegistrationAreaId()).get();
        farmerToUpdate.setName(farmerDTO.getName());
        farmerToUpdate.setINN(farmerDTO.getINN());
        farmerToUpdate.setKPP(farmerDTO.getKPP());
        farmerToUpdate.setOGRN(farmerDTO.getOGRN());
        farmerToUpdate.setRegistrationArea(registrationArea);
        farmerToUpdate.setRegistrationDate(farmerDTO.getRegistrationDate());
        farmerToUpdate.setIsArchive(farmerDTO.getIsArchive());
        farmersRepository.save(farmerToUpdate);
    }

    @Transactional
    public void archive(Long id) {
        Farmer farmerToArchive = farmersRepository.findById(id).get();
        farmerToArchive.setIsArchive(true);
        farmersRepository.save(farmerToArchive);
    }

    @Transactional
    public void removeFarmer(Long id) {
        farmersRepository.deleteById(id);
    }
}
