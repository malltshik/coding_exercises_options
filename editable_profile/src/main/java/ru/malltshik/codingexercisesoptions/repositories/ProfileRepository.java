package ru.malltshik.codingexercisesoptions.repositories;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.malltshik.codingexercisesoptions.exceptions.RepositoryException;
import ru.malltshik.codingexercisesoptions.models.Profile;
import ru.malltshik.codingexercisesoptions.services.ProfileService;

import java.util.List;

/**
 * Repository provide methods to work with a profile.
 * Current version support simple CRUD operations.
 *
 * @author Artem Gavrilov
 */
public interface ProfileRepository {

    /**
     * Find all profiles
     *
     * @return list of profiles
     */
    @NonNull
    List<Profile> findAll() throws RepositoryException;

    /**
     * Find one profile by primary key
     *
     * @param id primary key (should be not null)
     * @return {@link Profile} object or <strong>null</strong> if profile hasn't been found.
     */
    @Nullable
    Profile getOne(@NonNull Long id) throws RepositoryException;

    /**
     * Save or update method for persisting profile data into storage.
     * If {@param profile} has an {@link Profile#id} that means we are going to update existed profile.
     * Otherwise we'll be creating new record in storage.
     *
     * @param profile entity to save
     * @return {@link Profile} object which has been stored with primary key
     */
    @NonNull
    Profile save(Profile profile) throws RepositoryException;

    /**
     * Delete profile record from storage
     *
     * @param id primary key (should be not null)
     */
    void delete(@NonNull Long id) throws RepositoryException;

    /**
     * Delete profile record from storage.
     * Actually do {@link ProfileService#delete(Long)}
     *
     * @param profile entity to remove (should has id)
     */
    default void delete(@NonNull Profile profile) throws RepositoryException {
        delete(profile.getId());
    }

}
