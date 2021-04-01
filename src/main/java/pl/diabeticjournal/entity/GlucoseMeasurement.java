package pl.diabeticjournal.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime measurementDateTime;

    private int glucoseLevel;

    private String stress;

    private String activityBeforeMeasurement;

    private String mood;

    private String comments;
}
