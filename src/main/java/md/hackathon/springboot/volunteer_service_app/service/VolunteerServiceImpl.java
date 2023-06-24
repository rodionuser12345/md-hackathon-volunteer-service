package md.hackathon.springboot.volunteer_service_app.service;

import md.hackathon.springboot.volunteer_service_app.dto.VolunteerDto;
import md.hackathon.springboot.volunteer_service_app.enums.DayOfWeek;
import md.hackathon.springboot.volunteer_service_app.enums.Skill;
import md.hackathon.springboot.volunteer_service_app.exception.VolunteerNotFoundException;
import md.hackathon.springboot.volunteer_service_app.mapper.VolunteerMapper;
import md.hackathon.springboot.volunteer_service_app.model.Volunteer;
import md.hackathon.springboot.volunteer_service_app.repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    private final VolunteerMapper volunteerMapper;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository, VolunteerMapper volunteerMapper) {
        this.volunteerRepository = volunteerRepository;
        this.volunteerMapper = volunteerMapper;
    }

    @Override
    public Volunteer registerVolunteer(VolunteerDto volunteerDto) {
        Volunteer volunteer = volunteerMapper.toEntity(volunteerDto);
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer getVolunteer(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new VolunteerNotFoundException("Volunteer not found"));
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer updateVolunteer(Long id, VolunteerDto volunteerDto) {
        Volunteer volunteer = getVolunteer(id);
        volunteerMapper.updateEntityFromDto(volunteerDto, volunteer);
        return volunteerRepository.save(volunteer);
    }

    @Override
    public void deleteVolunteer(Long id) {
        Volunteer volunteer = getVolunteer(id);
        volunteerRepository.delete(volunteer);
    }

    @Override
    public List<Volunteer> findVolunteersBySkills(Set<Skill> skills) {
        return volunteerRepository.findBySkillsIn(skills);
    }

    @Override
    public List<Volunteer> findVolunteersByAvailability(Set<DayOfWeek> days) {
        return volunteerRepository.findByAvailabilityIn(days);
    }
}