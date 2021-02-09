import java.lang.Math; 
/**
 * This defines the Manhattan heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class ManhattanHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public ManhattanHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    /**
     * Returns a a distance each tile should be moved regardless of othere tiles
     */
    public int distance(PuzzleState state) {
        int numOfTiles = 8;
        int rowLength = 3;
        int finalDistance = 0;
        for(int i = 0; i < numOfTiles; i++){
            if(goalState.posOf(i) != state.posOf(i)){
                int indexOfMissplacedTile = state.posOf(i);
                int indexOfGoalplacedTile = goalState.posOf(i);
                int notFinalDistance = Math.abs(indexOfGoalplacedTile - indexOfMissplacedTile);
                int finalDistanceOfThisTile = 0;
                if(notFinalDistance >= rowLength){
                    finalDistanceOfThisTile = notFinalDistance / 3;// number of rows moved
                    finalDistanceOfThisTile += notFinalDistance % 3;// number of columns moved
                }
                finalDistance += finalDistanceOfThisTile;
            }
        }
        return finalDistance;
    }
    
}