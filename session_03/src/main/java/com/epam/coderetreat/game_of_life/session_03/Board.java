package com.epam.coderetreat.game_of_life.session_03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    final List<Cell> cells;
    final int sizeX;
    final int sizeY;

    public Board( int[][] cellmatrix){
        this.sizeX = cellmatrix[0].length;
        this.sizeY = cellmatrix.length;
        cells = new ArrayList<Cell>();
        for (int j=0;j<sizeY; j++){
            for (int i=0;i<sizeX; i++){
                int state = cellmatrix[j][i];
                Cell cell = new Cell(i, j, state);
                cells.add(cell);
            }
        }
        populateNeghbours();
    }

    private void populateNeghbours() {
        for (Cell cell: cells) {
            List<Cell> neighbours = findNeighbours(cell);
            cell.setNeighbours(neighbours);
        }
    }

    public int getCellState(int x, int y) {
        return getCell(x,y).getState();
    }

    public void step() {
        cells.stream().forEach(cell -> cell.calculateNextState());
        cells.stream().forEach(cell -> cell.commitNextState());
    }

    private List<Cell> findNeighbours(Cell cell) {
        int x = cell.x;
        int y = cell.y;
        List<Cell> neighbours = new ArrayList<Cell>();

        int xPlus1  = (x + 1 + sizeX) % sizeX;
        int xMinus1 = (x - 1 + sizeX) % sizeX;
        int yPlus1  = (y + 1 + sizeY) % sizeY;
        int yMinus1 = (y - 1 + sizeY) % sizeY;

        List<Cell> collect = cells.stream().filter(c -> !(c.x == x && c.y == y)).collect(Collectors.toList());
        collect.stream().filter(c -> c.x == xPlus1 || c.x == xMinus1 || c.x == x).collect(Collectors.toList());

        for (Cell neighbour : cells) {
            int i = neighbour.x;
            int j = neighbour.y;
            if (!(i == x && j == y)) {
                if (
                        ((i == xPlus1) || (i == xMinus1) || (i == x))
                     && ((j == yPlus1) || (j == yMinus1) || (j == y))
                ) {
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    public Cell getCell(int x, int y) {
        return  cells.stream().filter(cell -> (cell.x == x && cell.y == y)).findAny().orElse(null);
    }


    @Override
    public String toString() {
        return "Board{" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                '}';
    }

    public void print(){
        int[][] cellmatrix = new int[sizeY][sizeX];
        for (Cell cell: cells) {
            cellmatrix[cell.y][cell.x] = cell.getState();
        }

        for(int j=0; j<sizeY; j++){
            for (int i=0; i<sizeX; i++){
                System.out.print(cellmatrix[j][i]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
