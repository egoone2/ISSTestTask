package ru.osokin.farmerregistry.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.osokin.farmerregistry.dto.AreaDTO;
import ru.osokin.farmerregistry.entity.Area;
import ru.osokin.farmerregistry.repositories.AreasRepository;
import ru.osokin.farmerregistry.util.exceptions.AreaRegistrationException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AreasValidator implements Validator {

    private final AreasRepository areasRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AreaDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AreaDTO areaDTO = (AreaDTO) target;
        Optional<Area> areaToFind = areasRepository.findByCode(areaDTO.getCode());
        if (areaToFind.isPresent()) throw new AreaRegistrationException("Район с таким кодом уже существует");
    }

    public boolean isAreaActual(Long areaId) {
        return areasRepository.findById(areaId).isPresent();
    }
}
