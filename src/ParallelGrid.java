import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class ParallelGrid extends RecursiveTask<Boolean>{
     private static final int THRESHOLD = 80;   // a cut off for the threads
    private final Grid grid;           // grid instance variable 
    private final int startRow, endRow;  // first and last row 
  
  ParallelGrid(Grid grid, int startRow, int endRow) { // constructor for the class
        this.grid = grid;
        this.startRow = startRow;
        this.endRow = endRow;
        
    } 
  
   protected Boolean compute(){ // the parallelism part 
    if (endRow - startRow <= THRESHOLD){ // executes if the difference between the rows is less than the threshold 
   boolean change=false;
		//do not update border
		for( int i = startRow; i<endRow; i++ ) { // method for grain distribution 
			for( int j = 1; j<grid.columns-1; j++ ) {
				grid.updateGrid[i][j] = (grid.grid[i][j] % 4) + 
						(grid.grid[i-1][j] / 4) +
						grid.grid[i+1][j] / 4 +
						grid.grid[i][j-1] / 4 + 
						grid.grid[i][j+1] / 4;
				if (grid.grid[i][j]!=grid.updateGrid[i][j]) {  
					change=true; // changes to true if the grids are not equal 
				}
		}}  
     return change;

 }
 else{
      int mid = (this.startRow + this.endRow) / 2; // splits the grid into 2 in terms of rows 
      //System.out.println(mid);
                         int midRow = (startRow + endRow) / 2;
                    ParallelGrid task1 = new ParallelGrid(grid, startRow, mid); // part one of the thread 
                    ParallelGrid task2 = new ParallelGrid(grid, mid, endRow); // part two of the thread 
                    task1.fork(); // sends the first task to the pool 
                    boolean ansA = task2.compute(); //
                    boolean ansB = task1.join(); // waits for the task 1 thread to finish 
                     
                   return ansA || ansB; // return the combination answer for both tasks 
                   
                }

               
                
    }
   
  }
 
  