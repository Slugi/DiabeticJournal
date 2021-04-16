package pl.diabeticjournal.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.diabeticjournal.entity.Insulin;
import pl.diabeticjournal.repository.InsulinRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class InsulinService {
    private InsulinRepository insulinRepo;

    public void addInsulin(Insulin insulin){
        insulin.setName(insulin.getName());
        insulin.setType(insulin.getType());
        insulinRepo.save(insulin);
    }

    public List<Insulin> insulinList(){
        return insulinRepo.findAll();
    }
}
