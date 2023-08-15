package ru.osokin.farmerregistry.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.osokin.farmerregistry.entity.OPF;

import java.util.Date;

@Data
@NoArgsConstructor
public class FarmerDTO {

    private String name;
    private OPF opf;
    @Pattern(regexp = "^[\\d+]{10,12}$")
    private String INN;
    @Pattern(regexp = "^[\\d+]{9}$")
    private String KPP;
    @Pattern(regexp = "^[\\d+]{13}$")
    private String OGRN;
    private Long registrationAreaId;
    private Date registrationDate;
    private Boolean isArchive;
}
