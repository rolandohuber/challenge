package com.rolandohuber.minesweeper.service;

import com.rolandohuber.minesweeper.entity.Cell;
import com.rolandohuber.minesweeper.entity.CellType;
import com.rolandohuber.minesweeper.entity.Game;
import com.rolandohuber.minesweeper.repository.GameRepository;
import com.rolandohuber.minesweeper.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CellRepository cellRepository;

    public Game create(Game game) {


        int[][] board = new int[game.getWidth()][game.getHeight()];

        game.setDate(LocalDate.now());
        game.setTimer(0L);

        gameRepository.save(game);

        Set<String> bombsPositions = generateBombsPositions(game.getWidth(), game.getHeight(), game.getBombs());

        List<Cell> cells = new LinkedList<Cell>();

        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {
                Cell cell = new Cell();
                cell.setPositionX(x);
                cell.setPositionY(y);
                cell.setGame(game);
                CellType type = new CellType();
                if (bombsPositions.contains(x + "_" + y)) {
                    type.setName("BOMB");
                    board[x][y] = -10;
                } else {
                    type.setName("EMPTY");
                    board[x][y] = 0;
                }
                cell.setType(type);
                cells.add(cell);
            }
        }
        game.setCells(cells);

        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {

                if (board[x][y] == -10)
                    continue;
                board[x][y] += countBombsAround(board, x, y, 1);
                board[x][y] += countBombsAround(board, x, y, 2);
                board[x][y] += countBombsAround(board, x, y, 3);
            }
        }

        for (Cell cell : cells) {
            cell.setValue(board[cell.getPositionX()][cell.getPositionY()]);
            cellRepository.save(cell);
        }

        return game;
    }

    public int countBombsAround(int[][] board, int x, int y, int cant) {
        int bombsCount = 0;

        int xI = x - cant >= 0 ? x - cant : 0;
        int yI = y - cant >= 0 ? y - cant : 0;

        for (int xAux = xI; xAux < xAux + cant; xAux++) {

            if (xAux >= board.length)
                break;

            for (int yAux = yI; yAux < yAux + cant; yAux++) {

                if (yAux >= board[xAux].length)
                    break;

                bombsCount += board[xAux][yAux] == -10 ? 1 : 0;
            }
        }
        return bombsCount;
    }

    private Set<String> generateBombsPositions(Integer width, Integer height, Integer bombs) {
        Set<String> list = new LinkedHashSet<String>(bombs);
        while (list.size() < bombs) {
            int x = new Random().ints(0, width).findFirst().getAsInt();
            int y = new Random().ints(0, height).findFirst().getAsInt();
            list.add(x + "_" + y);
        }
        return list;
    }

    public void update(Game game) {
        this.gameRepository.save(game);
    }

    public List<Game> list() {
        return this.gameRepository.findAll();
    }
}
