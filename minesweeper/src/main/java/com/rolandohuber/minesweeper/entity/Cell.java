package com.rolandohuber.minesweeper.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@javax.persistence.Entity
@NoArgsConstructor
@Getter
@Setter
public class Cell extends Entity {
    public static final String GAME_ATTRIBUTE = "game";
    private Integer positionX;
    private Integer positionY;
    private Integer value;
    private Boolean discovered;
    private Boolean flag;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CellType type;
    @ManyToOne(targetEntity = Game.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Game game;

    @Builder
    public Cell(Long id, String name, Integer positionX, Integer positionY, Integer value, Boolean discovered, Boolean flag, CellType type, Game game) {
        super(id, name);
        this.positionX = positionX;
        this.positionY = positionY;
        this.value = value;
        this.discovered = discovered;
        this.flag = flag;
        this.type = type;
        this.game = game;
    }
}
