import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class ParallelGrid extends RecursiveTask<Boolean>{
     private static final int THRESHOLD = 100; // Adjust based on experimentation
    private final Grid grid;
    private final int startRow, endRow;
  
  ParallelGrid(Grid grid, int startRow, int endRow) {
        this.grid = grid;
        this.startRow = startRow;
        this.endRow = endRow;
    } 
  
   protected Boolean compute(){
    if (endRow - startRow <= THRESHOLD){
   boolean change=false;
		//do not update border
		for( int i = startRow; i<endRow; i++ ) {
			for( int j = 1; j<grid.columns-1; j++ ) {
				grid.updateGrid[i][j] = (grid.grid[i][j] % 4) + 
						(grid.grid[i-1][j] / 4) +
						grid.grid[i+1][j] / 4 +
						grid.grid[i][j-1] / 4 + 
						grid.grid[i][j+1] / 4;
				if (grid.grid[i][j]!=grid.updateGrid[i][j]) {  
					change=true;
				}
		}} //end nested for
	//if (change) {grid.nextTimeStep();}
	return change;

 }
 else{
      int mid = (this.startRow + this.endRow) / 2;
      //System.out.println(mid);
                         int midRow = (startRow + endRow) / 2;
                    ParallelGrid task1 = new ParallelGrid(grid, startRow, mid);
                    ParallelGrid task2 = new ParallelGrid(grid, mid, endRow);
                    task1.fork();
                    boolean ansA = task2.compute();
                    boolean ansB = task1.join();
                    //invokeAll(task1, task2);
               
                   return ansA || ansB;
                   
                }

               
                
    }
   
  }
 
  