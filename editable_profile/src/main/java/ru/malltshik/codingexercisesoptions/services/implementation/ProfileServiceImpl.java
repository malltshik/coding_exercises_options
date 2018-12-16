package ru.malltshik.codingexercisesoptions.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.malltshik.codingexercisesoptions.exceptions.RepositoryException;
import ru.malltshik.codingexercisesoptions.models.Profile;
import ru.malltshik.codingexercisesoptions.repositories.ProfileRepository;
import ru.malltshik.codingexercisesoptions.services.ProfileService;

import java.util.List;

import static java.lang.String.format;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> findAll() {
        try {
            return profileRepository.findAll();
        } catch (RepositoryException e) {
            throw new RuntimeException("Unable to get list of profiles", e);
        }
    }

    @Nullable
    @Override
    public Profile getOne(Long id) {
        try {
            return profileRepository.getOne(id);
        } catch (RepositoryException e) {
            throw new RuntimeException(format("Unable to get profile with id: %s", id), e);
        }
    }

    @Override
    public Profile save(Profile profile) {
        try {
            return profileRepository.save(profile);
        } catch (RepositoryException e) {
            throw new RuntimeException(format("Unable to save profile: %s", profile), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            profileRepository.delete(id);
        } catch (RepositoryException e) {
            throw new RuntimeException(format("Unable to delete profile with id: %s", id), e);
        }
    }
}
