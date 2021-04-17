package pl.diabeticjournal.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class GlucoseMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Insulin insulin;

    private int insulinUnits;

    private LocalDateTime measurementDateTime = LocalDateTime.now();

    private int glucoseLevel;

    private String stress;

    private String activityBeforeMeasurement;

    private String mood;

    private String comments;
}
