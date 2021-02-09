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
        int numOfTiles = 9;
        int rowLength = 3;
        int finalDistance = 0;
        for(int i = 1; i < numOfTiles; i++){
            if(goalState.posOf(i) != state.posOf(i)){
                int indexOfMissplacedTile = state.posOf(i);
                int indexOfGoalplacedTile = goalState.posOf(i);
                int indexDifference = Math.abs(indexOfGoalplacedTile - indexOfMissplacedTile);
                int finalDistanceOfThisTile = 0;
                if(indexDifference >= rowLength){
                    finalDistanceOfThisTile += indexDifference / 3;// number of rows moved
                    finalDistanceOfThisTile += indexDifference % 3;// number of columns moved
                }
                else if(indexDifference < rowLength && (indexDifference + 1)%rowLength == 0){//special case if the tile needs to travel ex(from index 3 to 4 or vice verca);
                 finalDistanceOfThisTile += 3;
                }
                else{
                    finalDistanceOfThisTile += indexDifference;
                }
                finalDistance += finalDistanceOfThisTile;
            }
        }
        return finalDistance;
    }
    
}