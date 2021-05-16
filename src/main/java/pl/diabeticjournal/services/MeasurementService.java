package pl.diabeticjournal.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.GlucoseMeasurement;
import pl.diabeticjournal.entity.Insulin;
import pl.diabeticjournal.entity.User;
import pl.diabeticjournal.repository.MeasurementRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MeasurementService {

    private final MeasurementRepository measurementRepo;
    private final InsulinService insulinService;


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

    public List<GlucoseMeasurement> measurements() {
        return measurementRepo.findAll();
    }

    public List<GlucoseMeasurement> getAll() {
        return measurementRepo.findAll();
    }


    public List<GlucoseMeasurement> getMeasurementsByUser(User user) {
        return measurementRepo.findGlucoseMeasurementByUser(user).orElseThrow(()
                -> new RuntimeException("Nie znaleziono pomiarów użytkownika."));
    }

    public GlucoseMeasurement getMeasurementByUser(User user) {
        return measurementRepo.findByUser(user).orElseThrow(()
                -> new RuntimeException("Nie znaleziono takiego pomiaru"));
    }

    public GlucoseMeasurement getMeasurementById(Long id) {
        return measurementRepo.findGlucoseMeasurementById(id).orElseThrow(()
                -> new RuntimeException("Nie znaleziono takiego pomiaru"));
    }

    public void editMeasurement(GlucoseMeasurement measurement, User user) {
        measurement.setUser(user);
        measurement.setGlucoseLevel(measurement.getGlucoseLevel());
        measurement.setInsulinUnits(measurement.getInsulinUnits());
        measurement.setMood(measurement.getMood());
        measurement.setActivityBeforeMeasurement(measurement.getActivityBeforeMeasurement());
        measurement.setStress(measurement.getStress());
        measurement.setComments(measurement.getComments());
        measurementRepo.save(measurement);
    }

    public void deleteMeasurement(GlucoseMeasurement measurement) {
        measurementRepo.delete(measurement);
    }
}
