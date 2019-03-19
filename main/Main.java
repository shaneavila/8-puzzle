package main;

import heuristics.HeuristicChoice;
import states.Board;
import states.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int puzzleSize = 3;
        int numOfPuzzles = 100;

        List<int[][]> puzzleList = new ArrayList<>(generatePuzzleList(puzzleSize, numOfPuzzles));

        HeuristicChoice heuristic;

        heuristic = HeuristicChoice.DISTANCE;
        test(puzzleList,heuristic,puzzleSize);

        heuristic = HeuristicChoice.MISPLACED;
        test(puzzleList,heuristic,puzzleSize);
    }

    private static void test(List<int[][]> puzzleList, HeuristicChoice heuristic, int puzzleSize) {
        System.out.println(heuristic);
        System.out.println("Puzzle\t\tDepth\tTime(ms)\t");

        for (int[][] puzzleElement : puzzleList) {
            Puzzle puzzle = new Puzzle(puzzleElement, goal(puzzleSize), heuristic);
            long start = System.currentTimeMillis();
            Node solution = puzzle.solve();
            long end = System.currentTimeMillis();
            long total = end - start;
            printReport(puzzle,solution,total);
        }

    }

    private static String printReport(Puzzle puzzle, Node solution, long total) {
        Node root = puzzle.getRoot();
        Board start = root.getBoard();
        String line = start+"\t"+solution.getCost()+"\t\t"+ total;
        System.out.println(line);
        return line;
    }

    private static List<int[][]> generatePuzzleList(int size, int numOfPuzzles) {
        List<Integer> numbers = new ArrayList<>();
        //Creates numbers until (size*size)-1
        for(int i = 0; i < size * size; i++)
            numbers.add(i);
        List<int[][]> puzzleList = new ArrayList<>();
        for (int i = 0; i < numOfPuzzles; i++) {
            Collections.shuffle(numbers);
            //Checks parity. If unsolvable, shuffle numbers to generate new puzzle
            while (!isSolvable(numbers))
                Collections.shuffle(numbers);
            int[][] temp = new int[size][size];
            for (int rows = 0; rows < size; rows++)
                for (int cols = 0; cols < size; cols++)
                    temp[rows][cols] = numbers.get(rows * size + cols);
            puzzleList.add(temp);
        }
        return puzzleList;
    }

    private static int[][] goal(int size) {
        int[][] goalState = new int[size][size];
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                goalState[i][j] = i * size + j;
        return goalState;
    }

    private static boolean isSolvable(List<Integer> shuffledList) {
        int inversion = 0;
        for(int i = 0; i < shuffledList.size() - 1; i++)
            for(int j = i+1; j < shuffledList.size(); j++)
                if(shuffledList.get(i) != 0 && shuffledList.get(j) != 0 && shuffledList.get(i) > shuffledList.get(j))
                    inversion++;
        return (inversion % 2 == 0);
    }
}
