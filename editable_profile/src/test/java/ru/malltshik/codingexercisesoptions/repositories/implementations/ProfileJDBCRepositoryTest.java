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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
    }

    @Test
    public void test02_findAll() throws Exception {
        List<Profile> profiles = repository.findAll();
        assertEquals(profiles.size(), 1);
        Profile profile = profiles.get(0);
        assertNotNull(profile.getId());
    }

    @Test
    public void test03_getOne() throws Exception {
        Profile one = repository.getOne(-42L);
        assertNull(one);
        one = repository.getOne(1L);
        assertNotNull(one);
        assertNotNull(one.getId());
    }

    @Test
    public void test04_delete() throws Exception {
        repository.delete(1L);
        Profile one = repository.getOne(1L);
        assertNull(one);
    }

}