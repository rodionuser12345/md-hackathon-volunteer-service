package md.hackathon.springboot.volunteer_service_app.mapper;

import md.hackathon.springboot.volunteer_service_app.dto.VolunteerDto;
import md.hackathon.springboot.volunteer_service_app.model.Volunteer;
import org.springframework.stereotype.Component;

@Component
public class VolunteerMapper {

    public VolunteerDto toDto(Volunteer volunteer) {
        VolunteerDto dto = new VolunteerDto();
        dto.setId(volunteer.getId());
        dto.setUsername(volunteer.getUsername());
        dto.setFirstName(volunteer.getFirstName());
        dto.setLastName(volunteer.getLastName());
        dto.setEmail(volunteer.getEmail());
        dto.setSkills(volunteer.getSkills());
        dto.setAvailability(volunteer.getAvailability());
        return dto;
    }

    public Volunteer toEntity(VolunteerDto dto) {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(dto.getId());
        volunteer.setUsername(dto.getUsername());
        volunteer.setFirstName(dto.getFirstName());
        volunteer.setLastName(dto.getLastName());
        volunteer.setEmail(dto.getEmail());
        volunteer.setSkills(dto.getSkills());
        volunteer.setAvailability(dto.getAvailability());
        return volunteer;
    }

    public void updateEntityFromDto(VolunteerDto dto, Volunteer volunteer) {
        volunteer.setUsername(dto.getUsername());
        volunteer.setFirstName(dto.getFirstName());
        volunteer.setLastName(dto.getLastName());
        volunteer.setEmail(dto.getEmail());
        volunteer.setSkills(dto.getSkills());
        volunteer.setAvailability(dto.getAvailability());
    }
}