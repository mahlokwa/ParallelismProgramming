import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class SimulationThread extends RecursiveAction{
  int low;
  int high;
  int [][] array;
  int SEQUENTIAL_CUTOFF = 500;
  static volatile Grid simulationGrid;
  //static volatile int counter = 0;
  
  public SimulationThread(int [][] a,int l, int h){
   low = l;
   high = h;
   array = a;
   }
   protected void compute(){
    if((high - low) < SEQUENTIAL_CUTOFF){
        simulationGrid = new Grid(array);
          }
        else{
          SimulationThread left = new SimulationThread(array,low,(low+high)/2);
          SimulationThread right = new SimulationThread(array,(low+high)/2, high);
          left.fork();
          right.compute();
          left.join();
        }

        
    }
}