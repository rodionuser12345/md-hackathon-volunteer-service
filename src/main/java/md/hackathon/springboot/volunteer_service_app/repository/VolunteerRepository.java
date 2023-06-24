package md.hackathon.springboot.volunteer_service_app.repository;

import md.hackathon.springboot.volunteer_service_app.enums.DayOfWeek;
import md.hackathon.springboot.volunteer_service_app.enums.Skill;
import md.hackathon.springboot.volunteer_service_app.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Volunteer findByUsername(String username);

    Volunteer findByEmail(String email);

    List<Volunteer> findBySkillsIn(Set<Skill> skills);

    List<Volunteer> findByAvailabilityIn(Set<DayOfWeek> days);
}
