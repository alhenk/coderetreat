package com.epam.coderetreat.game_of_life.session_05;

import java.util.ArrayList;
import java.util.List;

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
        for (Cell cell: cells) {
            if (cell.x == x && cell.y == y){
                return cell.getState();
            }
        }
        return 0;
    }

    public void step() {
        for (Cell cell: cells) {
            cell.calculateNextState();
        }
        for (Cell cell: cells) {
            cell.commitNextState();
        }
    }

    private List<Cell> findNeighbours(Cell cell) {
        int x = cell.x;
        int y = cell.y;
        List<Cell> neighbours = new ArrayList<Cell>();

        int xPlus1  = (x + 1 + sizeX) % sizeX;
        int xMinus1 = (x - 1 + sizeX) % sizeX;
        int yPlus1  = (y + 1 + sizeY) % sizeY;
        int yMinus1 = (y - 1 + sizeY) % sizeY;

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
        for (Cell cell: cells) {
            if (cell.x ==x && cell.y == y)
            return cell;
        }
        return null;
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
