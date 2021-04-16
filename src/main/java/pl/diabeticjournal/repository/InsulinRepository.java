package pl.diabeticjournal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.diabeticjournal.entity.Insulin;



@Repository
public interface InsulinRepository extends JpaRepository<Insulin,Long> {

}
