package movements;

import states.Board;

public interface Movement {
    Board move(Board board);
}
