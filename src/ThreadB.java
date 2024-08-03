import java.io.FileReader;
import java.io.IOException;
public class ThreadB extends Thread{
    private String outputFile;
    public ThreadB(String Ot){
      outputFile = Ot;
     }
     
     public void run(){
     try{
      System.out.println("Simulation complete, writing image...");
       if(ThreadA.simulation !=null){
    	ThreadA.simulation.gridToImage(outputFile);
      }
      else{
          System.out.println("Wrong!");
          }
      }
      catch (IOException e) {
	            e.printStackTrace();
	        }

      
     }
 }
