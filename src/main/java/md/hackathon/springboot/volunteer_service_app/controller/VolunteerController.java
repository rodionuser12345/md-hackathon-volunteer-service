package md.hackathon.springboot.volunteer_service_app.controller;

import jakarta.validation.Valid;
import md.hackathon.springboot.volunteer_service_app.dto.UserDto;
import md.hackathon.springboot.volunteer_service_app.dto.VolunteerDto;
import md.hackathon.springboot.volunteer_service_app.enums.DayOfWeek;
import md.hackathon.springboot.volunteer_service_app.enums.Skill;
import md.hackathon.springboot.volunteer_service_app.mapper.VolunteerMapper;
import md.hackathon.springboot.volunteer_service_app.model.Volunteer;
import md.hackathon.springboot.volunteer_service_app.service.VolunteerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final VolunteerServiceImpl volunteerServiceImpl;
    private final VolunteerMapper volunteerMapper;

    private final RestTemplate restTemplate;

    @Autowired
    public VolunteerController(VolunteerServiceImpl volunteerServiceImpl, VolunteerMapper volunteerMapper,
                               RestTemplate restTemplate) {
        this.volunteerServiceImpl = volunteerServiceImpl;
        this.volunteerMapper = volunteerMapper;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/register/{username}")
    public ResponseEntity<VolunteerDto> registerVolunteer(@Valid @PathVariable String username) {
        String userServiceUrl = "http://user-service/api/users/username/" + username;
        UserDto userDto = restTemplate.getForObject(userServiceUrl, UserDto.class);

        VolunteerDto volunteerDto = new VolunteerDto();
        volunteerDto.setUsername(userDto.getUsername());
        volunteerDto.setFirstName(userDto.getFirstName());
        volunteerDto.setLastName(userDto.getLastName());
        volunteerDto.setEmail(userDto.getEmail());

        Volunteer volunteer = volunteerServiceImpl.registerVolunteer(volunteerDto);
        VolunteerDto responseDto = volunteerMapper.toDto(volunteer);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDto> getVolunteerById(@PathVariable Long id) {
        Volunteer volunteer = volunteerServiceImpl.getVolunteer(id);
        return new ResponseEntity<>(volunteerMapper.toDto(volunteer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VolunteerDto>> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerServiceImpl.getAllVolunteers();
        List<VolunteerDto> volunteerDtos = volunteers.stream().map(volunteerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(volunteerDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerDto> updateVolunteer(@PathVariable Long id, @RequestBody VolunteerDto volunteerDto) {
        Volunteer volunteer = volunteerServiceImpl.updateVolunteer(id, volunteerDto);
        return new ResponseEntity<>(volunteerMapper.toDto(volunteer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable Long id) {
        volunteerServiceImpl.deleteVolunteer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/skills")
    public ResponseEntity<List<VolunteerDto>> findVolunteersBySkills(@RequestParam Set<Skill> skills) {
        List<Volunteer> volunteers = volunteerServiceImpl.findVolunteersBySkills(skills);
        List<VolunteerDto> volunteerDtos = volunteers.stream().map(volunteerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(volunteerDtos, HttpStatus.OK);
    }

    @GetMapping("/availability")
    public ResponseEntity<List<VolunteerDto>> findVolunteersByAvailability(@RequestParam Set<DayOfWeek> days) {
        List<Volunteer> volunteers = volunteerServiceImpl.findVolunteersByAvailability(days);
        List<VolunteerDto> volunteerDtos = volunteers.stream().map(volunteerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(volunteerDtos, HttpStatus.OK);
    }
}
