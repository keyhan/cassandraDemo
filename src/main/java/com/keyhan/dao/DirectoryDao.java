package com.keyhan.dao;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;
import com.keyhan.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.datastax.driver.core.ConsistencyLevel.ONE;
import static com.datastax.driver.core.ConsistencyLevel.QUORUM;
import static com.datastax.driver.mapping.Mapper.Option.consistencyLevel;
import static com.datastax.driver.mapping.Mapper.Option.saveNullFields;
import static com.datastax.driver.mapping.Mapper.Option.tracing;

/**
 * Created by keyhan on 2016-10-22.
 */
@Component
public class DirectoryDao {

    //@Autowired
    //ListenableFuture<Session> session;
    Session session;

    //@Autowired
    MappingManager mappingManager;

    Mapper<Person> personMapper;


    public DirectoryDao() {
        session = session();
        mappingManager = mappingManager();
        personMapper = mappingManager.mapper(Person.class);
        personMapper.setDefaultGetOptions(tracing(true),consistencyLevel(QUORUM));
        personMapper.setDefaultSaveOptions(saveNullFields(false));
        personMapper.setDefaultDeleteOptions(consistencyLevel(ONE));
        //session = cluster.connectAsync();

    }

    public List<Person> getPersons() {
        ResultSet rs = session.execute("select * from directory.person");
        return personMapper.map(rs).all();
    }

    public void addPerson(Person person) {
        personMapper.save(person);
    }

    public Cluster cluster() {
        return Cluster.builder()
                .addContactPoint("127.0.0.1")
                .build();
    }

    public Session session() {
        return cluster().connect();
    }

    MappingManager mappingManager() {
        return new MappingManager(session());
    }



//    public ListenableFuture<ResultSet> getAllPersons() {
//        ListenableFuture<ResultSet> resultSet = Futures.transform(session,
//                new AsyncFunction<Session, ResultSet>() {
//                    @Override
//                    public ListenableFuture<ResultSet> apply(Session session) throws Exception {
//                        return session.executeAsync("select * from directory.person");
//                    }
//                });
//        return resultSet;
//    }
}
