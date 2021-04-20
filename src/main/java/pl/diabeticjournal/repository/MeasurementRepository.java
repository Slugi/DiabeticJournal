package pl.diabeticjournal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.diabeticjournal.entity.GlucoseMeasurement;

@Repository
public interface MeasurementRepository extends JpaRepository<GlucoseMeasurement, Long> {
}
