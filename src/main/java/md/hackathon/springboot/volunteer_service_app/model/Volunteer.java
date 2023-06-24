package md.hackathon.springboot.volunteer_service_app.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.hackathon.springboot.volunteer_service_app.enums.DayOfWeek;
import md.hackathon.springboot.volunteer_service_app.enums.Skill;

import java.util.Set;

@Entity
@Table(name = "volunteers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "volunteer_skills")
    @Column(name = "skill")
    private Set<Skill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "volunteer_availability")
    @Column(name = "day_of_week")
    private Set<DayOfWeek> availability;

    // Getters and setters
}

