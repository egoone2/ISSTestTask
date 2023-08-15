package ru.osokin.farmerregistry.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AreaErrorResponse {

    private String message;
    private long timestamp;

}
