package pl.diabeticjournal.entity;

import javax.persistence.*;

@Entity
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int age;

  private int weight;

  private int height;

  private String sex;

  @OneToOne private User user;
}
