package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length=100)
    private String title;

    @Lob // big data
    private String content; // including <html>tag

    @ColumnDefault("0")
    private int count;

    @ManyToOne // Many = board, one = user
    @JoinColumn(name="userId")
    private User user; // Object cannot save in DB. FK can use.

    @CreationTimestamp
    private Timestamp createDate;
    
}