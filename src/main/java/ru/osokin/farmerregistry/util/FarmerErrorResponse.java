package ru.osokin.farmerregistry.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FarmerErrorResponse {

    private String message;
    private long timestamp;

}