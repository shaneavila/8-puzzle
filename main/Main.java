package main;

import heuristics.HeuristicChoice;
import states.Board;
import states.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int tileSpaces = 9;     //Must be a sqrt 4, 9, 16, etc.
        int numOfPuzzles = 10;

        List<int[]> puzzleList = new ArrayList<>(generatePuzzleList(tileSpaces, numOfPuzzles));

        List<HeuristicChoice> heuristics = new ArrayList<>(Arrays.asList(HeuristicChoice.values()));

        for (HeuristicChoice heuristicChoice : heuristics)
            test(puzzleList, heuristicChoice, tileSpaces);
    }

    private static void test(List<int[]> puzzleList, HeuristicChoice heuristic, int puzzleSize) {
        System.out.println(heuristic);
        System.out.println("Puzzle\t\t\t\t\t\tDepth\tTime(ms)\t");
        for (int[] startState : puzzleList) {
            Puzzle puzzle = new Puzzle(startState, goal(puzzleSize), heuristic);
            long start = System.currentTimeMillis();
            Node solution = puzzle.solve();
            long end = System.currentTimeMillis();
            long total = end - start;
            printReport(puzzle,solution,total);
        }
    }

    private static void printReport(Puzzle puzzle, Node solution, long total) {
        Node root = puzzle.getRoot();
        Board start = root.getBoard();
        String line = start+"\t"+solution.getCost()+"\t\t"+ total;
        System.out.println(line);
    }

    private static List<int[]> generatePuzzleList(int tileSpaces, int numOfPuzzles) {
        //Creates numbers until (n^2)-1
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < tileSpaces; i++)
            numbers.add(i);
        List<int[]> puzzleList = new ArrayList<>();
        for (int i = 0; i < numOfPuzzles; i++) {
            Collections.shuffle(numbers);
            //Checks inversions. If unsolvable, shuffle numbers to generate new puzzle
            while (!isSolvable(numbers))
                Collections.shuffle(numbers);
            int[] temp = new int[tileSpaces];
            for (int j = 0; j < tileSpaces; j++)
                temp[j] = numbers.get(j);
            puzzleList.add(temp);
        }
        return puzzleList;
    }

    private static int[] goal(int width) {
        int[] goalState = new int[width];
        if (width % 2 == 0) {
            for (int i = 0; i < width - 1; i++)
                goalState[i] = i + 1;
        }
        else {
            for (int i = 0; i < width; i++)
                goalState[i] = i;
        }
        return goalState;
    }

    private static boolean isSolvable(List<Integer> shuffledList) {
        int squareLength = (int) Math.sqrt(shuffledList.size());
        int inversion = 0;
        int currentRow = 1;
        int blankRow = 0;

        for (int i = 0; i < shuffledList.size(); i++) {
            if (i % squareLength == 0) {
                currentRow++;
            }
            if (shuffledList.get(i) == 0) {
                blankRow = currentRow;
                continue;
            }
            for (int j = i + 1; j < shuffledList.size(); j++)
                if (shuffledList.get(i) != 0 && shuffledList.get(j) != 0 && shuffledList.get(i) > shuffledList.get(j))
                    inversion++;
        }
        if (squareLength % 2 == 0) { //Even width
            if (blankRow % 2 == 0) {//blank on odd
                return inversion % 2 == 1;
            }
            else {//blank on even
                return inversion % 2 == 0;
            }
        }
        else //Odd width
            return inversion % 2 == 0;
    }
}
