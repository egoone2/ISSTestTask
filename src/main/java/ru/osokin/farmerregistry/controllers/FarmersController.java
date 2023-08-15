package ru.osokin.farmerregistry.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.osokin.farmerregistry.dto.FarmerDTO;
import ru.osokin.farmerregistry.entity.Farmer;
import ru.osokin.farmerregistry.service.FarmersService;
import ru.osokin.farmerregistry.util.FarmerErrorResponse;
import ru.osokin.farmerregistry.util.FarmersValidator;
import ru.osokin.farmerregistry.util.exceptions.FarmerRegistrationException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farmers")
public class FarmersController {

    private final FarmersService farmersService;
    private final FarmersValidator validator;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<FarmerDTO> getAll() {
        return farmersService.findAll();
    }

    @GetMapping("/{id}")
    public FarmerDTO getById(@PathVariable Long id) {
        Farmer farmer = farmersService.findById(id);
        return modelMapper.map(farmer, FarmerDTO.class);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registerFarmer(@RequestBody FarmerDTO farmerDTO,
                                                     BindingResult bindingResult) {
        validator.validate(farmerDTO, bindingResult);
        farmersService.registerFarmer(farmerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateFarmer(@PathVariable Long id,
                                                   @RequestBody FarmerDTO farmerDTO,
                                                   BindingResult bindingResult) {
        validator.validate(farmerDTO, bindingResult);
        farmersService.update(id, farmerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> archive(@RequestBody Long id) {
        farmersService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFarmer(@PathVariable Long id) {
        farmersService.removeFarmer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler
    public ResponseEntity<FarmerErrorResponse> handleException(FarmerRegistrationException e) {
        FarmerErrorResponse response = new FarmerErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
