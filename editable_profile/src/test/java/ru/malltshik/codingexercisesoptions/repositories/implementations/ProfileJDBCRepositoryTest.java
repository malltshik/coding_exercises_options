package ru.malltshik.codingexercisesoptions.repositories.implementations;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.malltshik.codingexercisesoptions.models.Profile;
import ru.malltshik.codingexercisesoptions.repositories.ProfileRepository;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * TODO add context configuration. {@link ProfileRepository} only one bean we have to inject
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfileJDBCRepositoryTest {

    @Autowired
    private ProfileRepository repository;

    private static Long targetId = null;

    @Test
    public void test01_save() throws Exception {
        Profile profile = Profile.builder()
                .displayName("Display Name")
                .realName("Real name")
                .birthday(new Date())
                .gender("Male")
                .maritalStatus("Never Married")
                .location("St.Petersburt (59°57'N, 30°18'E)")
                .build();
        Profile saved = repository.save(profile);
        assertNotNull(saved.getId());
        targetId = saved.getId();
    }

    @Test
    public void test02_findAll() throws Exception {
        List<Profile> profiles = repository.findAll();
        assertFalse(profiles.isEmpty());
        Profile profile = profiles.stream().filter(p -> p.getId().equals(targetId)).findFirst().orElse(null);
        assertNotNull(profile);
    }

    @Test
    public void test03_getOne() throws Exception {
        Profile one = repository.getOne(-42L);
        assertNull(one);
        one = repository.getOne(targetId);
        assertNotNull(one);
        assertNotNull(one.getId());
    }

    @Test
    public void test04_delete() throws Exception {
        repository.delete(targetId);
        Profile one = repository.getOne(targetId);
        assertNull(one);
    }

}