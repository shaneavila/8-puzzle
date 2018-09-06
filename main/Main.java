package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter a size for the sliding block puzzle (3 or 4): ");
        Scanner input = new Scanner(System.in);
        //TODO add check to make sure puzzleSize int value is 3 or 4
        int puzzleSize = 3;
//        try {
//            puzzleSize = Integer.parseInt(input.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Using default size of 3...");
//        }

        //TODO Generates Puzzles
        Puzzle puzzle = new Puzzle(puzzleSize);

    }
}
