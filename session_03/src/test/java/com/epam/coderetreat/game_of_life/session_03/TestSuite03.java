package com.epam.coderetreat.game_of_life.session_03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSuite03 {
    Board board;

    @Test
    public void givenInitBoardReturnsBoardSize3x3(){
        int[][] cellmatrix = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        board = new Board(cellmatrix);
        assertEquals(board.cells.size(), cellmatrix[0].length * cellmatrix.length);
    }

    @Test
    public void givenSetBoardReturnsCellx1y1StateAlive(){
        int x = 1;
        int y = 1;
        int[][] cellmatrix = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        board = new Board(cellmatrix);

        int state = board.getCellState(x, y);
        assertEquals(state, 1);
    }

    @Test
    public void givenUnderpopulationTheCellNextStateIsDead(){
        int x = 3;
        int y = 3;
        int[][] cellmatrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        board = new Board(cellmatrix);

        board.print();
        board.step();
        board.print();

        int state = board.getCell(x, y).getState();
        assertEquals(state, 0);
    }

    @Test
    public void givenTwoNeighboursTheCellNextStateIsAlive(){
        int x = 3;
        int y = 3;
        int[][] cellmatrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        board = new Board(cellmatrix);

        board.print();
        board.step();
        board.print();

        int state = board.getCell(x, y).getState();
        assertEquals(state, 1);
    }

    @Test
    public void givenThreeNeighboursTheCellNextStateIsAlive(){
        int x = 3;
        int y = 3;
        int[][] cellmatrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        board = new Board(cellmatrix);

        board.print();
        board.step();
        board.print();

        int state = board.getCell(x, y).getState();
        assertEquals(state, 1);
    }

    @Test
    public void givenOvercrowdingTheCellNextStateIsDead(){
        int x = 3;
        int y = 3;
        int[][] cellmatrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        board = new Board(cellmatrix);

        board.print();
        board.step();
        board.print();

        int state = board.getCell(x, y).getState();
        assertEquals(state, 0);
    }

    @Test
    public void givenThreeAliveCellsReproduceNewAlive(){
        int x = 3;
        int y = 3;
        int[][] cellmatrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        board = new Board(cellmatrix);
        int state = board.getCell(x, y).getState();
        assertEquals(state, 0);

        board.print();
        board.step();
        board.print();

        state = board.getCell(x, y).getState();
        assertEquals(state, 1);
    }

    @Test
    public void givenTwoAliveNeighboursFindNeighboursRetursTwo(){
        int[][] cellmatrix = new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        board = new Board(cellmatrix);


        Cell cell = board.getCell(1,1);
        int aliveNeighbourCount = 0;
        for (Cell neighbour : cell.neighbours) {
            aliveNeighbourCount += neighbour.getState();
        }

        assertEquals(aliveNeighbourCount, 2);
    }

    @Test
    public void givenAllAliveNeighboursFindNeighboursRetursEight(){
        int[][] cellmatrix = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        board = new Board(cellmatrix);

        Cell cell = board.getCell(1,1);
        int aliveNeighbourCount = 0;
        for (Cell neighbour : cell.neighbours) {
            aliveNeighbourCount += neighbour.getState();
        }

        assertEquals(aliveNeighbourCount, 8);
    }
    @Test
    public void givenCellIndexesOneOneAliveBoardReturnsAliveCellState(){
        int[][] cellmatrix = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        board = new Board(cellmatrix);
        Cell cell = board.getCell(1,1);
        assertEquals(cell.getState(), 1);
    }

    @Test
    public void givenBoardX5Y3ReturnsBoardSizeX5Y3(){
        int[][] cellmatrix = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        board = new Board(cellmatrix);
        assertEquals(board.sizeX, 5);
        assertEquals(board.sizeY, 3);
    }
}
