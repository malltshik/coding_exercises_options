package ru.malltshik.codingexercisesoptions.repositories.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.malltshik.codingexercisesoptions.exceptions.RepositoryException;
import ru.malltshik.codingexercisesoptions.models.Profile;
import ru.malltshik.codingexercisesoptions.repositories.ProfileRepository;

import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.*;

/**
 * See javadoc there {@link ProfileRepository}
 */
@Repository
public class ProfileJDBCRepository implements ProfileRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileJDBCRepository.class);

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public ProfileJDBCRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Profile> findAll() throws RepositoryException {
        try {
            return template.query("SELECT * FROM profile", emptyMap(), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return emptyList();
        } catch (DataAccessException e) {
            String message = "Unable to find all profile rows";
            LOGGER.error(message, e);
            throw new RepositoryException(message, e);
        }
    }

    @Nullable
    @Override
    public Profile getOne(Long id) throws RepositoryException {
        try {
            return template.queryForObject("SELECT * FROM profile WHERE id=:id", singletonMap("id", id), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            String message = format("Unable to find profile with id: %s", id);
            LOGGER.error(message, e);
            throw new RepositoryException(message, e);
        }

    }

    @Override
    public Profile save(Profile profile) throws RepositoryException {
        try {
            if (profile.getId() != null) {
                template.update(UPDATE_QUERY, getParamSourceFromProfile(profile));
            } else {
                GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
                template.update(INSERT_QUERY, getParamSourceFromProfile(profile), keyHolder, new String[]{"id"});
                profile.setId(keyHolder.getKey().longValue());
            }
        } catch (DataAccessException e) {
            String message = format("Unable to save profile: %s", profile);
            LOGGER.error(message, e);
            throw new RepositoryException(message, e);
        }
        return getOne(profile.getId());
    }

    @Override
    public void delete(Long id) throws RepositoryException {
        try {
            template.update("DELETE FROM profile WHERE id=:id", singletonMap("id", id));
        } catch (DataAccessException e) {
            String message = format("Unable to remove profile with id: %s", id);
            LOGGER.error(message, e);
            throw new RepositoryException(message, e);
        }
    }

    private final RowMapper<Profile> rowMapper = (rs, i) -> {
        Profile profile = new Profile();
        profile.setId(rs.getLong("id"));
        profile.setDisplayName(rs.getString("display_name"));
        profile.setRealName(rs.getString("real_name"));
        profile.setPicture(rs.getBytes("picture"));
        profile.setBirthday(rs.getTimestamp("birthday"));
        profile.setGender(rs.getString("gender"));
        profile.setEthnicity(rs.getString("ethnicity"));
        profile.setReligion(rs.getString("religion"));
        profile.setHeight(rs.getInt("height"));
        profile.setFigure(rs.getString("figure"));
        profile.setMaritalStatus(rs.getString("marital_status"));
        profile.setOccupation(rs.getString("occupation"));
        profile.setAboutMe(rs.getString("about_me"));
        profile.setLocation(rs.getString("location"));
        return profile;
    };

    private MapSqlParameterSource getParamSourceFromProfile(Profile profile) {
        return new MapSqlParameterSource()
                .addValue("display_name", profile.getDisplayName())
                .addValue("real_name", profile.getRealName())
                .addValue("picture", profile.getPicture())
                .addValue("birthday", profile.getBirthday())
                .addValue("gender", profile.getGender())
                .addValue("ethnicity", profile.getEthnicity())
                .addValue("religion", profile.getReligion())
                .addValue("figure", profile.getFigure())
                .addValue("marital_status", profile.getMaritalStatus())
                .addValue("occupation", profile.getOccupation())
                .addValue("about_me", profile.getAboutMe())
                .addValue("location", profile.getLocation())
                .addValue("height", profile.getHeight());
    }

    private final static String INSERT_QUERY = "INSERT INTO profile " +
            "(display_name, real_name, picture, birthday, gender, ethnicity, religion, height, figure, " +
            "marital_status,occupation, about_me, location) VALUES " +
            "(:display_name, :real_name, :picture, :birthday, :gender, :ethnicity, :religion, :height,:figure," +
            ":marital_status,:occupation,:about_me,:location)";

    private final static String UPDATE_QUERY = "UPDATE profile SET DISPLAY_NAME=:display_name, REAL_NAME=:real_name, " +
            "PICTURE=:picture, BIRTHDAY=:birthday, GENDER=:gender, ETHNICITY=:ethnicity, RELIGION=:religion, " +
            "FIGURE=:figure, MARITAL_STATUS=:marital_status,OCCUPATION=:occupation," +
            "ABOUT_ME=:about_me,LOCATION=:location";
}
