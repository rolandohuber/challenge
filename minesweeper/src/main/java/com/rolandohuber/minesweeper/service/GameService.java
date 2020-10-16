package com.rolandohuber.minesweeper.service;

import com.rolandohuber.minesweeper.entity.Cell;
import com.rolandohuber.minesweeper.entity.CellType;
import com.rolandohuber.minesweeper.entity.Game;
import com.rolandohuber.minesweeper.repository.CellRepository;
import com.rolandohuber.minesweeper.repository.GameRepository;
import com.rolandohuber.minesweeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GameService {

    @Value("${minesweeper.radar}")
    private Integer radar;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CellRepository cellRepository;
    @Autowired
    private UserRepository userRepository;

    public Game create(Game game) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        game.setUser(userRepository.findByUsername(user.getUsername()));

        int[][] board = new int[game.getWidth()][game.getHeight()];

        game.setDate(LocalDate.now());
        game.setTimer(0L);

        gameRepository.save(game);

        Set<String> bombsPositions = generateBombsPositions(game.getWidth(), game.getHeight(), game.getBombs());

        List<Cell> cells = new LinkedList<>();

        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {
                Cell cell = new Cell();
                cell.setPositionX(x);
                cell.setPositionY(y);
                cell.setGame(game);
                if (bombsPositions.contains(x + "_" + y)) {
                    cell.setType(CellType.BOMB);
                    board[x][y] = -10;
                } else {
                    cell.setType(CellType.EMPTY);
                    board[x][y] = 0;
                }

                cells.add(cell);
            }
        }
        game.setCells(cells);

        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {

                if (board[x][y] == -10)
                    continue;
                board[x][y] += countBombsAround(board, x, y, radar);
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
        Set<String> list = new LinkedHashSet<>(bombs);
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

    public Game read(Game game) throws Exception {
        return this.gameRepository.findById(game.getId()).orElseThrow(() -> new Exception());
    }

    public List<Game> list() {
        return this.gameRepository.findAll();
    }
}
