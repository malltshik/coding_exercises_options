package ru.malltshik.codingexercisesoptions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import ru.malltshik.codingexercisesoptions.exceptions.ValidationModelException;
import ru.malltshik.codingexercisesoptions.exceptions.http.NotFoundException;
import ru.malltshik.codingexercisesoptions.models.Profile;
import ru.malltshik.codingexercisesoptions.services.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final Validator validator;

    @Autowired
    public ProfileController(ProfileService profileService,
                             @Qualifier("defaultValidator") Validator validator) {
        this.profileService = profileService;
        this.validator = validator;
    }

    @GetMapping("/")
    public List<Profile> getAll() {
        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public Profile getOne(@PathVariable Long id) throws NotFoundException {
        Profile one = profileService.getOne(id);
        if (one == null) throw new NotFoundException(String.format("Profile with id %s not found", id));
        return one;
    }

    @PostMapping("/")
    public ResponseEntity<Profile> saveProfile(@RequestBody Profile profile, Errors errors) throws ValidationModelException {
        validator.validate(profile, errors);
        if (errors.hasErrors()) throw new ValidationModelException("Profile validation failed", errors);
        return new ResponseEntity<>(profileService.save(profile), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfile(@RequestBody Profile profile, Errors errors) {
        validator.validate(profile, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(profileService.save(profile), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable Long id) {
        profileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
