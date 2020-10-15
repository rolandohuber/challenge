package com.rolandohuber.minesweeper.dto;

import com.rolandohuber.minesweeper.entity.CellType;
import com.rolandohuber.minesweeper.entity.Game;

public class CellDto {
    private Long id;
    private String name;
    private Integer positionX;
    private Integer positionY;
    private Integer value;
    private CellType type;
    private Game game;
    private CellDto[] cells;
}
