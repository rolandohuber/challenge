package com.rolandohuber.minesweeper.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
@Getter
@Setter
public class Cell extends Entity{
    public static final String GAME_ATTRIBUTE = "game";
    private Integer positionX;
    private Integer positionY;
    private Integer value;
    private Boolean discovered;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CellType type;
    @ManyToOne(targetEntity = Game.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Game game;
}
