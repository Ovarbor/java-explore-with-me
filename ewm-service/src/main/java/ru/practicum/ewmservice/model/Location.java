package ru.practicum.ewmservice.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", insertable = false)
    private Long id;

    @Column(name = "location_lat")
    @NotNull
    private Float lat;

    @Column(name = "location_lon")
    @NotNull
    private Float lon;

    @Column(name = "radius")
    private Float radius;

    @Column(name = "location_name")
    private String name;
}
