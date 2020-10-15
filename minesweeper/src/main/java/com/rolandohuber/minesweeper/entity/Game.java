package com.rolandohuber.minesweeper.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
public class Game extends Entity{
    public static final String USER_ATTRIBUTE = "user";
    private LocalDate date;
    private Long timer;
    private Integer flags;
    private Integer bombs;
    private Integer width;
    private Integer height;
    private Boolean finished;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Cell.GAME_ATTRIBUTE)
    private List<Cell> cells;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}
