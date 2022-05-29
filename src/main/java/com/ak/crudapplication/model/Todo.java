package com.ak.crudapplication.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Todo {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private TodoStatus todoStatus;

    @CreationTimestamp
    @Column(updatable = false)
    ZonedDateTime dateCreated;

    @UpdateTimestamp
    ZonedDateTime lastModified;

}
