package ru.practicum.ewmservice.model;
<<<<<<< HEAD
import lombok.Data;
=======
import lombok.Getter;
import lombok.Setter;
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
import lombok.ToString;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

<<<<<<< HEAD
@Data
=======
@Getter
@Setter
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
@ToString
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "lat", column = @Column(name = "location_lat")),
<<<<<<< HEAD
        @AttributeOverride(name = "lon", column = @Column(name = "location_lon")),
=======
        @AttributeOverride(name = "lon", column = @Column(name = "location_lon"))
>>>>>>> 3eeb1a23f1e913a3fb132a5491dbfc159437dec3
})
public class Location {

    private Float lat;

    private Float lon;
}
