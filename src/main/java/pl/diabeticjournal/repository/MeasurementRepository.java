package pl.diabeticjournal.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.diabeticjournal.entity.GlucoseMeasurement;
import pl.diabeticjournal.entity.User;

import java.util.List;
import java.util.Optional;

@Repository

public interface MeasurementRepository extends JpaRepository<GlucoseMeasurement, Long> {
    @EntityGraph(attributePaths = {"user", "insulin"})
    Optional<List<GlucoseMeasurement>> findGlucoseMeasurementByUser(User user);

    @EntityGraph(attributePaths = {"user", "insulin"})
    Optional<GlucoseMeasurement> findByUser(User user);

    @EntityGraph(attributePaths = {"user", "insulin"})
    Optional<GlucoseMeasurement> findGlucoseMeasurementById(Long id);
}
