package pl.diabeticjournal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private int diabeticType;

    private String email;

    private String password;

    private String role;
}




