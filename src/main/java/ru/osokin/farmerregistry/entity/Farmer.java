package ru.osokin.farmerregistry.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private OPF opf;
    @Column
    @Pattern(regexp = "^[\\d+]{10,12}$")
    private String INN;
    @Column
    @Pattern(regexp = "^[\\d+]{9}$")
    private String KPP;
    @Column
    @Pattern(regexp = "^[\\d+]{13}$")
    private String OGRN;
    @ManyToOne
    @JoinColumn(name = "registration_area_id", referencedColumnName = "id")
    private Area registrationArea;

    @Column(name = "registration_date")
    @Temporal(value = TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "is_archive")
    private Boolean isArchive;

}
