package com.studentProject.player;

import java.util.Random;
import java.util.StringJoiner;

public class game {

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


    static int[][] grid = new int[9][9];
    public static Random random = new Random();
    /*
    j = col
    i = row
     */

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = random.nextInt(2);
            }
        }
        printGrid();
        int changed = 1;
        // 1 == survive; 0 = died
        while (changed != 0) {
            // infinite loop, never terminate
            changed = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int cell1 = grid[i][j];
                    // case1: the position is 1
                    final int num_nei = checkNeigh(i, j);
                    if (cell1 == 1) {
                        if (num_nei == 0 || num_nei == 1) {
                            //Each cell with one or no neighbors dies, as if by solitude.
                            grid[i][j] = 0;
                            changed = 1;
                        }
                        //Each cell with four or more neighbors dies,
                        else if (num_nei >= 4) {
                            grid[i][j] = 0;
                            changed = 1;
                        }
                        //Each cell with two or three neighbors survives.
                        else {
                            //grid[i][j] = 1; since grid[i][j] = 1 is 1 already, so don't need to do it again
                            continue;
                        }

                    }
                    //case2: the position is 0
                    else {
                        if (num_nei == 3) {
                            grid[i][j] = 1;
                            changed = 1;
                        }


                    }

                }
            }

        printGrid();
        }
    }


    public static void printGrid() {
        StringJoiner joiner = new StringJoiner("");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                joiner.add(grid[i][j] + ", ");
            }
            joiner.add("\n");
        }
        System.out.println(joiner.toString());
        System.out.println("---------------------");
    }

    /*
     j = col = x
     i = row = y

    (j-1,i+1)(j,i+1)(j+1,i+1)
    (j-1,i)  (j,i)  (j+1,i)
    (j-1,i-1)(j,i-1)(j+1,i-1)
    ***
     */

    public static int checkNeigh(int i, int j) {
        int counter = 0;

        int [] i_lst = {i-1, i, i+1};
        int [] j_lst = {j-1, j, j+1};

        for (int i1 : i_lst) {
            for (int j1 : j_lst){
                // only count the cell's neighbors, don't count the cell itself
                // check if the neighbors in in range(0,8)
                if (0<=i1 && i1 <=8 && 0<=j1 && j1 <=8) {
                    if (!(i==i1 && j == j1)) {
                        if (grid[i1][j1]==1) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }




}
