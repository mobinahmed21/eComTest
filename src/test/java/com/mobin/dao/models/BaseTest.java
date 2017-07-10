package com.mobin.dao.models;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mobin.config.SpringCongurationLocal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringCongurationLocal.class}, webEnvironment = WebEnvironment.NONE)
public abstract class BaseTest {

}
