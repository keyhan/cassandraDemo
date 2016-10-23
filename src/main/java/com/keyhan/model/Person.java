package com.keyhan.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by keyhan on 2016-10-22.
 */
@Table(keyspace = "directory", name="person"
        , readConsistency = "QUORUM"
        ,writeConsistency = "QUORUM"
        , caseSensitiveKeyspace = false
        , caseSensitiveTable = false
        )
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class Person {
    @PartitionKey
    @Column(name = "personal_id")
    private UUID personal_id;

    private String firstName;

    private String lastName;

    private Date dob;
}
