package ru.osokin.farmerregistry;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.osokin.farmerregistry.dto.FarmerDTO;
import ru.osokin.farmerregistry.entity.Farmer;

@SpringBootApplication
public class FarmerRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmerRegistryApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Farmer, FarmerDTO> propertyMapper = modelMapper.createTypeMap(Farmer.class, FarmerDTO.class);
        propertyMapper.addMappings(mapper -> mapper.map(f -> f.getRegistrationArea().getId(), FarmerDTO::setRegistrationAreaId));
        return modelMapper;
    }

}
