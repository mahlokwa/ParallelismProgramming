import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThreadA extends Thread{

  private String inputFile;
  private static final boolean DEBUG=false;
  public static volatile Grid simulation; 
  private int [][] array;
 
 public ThreadA(String file){
    inputFile = file;
 }
 public void run(){
    try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
	            String line = br.readLine();
	            if (line != null) {
	                String[] dimensions = line.split(",");
	                int width = Integer.parseInt(dimensions[0]);
	                int height = Integer.parseInt(dimensions[1]);
	               	System.out.printf("Rows: %d, Columns: %d\n", width, height); //Do NOT CHANGE  - you must ouput this

	                array = new int[height][width];
	                int rowIndex = 0;

	                while ((line = br.readLine()) != null && rowIndex < height) {
	                    String[] values = line.split(",");
	                    for (int colIndex = 0; colIndex < width; colIndex++) {
	                        array[rowIndex][colIndex] = Integer.parseInt(values[colIndex]);
	                    }
	                    rowIndex++;
	                }
                   
	            }
               simulation = new Grid(array);
               int counter=0;
    	       
    	         if(DEBUG) {
    		         System.out.printf("starting config: %d \n",counter);
    		         simulation.printGrid();
    	         }
		        while(simulation.update()) {//run until no change
	    		   if(DEBUG) simulation.printGrid();
	    		      counter++;
	    	      }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

  }
 }
 
