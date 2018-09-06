package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle {
    private int size;
    private int[][] tiles2DArray;

    //Constructor for no given input
    public Puzzle (int size) {
        this.size = size;
        tiles2DArray = create();
        //System.out.println(Arrays.deepToString(tiles2DArray));
    }

     private int[][] create() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < size * size; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        while(!isSolvable(numbers)) {
            Collections.shuffle(numbers);
        }
        int[][] temp = new int[size][size];
        for (int rows = 0; rows < size; rows++)
            for (int cols = 0; cols < size; cols++)
                temp[rows][cols] = numbers.get(rows * size + cols);
        return temp;
    }

    public boolean isSolvable(List<Integer> shuffledList) {
        int inversion = 0;
        for(int i = 0; i < shuffledList.size() - 1; i++) {
            for(int j = i+1; j < shuffledList.size(); j++) {
                if(shuffledList.get(i) > shuffledList.get(j))
                    inversion++;
            }
        }
        System.out.println("Inversion: " + inversion);
        return (inversion % 2 == 0);
    }
}
