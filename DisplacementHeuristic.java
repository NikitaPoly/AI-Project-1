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
        
        int numOfTiles = 8;
        int wrongPos =0;
        for(int i = 0; i < numOfTiles ; i++){
            if(goalState.posOf(i) != state.posOf(i)){
                wrongPos++;
            }
            
        }
        
        return wrongPos;
    }
}