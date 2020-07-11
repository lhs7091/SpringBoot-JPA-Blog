package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // user class is generated on the MySQL table;
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it follows DB number connetected 
    private int id; // sequence, auto-increment

    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // -> hash encryption
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; // admin, user, manager, Enum type is better

    @CreationTimestamp // input time automatically
    private Timestamp createdate;
    
}