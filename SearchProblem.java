import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Comparator;

/**
 * Represents a search problem to be solved.
 * 
 * This is somewhat specific to the 8-Puzzle, since a more general solution would require increased use of
 * generics, which we're avoiding in this course for now.
 * @author Steven Bogaerts
 */
public class SearchProblem {
    private int maxChecks;
    private PuzzleState goalState;
    private Heuristic h;
    private Queue<PuzzlePath> frontier;
    private HashSet<PuzzleState> expanded= new HashSet<PuzzleState>();
    // *************************** TO DO - define whatever fields you need here
    /**
     * This Comparator object is an instance of an anonymous class.
     * It compares two paths based on cost, for use in the PriorityQueue, for ordering.
     */
    public static Comparator<PuzzlePath> pathComparator = new Comparator<PuzzlePath>() {
        public int compare(PuzzlePath p1, PuzzlePath p2) {
            return p1.getCost() - p2.getCost();
        }
    };
    /**
     * Constructs a new SearchProblem.
     * 
     * @param queueType For our purposes for now, assume that queueType is either "FIFO" (meaning BFS will be done)
     * or "Ordered" (meaning A* will be done).
     * 
     * @param goalCheckLimit Check if some state is a goal up to this many times, after which you give up the search.
     * This is useful for problems that turn out to be impossible to solve.
     * 
     * @param h The Heuristic, should be an instance of the NoHeuristic class if you're using FIFO (BFS) as the queueType.
     * Otherwise pass an appropriate Heuristic object.
     */
    public SearchProblem(PuzzleState initState, PuzzleState goalState, String queueType, int goalCheckLimit, Heuristic h) {
        // *************************** TO DO - do whatever initialization you need here
        maxChecks = goalCheckLimit;
        this.goalState = goalState;
        if(queueType == "FIFO"){// Check the value of queueType, and set the frontier to the correct type.
            this.h = new NoHeuristic();
            frontier = new LinkedList<PuzzlePath>();
            frontier.add(new PuzzlePath(initState,h));
        }
        else{
            this.h = h; 
            frontier = new PriorityQueue<PuzzlePath>(100,pathComparator);
            frontier.add(new PuzzlePath(initState,h));
        }
    }
    /**
     * Solve this search problem.
     */
    public boolean solve() {
        int checksDone = 0;
        while(!frontier.isEmpty() && checksDone <= maxChecks){
            checksDone++;
            PuzzlePath n = frontier.remove();//get and remove first state in the queue
            PuzzleState currentState = n.stateAtEndOfPath();
            if(currentState.equals(goalState)){//ifsolution is found
                System.out.print(checksDone + " : checks done\n" + n.length() + ": is the length\n" +  n);
                return true;//figure out how to return solution
            }
            expanded.add(currentState);//add node to the expanded set to prevent duplicates
            LinkedList<PuzzleState> listOfPotentialNewStates= currentState.expand();
            for(PuzzleState state:listOfPotentialNewStates){
                if(!expanded.contains(state)){//might be bug cause of types check later                   
                   PuzzlePath copyOfn = n.makeCopy();
                   copyOfn.addState(state);
                   frontier.add(copyOfn);
                }
            }
        }
        
        return false;
    }
        
}