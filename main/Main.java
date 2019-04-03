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
        int tileSpaces = 9;
        int numOfPuzzles = 10;

        List<int[]> puzzleList = new ArrayList<>(generatePuzzleList(tileSpaces, numOfPuzzles));

        List<HeuristicChoice> heuristics = new ArrayList<>(Arrays.asList(HeuristicChoice.values()));

        for (HeuristicChoice heuristicChoice : heuristics)
            test(puzzleList, heuristicChoice, tileSpaces);
    }

    private static void test(List<int[]> puzzleList, HeuristicChoice heuristic, int puzzleSize) {
        System.out.println("Puzzle\t\tDepth\tTime(ms)\t");
        System.out.println("Puzzle\t\t\t\t\t\tDepth\tTime(ms)\t");
        for (int[] startState : puzzleList) {
            Puzzle puzzle = new Puzzle(startState, goal(puzzleSize), heuristic);
            long start = System.currentTimeMillis();
            Node solution = puzzle.solve();
            //Board solution = puzzle.solve();
            long end = System.currentTimeMillis();
            long total = end - start;
            printReport(puzzle,solution,total);
        }
    }

    private static String printReport(Puzzle puzzle, Node solution, long total) {
        Node root = puzzle.getRoot();
        //Board root = puzzle.getRoot();
        Board start = root.getBoard();
        String line = start+"\t"+solution.getCost()+"\t\t"+ total;
        System.out.println(line);
        return line;
    }

    private static List<int[]> generatePuzzleList(int tileSpaces, int numOfPuzzles) {
        //Creates numbers until (n^2)-1
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < tileSpaces; i++)
            numbers.add(i);

        List<int[]> puzzleList = new ArrayList<>();
        for (int i = 0; i < numOfPuzzles; i++) {
            Collections.shuffle(numbers);
            //Checks parity. If unsolvable, shuffle numbers to generate new puzzle
            while (!isSolvable(numbers))
                Collections.shuffle(numbers);
            int[] temp = new int[tileSpaces];
            for (int j = 0; j < tileSpaces; j++)
                temp[j] = numbers.get(j);
            temp = new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0};
            puzzleList.add(temp);
        }
        return puzzleList;
    }

    private static int[] goal(int size) {
        int[] goalState = new int[size];
        for (int i = 0; i < size; i++)
            goalState[i] = i;
//        goalState = new int[]{1,2,3,4,5,6,7,8,0};
        return goalState;
    }

    //TODO improve by using merge sort
    private static boolean isSolvable(List<Integer> shuffledList) {
        int inversion = 0;
        for (int i = 0; i < shuffledList.size() - 1; i++)
            for (int j = i + 1; j < shuffledList.size(); j++)
                if (shuffledList.get(i) != 0 && shuffledList.get(j) != 0 && shuffledList.get(i) > shuffledList.get(j))
                    inversion++;
        return (inversion % 2 == 0);
    }
}
