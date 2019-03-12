# Sliding Puzzle

Given a square grid where N = (n * n)-1 tiles, order the tiles from 1 to N.

Overview (Informed Search)
---

Implementation
---
- [A*](https://en.wikipedia.org/wiki/A*_search_algorithm)

Heuristics
---
To ensure that A* finds an optimal solution, the heuristics needs to be admissible. An admissible heuristic never overestimates the cost of the path. The heuristics used are misplaced tiles and the Manhattan distance.
- Misplaced Tiles: the number of tiles that are not in the goal position
- Manhattan Distance: the sum of the distances of the tiles from their goal position
