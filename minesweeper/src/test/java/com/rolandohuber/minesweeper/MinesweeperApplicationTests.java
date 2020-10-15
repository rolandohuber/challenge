package com.rolandohuber.minesweeper;

import com.rolandohuber.minesweeper.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MinesweeperApplicationTests {

    @Autowired
    private GameService gameService;

    @Test
    void contextLoads() {

        int[][] testBoard = new int[4][4];
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                testBoard[x][y] = 0;
            }
        }

        testBoard[2][2]=-10;

        Assertions.assertEquals(gameService.countBombsAround(testBoard, 0, 0, 1),1);
        Assertions.assertEquals(gameService.countBombsAround(testBoard, 0, 0, 2),1);
        Assertions.assertEquals(gameService.countBombsAround(testBoard, 1, 2, 1),1);

        testBoard[0][1]=-10;

        Assertions.assertEquals(gameService.countBombsAround(testBoard, 0, 0, 1),2);
        Assertions.assertEquals(gameService.countBombsAround(testBoard, 0, 0, 2),2);
        Assertions.assertEquals(gameService.countBombsAround(testBoard, 1, 2, 1),2);

        testBoard[2][2]=0;
        testBoard[0][1]=0;

        Assertions.assertEquals(gameService.countBombsAround(testBoard, 0, 0, 1),0);
        Assertions.assertEquals(gameService.countBombsAround(testBoard, 0, 0, 2),0);
        Assertions.assertEquals(gameService.countBombsAround(testBoard, 1, 2, 1),0);

        testBoard[0][0]=-10;

        Assertions.assertEquals(gameService.countBombsAround(testBoard, 0, 1, 1),1);
        Assertions.assertEquals(gameService.countBombsAround(testBoard, 3, 3, 1),0);
        Assertions.assertEquals(gameService.countBombsAround(testBoard, 3, 3, 4),1);
    }

}
