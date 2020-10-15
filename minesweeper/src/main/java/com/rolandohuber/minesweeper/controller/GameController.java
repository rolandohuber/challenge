package com.rolandohuber.minesweeper.controller;

import com.rolandohuber.minesweeper.dto.GameDto;
import com.rolandohuber.minesweeper.entity.Game;
import com.rolandohuber.minesweeper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Game game) {

        this.gameService.create(game);

        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setName(game.getName());
        gameDto.setBombs(game.getBombs());
        gameDto.setDate(game.getDate());
        gameDto.setFinished(game.getFinished());
        gameDto.setTimer(game.getTimer());
        gameDto.setHeigth(game.getHeight());
        gameDto.setWidth(game.getWidth());
        gameDto.setFlags(game.getFlags());
        gameDto.setFinished(game.getFinished());
        gameDto.setUser(game.getUser());

        return ResponseEntity.ok(gameDto);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Game game) {
        this.gameService.update(game);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(this.gameService.list().stream().map(game -> {
            GameDto gameDto = new GameDto();
            gameDto.setId(game.getId());
            gameDto.setBombs(game.getBombs());
            gameDto.setDate(game.getDate());
            gameDto.setFinished(game.getFinished());
            gameDto.setTimer(game.getTimer());
            gameDto.setHeigth(game.getHeight());
            gameDto.setWidth(game.getWidth());
            return gameDto;
        }));
    }
}
