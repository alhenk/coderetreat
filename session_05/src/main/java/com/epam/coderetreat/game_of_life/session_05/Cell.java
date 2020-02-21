package com.epam.coderetreat.game_of_life.session_05;

import java.util.List;

public class Cell {
    final int x;
    final int y;
    int state;
    int nextState;
    List<Cell> neighbours;

    Cell(int x, int y, int state){
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setNeighbours(List<Cell> neighbours) {
        this.neighbours = neighbours;
    }

    public void commitNextState(){
        state = nextState;
    }

    public void calculateNextState() {
        int aliveNeighbours = countAliveNeighbours();
        if (aliveNeighbours < 2 && state == 1){
            nextState = 0;
        } else if ( (aliveNeighbours == 2 || aliveNeighbours == 3) && state == 1){
            nextState = 1;
        } else if (aliveNeighbours > 3){
            nextState = 0;
        } else if (aliveNeighbours == 3 && state == 0){
            nextState = 1;
        } else {
            nextState = state;
        }
    }

    private int countAliveNeighbours() {
        int counter = 0;
        for (Cell neighbour : neighbours) {
            counter += neighbour.state;
        }
        return counter;
    }
}
