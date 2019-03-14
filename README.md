# Sliding Puzzle

Given a square grid where N = (n * n)-1 tiles, order the tiles from 1 to N.

Usage
---

Overview (Informed Search)
---
Informed search algorithms show that if given certain criteria for a desired solution, or a set of rules to follow, it will steer the algorithm to find the solution. The purpose of informed search is to show how the solution was found. Given an initial state, informed search will expand the state to try to see if it can find a solution (goal state) within the given criteria also known as the heuristic function. The heuristic function is an estimate for the cost to the goal from the current state. 

Implementation
---
- [A*](https://en.wikipedia.org/wiki/A*_search_algorithm "Best-First Search")

Heuristics
---
To ensure that A* finds an optimal solution, the heuristics need to be admissible. An admissible heuristic never overestimates the cost of the path. The heuristics used are misplaced tiles and the Manhattan distance.
- Misplaced Tiles: the number of tiles that are not in the goal position
- Manhattan Distance: the sum of the distances of the tiles from their goal position

State Space
---
The state space varies depending on the size of the puzzle that is chosen to run, but the state space for all n-puzzles is restricted by solvability. There are two disjoint sets for all n-puzzles. They are solvable and unsolvable states. Starting with a state that is solvable or unsolvable will limit the puzzle to that set of solutions. Before attempting to find a solution, it's important to test for solvability. Trying to find a solution from an initial unsolvable state will never yield a solution.

N-Puzzle State Space Example
---
An 8-puzzle instance has 9!/2 = 181,440 reachable states. Depending on the size of the puzzle, a formula can be used to evaluate how many states the state sapce will contain. A puzzle with a 4x4 grid (15-puzzle) will have 16 positions for tiles meaning there are 16! different states that are both reachable and unreachable. 16!/2 gives only the reachable or unreachable states which in the case of the 15-puzzle is 10,461,394,944,000 states.
