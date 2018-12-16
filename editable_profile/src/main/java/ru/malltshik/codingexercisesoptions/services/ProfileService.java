package ru.malltshik.codingexercisesoptions.services;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.malltshik.codingexercisesoptions.models.Profile;

import java.util.List;

/**
 * Service provide methods to work with a profile.
 * Current version support simple CRUD operations.
 *
 * @author Artem Gavrilov
 */
public interface ProfileService {

    /**
     * Find all profiles
     *
     * @return list of profiles
     */
    @NonNull
    List<Profile> findAll();

    /**
     * Find one profile by primary key
     *
     * @param id primary key (should be not null)
     * @return {@link Profile} object or <strong>null</strong> if profile hasn't been found.
     */
    @Nullable
    Profile getOne(@NonNull Long id);

    /**
     * Save or update method for persisting profile data into storage.
     * If {@param profile} has an {@link Profile#id} that means we are going to update existed profile.
     * Otherwise we'll be creating new record in storage.
     *
     * @param profile entity to save
     * @return {@link Profile} object which has been stored with primary key
     */
    @NonNull
    Profile save(Profile profile);

    /**
     * Delete profile record from storage
     *
     * @param id primary key (should be not null)
     */
    void delete(@NonNull Long id);

    /**
     * Delete profile record from storage.
     * Actually do {@link ProfileService#delete(Long)}
     *
     * @param profile entity to remove (should has id)
     */
    default void delete(@NonNull Profile profile) {
        delete(profile.getId());
    }

}
