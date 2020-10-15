package com.rolandohuber.minesweeper.dto;

import com.rolandohuber.minesweeper.entity.Cell;
import com.rolandohuber.minesweeper.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDto {
    private Long id;
    private String name;
    private LocalDate date;
    private Long timer;
    private Integer flags;
    private Integer bombs;
    private Integer width;
    private Integer heigth;
    private Boolean finished;
    private CellDto[] cells;
    private User user;
}
