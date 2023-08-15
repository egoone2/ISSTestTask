package ru.osokin.farmerregistry.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AreaDTO {
    private String name;
    private Long code;
    private Boolean isArchive;
}
