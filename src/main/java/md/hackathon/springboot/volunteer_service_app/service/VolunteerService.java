package md.hackathon.springboot.volunteer_service_app.service;

import md.hackathon.springboot.volunteer_service_app.dto.VolunteerDto;
import md.hackathon.springboot.volunteer_service_app.enums.DayOfWeek;
import md.hackathon.springboot.volunteer_service_app.enums.Skill;
import md.hackathon.springboot.volunteer_service_app.model.Volunteer;

import java.util.List;
import java.util.Set;

public interface VolunteerService {

    Volunteer registerVolunteer(VolunteerDto volunteerDto);

    Volunteer getVolunteer(Long id);

    List<Volunteer> getAllVolunteers();

    Volunteer updateVolunteer(Long id, VolunteerDto volunteerDto);

    void deleteVolunteer(Long id);

    List<Volunteer> findVolunteersBySkills(Set<Skill> skills);

    List<Volunteer> findVolunteersByAvailability(Set<DayOfWeek> days);
}