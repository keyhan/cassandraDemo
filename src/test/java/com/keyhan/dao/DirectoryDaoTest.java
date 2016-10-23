package com.keyhan.dao;

import com.keyhan.CassandraDemoApplication;
import com.keyhan.model.Person;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by keyhan on 2016-10-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CassandraDemoApplication.class})
public class DirectoryDaoTest {

    @Autowired
    DirectoryDao dao;

    @BeforeClass
    public static void init() throws  Exception{
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
    }

    @Test
    public void testAdd() {
        Person keyhan = Person.builder().firstName("Keyhan").lastName("Hadjari").dob(new Date()).build();
        dao.addPerson(keyhan);

        System.out.printf("Read Values from database = " + dao.getPersons());

    }
}
