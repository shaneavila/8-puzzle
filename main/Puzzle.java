package main;

import heuristics.HeuristicChoice;
import movements.MovementChoice;
import states.Board;
import states.Node;

import java.util.*;

public class Puzzle {

    private Board goalState;
    private HeuristicChoice heuristic;
    private Node root;
    private Queue<Node> frontier;
    private Map<Integer, Node> explored;
    private List<MovementChoice> movements;

    public Puzzle(int[] start, int[] goal, HeuristicChoice heuristic) {
        Board startState = new Board(start);
        goalState = new Board(goal);
        this.heuristic = heuristic;
        root = new Node(startState, goalState, heuristic, null);
        frontier = new PriorityQueue<>();
        explored = new HashMap<>();
        movements = new ArrayList<>(Arrays.asList(MovementChoice.values()));
    }

    public Node solve() {
        frontier.add(root);
        while (!frontier.isEmpty()) {
            root = frontier.poll();
            explored.put(root.hashCode(), root);
            if (root.equals(goalState)) {
                return root;
            }
            for (MovementChoice movement : movements) {
                Node next = new Node(movement.move(root.getBoard()), goalState, heuristic, root);
                if (!explored.containsKey(next.hashCode())) {
                    frontier.add(next);
                }
            }
        }
        return null;
    }

    public Node getRoot() {
        return root;
    }
}
