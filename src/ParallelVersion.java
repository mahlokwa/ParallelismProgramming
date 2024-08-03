  public class ParallelVersion{
  public static void main(String [] args){
  
      String inputFileName = args[0];  //input file name
		String outputFileName=args[1];
       
       ThreadA A = new ThreadA(inputFileName);
       ThreadB B = new ThreadB(outputFileName);
       try{
       A.start();
       A.join();
       B.start();
       B.join();
       }
       catch(InterruptedException e) {
            e.printStackTrace();
        }
       //write grid as an image - you must do this.
    	//Do NOT CHANGE below!
    	//simulation details - you must keep these lines at the end of the output in the parallel version
       if(ThreadA.simulation != null){
	System.out.printf("\t Rows: %d, Columns: %d\n", ThreadA.simulation.getRows(), ThreadA.simulation.getColumns());
		System.out.printf("Number of steps to stable state:");
		System.out.printf("Time:");
         }
       else{ System.out.println("Wrong!");}
  }
}