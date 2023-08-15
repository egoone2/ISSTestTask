package ru.osokin.farmerregistry.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.osokin.farmerregistry.dto.AreaDTO;
import ru.osokin.farmerregistry.entity.Area;
import ru.osokin.farmerregistry.service.AreasService;
import ru.osokin.farmerregistry.util.AreaErrorResponse;
import ru.osokin.farmerregistry.util.AreasValidator;
import ru.osokin.farmerregistry.util.exceptions.AreaNotFoundException;
import ru.osokin.farmerregistry.util.exceptions.AreaRegistrationException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/areas")
public class AreasController {

    private final AreasService areasService;
    private final ModelMapper modelMapper;
    private final AreasValidator validator;


    @GetMapping
    public List<AreaDTO> getAll() {
        return areasService.findAll();
    }

    @GetMapping("/{id}")
    public AreaDTO getAreaById(@PathVariable Long id) {
        return modelMapper.map(areasService.findById(id), AreaDTO.class);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addArea(@RequestBody AreaDTO areaDTO,
                                              BindingResult bindingResult) {

        validator.validate(areaDTO, bindingResult);

        Area area = modelMapper.map(areaDTO, Area.class);
        areasService.addArea(area);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteArea(@PathVariable Long id) {
        areasService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateArea(@PathVariable Long id,
                                                 @RequestBody AreaDTO areaDTO,
                                                 BindingResult bindingResult) {
        validator.validate(areaDTO, bindingResult);

        areasService.updateArea(id, modelMapper.map(areaDTO, Area.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping
    public ResponseEntity<HttpStatus> archive(@RequestBody Long id) {
        areasService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler
    public ResponseEntity<AreaErrorResponse> handleException(AreaRegistrationException e) {
        AreaErrorResponse response = new AreaErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AreaErrorResponse> handleException(AreaNotFoundException e) {
        AreaErrorResponse response = new AreaErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
