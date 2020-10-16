package com.rolandohuber.minesweeper.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Game.USER_ATTRIBUTE)
    private List<Game> games;
}