package pkg.app.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Environment;
import android.util.Log;

public class OutputFileMaker {
	public String getOutput()
	 {
		 String output = null;
		 try{
			   File f = new File(Environment.getExternalStorageDirectory()+"/XOR.txt");
			   FileInputStream fileIS = new FileInputStream(f);
			   fileIS = new FileInputStream(f);
			   BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));
			   String readString = new String(); 
			   //just reading each line and pass it on the debugger
			   while((readString = buf.readLine())!= null)
			   {
			      Log.d("line: ", readString);
			      output = " "+readString;
			   }
			} catch (FileNotFoundException e) {
			   e.printStackTrace();
			} catch (IOException e){
			   e.printStackTrace();
			}
		 return output;
	 }
	 public void saveInputArray(double array[])
	 {
		 if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) 
		 {
			 Log.d("MOUNTED", "Sdcard was not mounted !!" ); 
		 }
		 else
		 {
		          File nmea_file; 
		          File root = Environment.getExternalStorageDirectory();
		          FileWriter nmea_writer = null;
		          try {
		              nmea_file = new File(root,"input.txt");
		        	  //nmea_file = new File(root,"XOR.txt");
		              if(!nmea_file.exists()) {
		                      Log.w("File not Found", "File Doesn't Exists!");
		                      nmea_file.createNewFile();
		                  }
		              nmea_writer = new FileWriter(nmea_file);
		              for(int i=0; i<array.length;i++)
		     		  {
		            	  nmea_writer.append(array[i]+",");
		     		  }
		              nmea_writer.append("\t");
		              for(int i=0; i<26;i++)
		     		  {
		            	  nmea_writer.append("0,");
		     		  }
		              nmea_writer.append("\n");
		              nmea_writer.flush();
		          }
		          catch (IOException e) 
		          {
		              Log.w("Fiel write failed", "Unable to write", e);
		          } 
		          finally 
		          {
		              if (nmea_writer != null) 
		              {
		                  try 
		                  {
		                      nmea_writer.close();
		                  } 
		                  catch (IOException e) 
		                  {
		                      Log.w("Exception closing file", "Exception closing file", e);
		                  }
		              }
		          }
		 	}

		
	 }
	 public void saveResult(String string,int counter,String name)
	 {
		 if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) 
		 {
			 Log.d("MOUNTED", "Sdcard was not mounted !!" ); 
		 }
		 else
		 {
		          File nmea_file; 
		          File root = Environment.getExternalStorageDirectory();
		          FileWriter nmea_writer = null;
		          try {
		              nmea_file = new File(root,name+counter+".txt");
		        	  //nmea_file = new File(root,"XOR.txt");
		              if(!nmea_file.exists()) {
		                      Log.w("File not Found", "File Doesn't Exists!");
		                      nmea_file.createNewFile();
		                  }
		              nmea_writer = new FileWriter(nmea_file);
		              nmea_writer.append(""+string);
		              nmea_writer.flush();
		          }
		          catch (IOException e) 
		          {
		              Log.w("Fiel write failed", "Unable to write", e);
		          } 
		          finally 
		          {
		              if (nmea_writer != null) 
		              {
		                  try 
		                  {
		                      nmea_writer.close();
		                  } 
		                  catch (IOException e) 
		                  {
		                      Log.w("Exception closing file", "Exception closing file", e);
		                  }
		              }
		          }
		 	}
	 }
	 
}
