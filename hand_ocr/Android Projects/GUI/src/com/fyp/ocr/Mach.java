package com.fyp.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Environment;
import android.util.Log;

public class Mach {
	public static char characters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};
	StringBuilder out = new StringBuilder();
	static int inc = 0;
	static int sizeOfOutPutLenght=62;
	static int sizeOfTestingDataLenght=288;
	
	public   String testNeuralNetwork(NeuralNetwork nnet , Bitmap [][][] finalExtractedImages) 
	{
		String output= "";
		double[] networkOutput={};
		int cCount = 0 , machingIndex;
		double[] input = new double[ sizeOfTestingDataLenght + sizeOfOutPutLenght ] ;
		out.append(" ");
		for(int l=0;l<Segment.counterCantainar[0][0][0];l++) {
        	for(int w=0;w<Segment.counterCantainar[0][l+1][0];w++){
        		cCount=Segment.counterCantainar[0][l+1][w+1];
        		for(int c=1;c<cCount;c++){
        			input=convertImageToVectorArray(finalExtractedImages[l][w][c]);
        			nnet.setInput(input);
        			nnet.calculate();
        			 networkOutput = nnet.getOutput();
        			
        			
        			output = Arrays.toString(networkOutput);
        			double get[] = convertStringToDouble(output);
            		machingIndex=bestMatch(get);
            		out.append(characters[machingIndex]);
    		}
    		 out.append(" ");
    	}
    	out.append(" \n ");
    }
    		
		saveResult(out.toString()  , "result");
			return out.toString();	
	}
	public  double[] convertImageToVectorArray(Bitmap bmp)
    {
		Segment call = new Segment();
	    int index = 0 , reScaleWidht = 16 , reScaleHight = 18;
	    int R=0,G =0,B =0;
	    int segmentChracterWidth = bmp.getWidth();
	    int segmentChracterHeight = bmp.getHeight();
	    int roi[] = call.regionOfIntrest(bmp,0, segmentChracterWidth,0, segmentChracterHeight);
	    bmp = call.getCropedRegion(bmp, roi[0], roi[1], roi[2], roi[3]);
	    bmp = rescaleer(bmp, reScaleWidht, reScaleHight);
	    segmentChracterWidth  = bmp.getWidth();
	    segmentChracterHeight = bmp.getHeight();
	    int[] pix = new int[segmentChracterWidth * segmentChracterHeight];
	    double[] structure = new double[segmentChracterWidth * segmentChracterHeight];
	    bmp.getPixels(pix, 0, segmentChracterWidth, 0, 0, segmentChracterWidth, segmentChracterHeight);
	    
	    
	    for (int y = 0; y <segmentChracterHeight; y++)
        {
        	for (int x = 0; x <  segmentChracterWidth; x++)
            {
        		 index = y *  segmentChracterWidth + x;   	
        	     R = (pix[index] >> 16) & 0xff;
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R==0 && G==0 && B==0)
        		 {
        			 structure[index] = 1;
        		 }
        		 else
        		 {
        			 structure[index] = 0;
        		 }
            }
        }
    	return structure;
    }

public   double[] convertStringToDouble(String str)
{
	int next=0;
	String newstr = "";
	int getLength = str.length();
	String result[] = new String [sizeOfOutPutLenght];
	for(int i=0;i<result.length;i++)
	{
		result[i] = "";
	}
	for(int i=1;i<(getLength-1);i++)
	{
		newstr += str.charAt(i);
	}
	getLength = newstr.length();
	for(int i=0;i<(getLength);i++)
	{
		if(newstr.charAt(i) != ',')
		{
			result[next] += newstr.charAt(i);
		}
		else
		{
			//android.util.Log.i("Next"+next, result[next]);
			next++;
		}
	}
	//android.util.Log.i("Next"+25, result[25]);
	String a = "",b="";
	double twentysix[] = new double[sizeOfOutPutLenght];
	double g =0.0;
	for(int i =0; i<twentysix.length ;i++)
	{
		a = result[i];
		if(a.length()>7)
		{
    		b += a.charAt(0);
    	    b += a.charAt(1);
    	    b += a.charAt(2);
    	    b += a.charAt(3);
    	    b += a.charAt(4);
    	    b += a.charAt(5);
    	    b += a.charAt(6);
    	    g = Double.parseDouble(b);
    	    if(g > 1 || g < 0)
    	    {
    	    	g = 0.0;
    	    }
    	    twentysix[i] = g;
		}
		else
		{
			b += a.charAt(0);
    	    b += a.charAt(1);
    	    b += a.charAt(2);
    	    b += a.charAt(3);
    	    g = Double.parseDouble(b);
    	    if(g > 1 || g < 0)
    	    {
    	    	g = 0.0;
    	    }
    	    twentysix[i] = g;
		}
    	//android.util.Log.i("double", (i+1)+": "+twentysix[i]);
    	b="";
    	a="";
    	//System.out.print(" "+twentysix[i]);
	}
	//System.out.println();
	return twentysix;
}
	public int bestMatch(double twentysix[])
    {
    	double chooose = 0.0;
	    double temp    = 0.0;
	    boolean go     = false;
	    int index      = 0;
    	for(int i =0; i<twentysix.length; i++)
    	{
    		if(twentysix[i]>0.0)
    		{
    			if(go == false)
    			{
    				chooose = 1-twentysix[i];
    				index = i;
    				go = true;
    			}
    			temp = 1-twentysix[i];
    			if(temp < chooose)
    			{
    				chooose = temp;
    				index = i;
    			}
    		}
    	
    	}
    	//System.out.println(characters[index] +"    "+twentysix[index]+"        "+index);
    	//char a = characters[index];
    	android.util.Log.i(" "+characters[index], "    "+twentysix[index]);
    	return index;
    }
	 public Bitmap rescaleer(Bitmap m2,int m1w,int m1h)
	    {
			int m2wi = m2.getWidth();
			int m2hi = m2.getHeight();
	        int[] pix = new int[m2wi * m2hi];
	        m2.getPixels(pix, 0, m2wi, 0, 0, m2wi, m2hi);
	        int[] pixa = new int[m1w * m1h];
	        pixa = resizePixels(pix, m2wi, m2hi, m1w,m1h);
	        m2=Bitmap.createBitmap(pixa, m1w, m1h, Config.ARGB_8888);
	    	return m2;
	    }
	    public int[] resizePixels(int[] pixels,int w1,int h1,int w2,int h2) {
	    	int[] temp = new int[w2*h2] ;
	        // EDIT: added +1 to account for an early rounding problem
	        int x_ratio = ((w1<<16)/w2) +1;
	        int y_ratio = ((h1<<16)/h2) +1;
	        int x2, y2 ;
	        for (int i=0;i<h2;i++) {
	            for (int j=0;j<w2;j++) {
	                x2 = ((j*x_ratio)>>16) ;
	                y2 = ((i*y_ratio)>>16) ;
	                temp[(i*w2)+j] = pixels[(y2*w1)+x2] ;
	            }                
	        }                
	        return temp ;

	    }
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
	    public void saveResult(String string ,String name)
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
			              nmea_file = new File(root,name+".txt");
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
