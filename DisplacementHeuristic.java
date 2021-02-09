/**
 * This defines the displacement heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class DisplacementHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public DisplacementHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    /** 
         * Returns the number of tiles in the wrong position. 
         */
    public int distance(PuzzleState state) {
        int numOfTiles = 9;
        int wrongPos = 0;
        for(int i = 1; i < numOfTiles; i++){//loop thru each number 1-8 and check if its in the right position
            if(goalState.posOf(i) != state.posOf(i)){//if not is same position then add to displacment
                wrongPos++;
           }    
        }
        return wrongPos;
    }
}