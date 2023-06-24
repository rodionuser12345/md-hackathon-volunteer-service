package md.hackathon.springboot.volunteer_service_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.hackathon.springboot.volunteer_service_app.enums.DayOfWeek;
import md.hackathon.springboot.volunteer_service_app.enums.Skill;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Skill> skills;
    private Set<DayOfWeek> availability;

}
