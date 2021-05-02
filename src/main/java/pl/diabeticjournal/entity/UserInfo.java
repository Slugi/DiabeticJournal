package pl.diabeticjournal.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int age;

  private int weight;

  private int height;

  private String gender;

  @Length(min = 5, message = "Imię musi mieć conajmniej 3 znaki")
  @NotEmpty(message = "Podaj imię")
  private String firstName;

  @Length(min = 2, message = "Nazwisko musi mieć conajmniej 2 znaki")
  @NotEmpty(message = "Podaj nazwisko")
  private String lastName;


  @Min(1)
  @Max(2)
  private int diabeticType;

@OneToOne
  private User user;
}
