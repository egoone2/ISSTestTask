package ru.osokin.farmerregistry.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String name;
    @Column
    private Long code;
    @Column(name = "is_archive")
    private Boolean isArchive;

    @OneToMany(mappedBy = "registrationArea")
    private List<Farmer> registeredFarmers;

}
