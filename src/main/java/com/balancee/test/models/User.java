package com.balancee.test.models;

import jakarta.persistence.*;
//lombrok to generate getters, setters, constructors, and other boilerplate code, to reduce code clutter.
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;
    @Column(name = "user_name",
        nullable = false,
    unique = true)
    private String userName;
    @Column(name = "password",
            nullable = false)
    private String password;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private Date updatedAt;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

// Getters and setters done by lombok
}
