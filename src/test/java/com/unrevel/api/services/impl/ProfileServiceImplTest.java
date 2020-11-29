package com.unrevel.api.services.impl;

import com.unrevel.api.model.Profile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProfileServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
    }

    @Test
    void findByUseName() {
    }

    @Test
    void save() {
        Profile profile = new Profile();
        profile.setProfileImageUrl("link demo ");
        profile.setSocialMediaLinks(List.of("facebook","insta ","blog"));

    }
    @Test
    void  qustionReleventSave(){

    }
}