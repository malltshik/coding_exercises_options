package ru.malltshik.codingexercisesoptions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malltshik.codingexercisesoptions.exceptions.http.NotFoundException;
import ru.malltshik.codingexercisesoptions.models.Profile;
import ru.malltshik.codingexercisesoptions.services.ProfileService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO write integration test for this API
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping({"", "/"})
    public List<Profile> getAll() {
        return profileService.findAll();
    }

    @GetMapping({"/{id}", "/{id}/"})
    public Profile getOne(@PathVariable Long id) throws NotFoundException {
        Profile one = profileService.getOne(id);
        if (one == null) throw new NotFoundException(String.format("Profile with id %s not found", id));
        return one;
    }

    @PostMapping({"", "/"})
    public Profile saveProfile(@Valid @RequestBody Profile profile) {
        return profileService.save(profile);
    }


    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Profile> deleteProfile(@PathVariable Long id) {
        profileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
