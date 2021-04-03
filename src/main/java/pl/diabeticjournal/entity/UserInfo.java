package pl.diabeticjournal.entity;

import lombok.Data;

import javax.persistence.*;

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

  @OneToOne private User user;
}
