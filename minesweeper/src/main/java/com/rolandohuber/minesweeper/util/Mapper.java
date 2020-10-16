package com.rolandohuber.minesweeper.util;

import com.rolandohuber.minesweeper.dto.CellDto;
import com.rolandohuber.minesweeper.dto.GameDto;
import com.rolandohuber.minesweeper.dto.UserDto;
import com.rolandohuber.minesweeper.entity.Cell;
import com.rolandohuber.minesweeper.entity.Game;
import com.rolandohuber.minesweeper.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Mapper {

    public GameDto mapGameToGameDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setName(game.getName());
        gameDto.setDate(game.getDate());
        gameDto.setTimer(game.getTimer());
        gameDto.setFlags(game.getFlags());
        gameDto.setBombs(game.getBombs());
        gameDto.setWidth(game.getWidth());
        gameDto.setHeight(game.getHeight());
        gameDto.setFinished(game.getFinished());
        gameDto.setUser(mapUserToDto(game.getUser()));

        CellDto[][] cells = new CellDto[game.getWidth()][game.getHeight()];
        game.getCells().forEach(cell -> cells[cell.getPositionX()][cell.getPositionY()] = mapCellToDto(cell));
        gameDto.setCells(cells);

        return gameDto;
    }

    public Game mapDtoToGame(GameDto gameDto) {
        Game game = new Game();
        game.setId(gameDto.getId());
        game.setName(gameDto.getName());
        game.setDate(gameDto.getDate());
        game.setTimer(gameDto.getTimer());
        game.setFlags(gameDto.getFlags());
        game.setBombs(gameDto.getBombs());
        game.setWidth(gameDto.getWidth());
        game.setHeight(gameDto.getHeight());
        game.setFinished(gameDto.getFinished());
        game.setUser(mapDtoToUser(gameDto.getUser()));

        ArrayList<Cell> list = new ArrayList<>();
        for (int x = 0; x < gameDto.getCells().length; x++) {
            for (int y = 0; y < gameDto.getCells()[x].length; y++) {
                list.add(Cell.builder().id(gameDto.getCells()[x][y].getId()).value(gameDto.getCells()[x][y].getValue()).build());
            }
        }
        game.setCells(list);
        return game;
    }

    public CellDto mapCellToDto(Cell cell) {
        return CellDto.builder().id(cell.getId()).positionX(cell.getPositionX()).positionY(cell.getPositionY()).value(cell.getValue()).type(cell.getType()).flag(cell.getFlag()).discovered(cell.getDiscovered()).build();
    }

    public Cell mapDtoToCell(CellDto cell) {
        return Cell.builder().id(cell.getId()).positionX(cell.getPositionX()).positionY(cell.getPositionY()).value(cell.getValue()).type(cell.getType()).flag(cell.getFlag()).discovered(cell.getDiscovered()).build();
    }

    public UserDto mapUserToDto(User user) {
        return UserDto.builder().id(user.getId()).username(user.getUsername()).build();
    }

    public User mapDtoToUser(UserDto user) {
        return User.builder().id(user.getId()).username(user.getUsername()).build();
    }

}
