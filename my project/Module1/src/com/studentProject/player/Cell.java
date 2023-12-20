package com.studentProject.player;

public class Cell {

    //The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, live or dead (or populated and unpopulated, respectively). Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

    //Any live cell with fewer than two live neighbours dies, as if by underpopulation.
//    Any live cell with two or three live neighbours lives on to the next generation.
//    Any live cell with more than three live neighbours dies, as if by overpopulation.
//    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
//    These rules, which compare the behaviour of the automaton to real life, can be condensed into the following:
//
//    Any live cell with two or three live neighbours survives.
//    Any dead cell with three live neighbours becomes a live cell.
//    All other live cells die in the next generation. Similarly, all other dead cells stay dead.

    private Boolean alive; //if the cell alive or dead
    private int[] location; // the current location of the cell,
                            // when a new game start, the cell is at (0,0)








}
