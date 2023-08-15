package ru.osokin.farmerregistry.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.osokin.farmerregistry.dto.FarmerDTO;
import ru.osokin.farmerregistry.entity.Area;
import ru.osokin.farmerregistry.repositories.AreasRepository;
import ru.osokin.farmerregistry.util.exceptions.FarmerRegistrationException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FarmersValidator implements Validator {

    private final AreasRepository areasRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(FarmerDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FarmerDTO farmerDTO = (FarmerDTO) target;
        Optional<Area> registrationArea = areasRepository.findById(farmerDTO.getRegistrationAreaId());
        if (registrationArea.isEmpty())
            throw new FarmerRegistrationException("Такого района регистрации не существует");
    }


}
