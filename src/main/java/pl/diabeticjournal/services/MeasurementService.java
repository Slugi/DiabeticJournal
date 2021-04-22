package pl.diabeticjournal.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.GlucoseMeasurement;
import pl.diabeticjournal.entity.Insulin;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.repository.MeasurementRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementService {

    private MeasurementRepository measurementRepo;
    private InsulinService insulinService;


    public void addMeasurement(GlucoseMeasurement measurement, User user, Insulin insulin) {
       measurement.setUser(user);
       measurement.setInsulin(insulinService.findInsulinByName(insulin.getName()));
       measurement.setGlucoseLevel(measurement.getGlucoseLevel());
       measurement.setInsulinUnits(measurement.getInsulinUnits());
       measurement.setMood(measurement.getMood());
       measurement.setActivityBeforeMeasurement(measurement.getActivityBeforeMeasurement());
       measurement.setStress(measurement.getStress());
       measurement.setComments(measurement.getComments());
       measurementRepo.save(measurement);
    }

    public List<GlucoseMeasurement> measurements(){
        return measurementRepo.findAll();
    }
}
