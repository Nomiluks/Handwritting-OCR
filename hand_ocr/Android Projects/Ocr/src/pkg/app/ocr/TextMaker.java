package pkg.app.ocr;

import java.util.Arrays;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

public class TextMaker {
	/******************************/
	char characters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
			,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'			
			,'0','1','2','3','4','5','6','7','8','9'};
	static String set_result = null;
	static String answer = "";
	double[] networkOutput = {};
	double[] input   = {};
	double[] target  = {0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,
			0,0};
	
	/******************************/
	public char calculateTextforLine(Bitmap bmp)
	{
		bmp   = bmp.copy(Config.ARGB_8888, true);
		input = convertImageToVectorArray(bmp);
		return testNeuralNetwork(input);
	}
	public String calculateText(Bitmap character[])
	{
		String out= "";
		for(int i = 1; i<character.length; i++)
		{
			input = convertImageToVectorArray(character[i]);
			out  += testNeuralNetwork(input);
		}
		return out;
	}
	public double[] convertImageToVectorArray(Bitmap bmp)
	{
	    int index = 0;
	    int R=0,G =0,B =0;
	    int segmentChracterWidth  = bmp.getWidth();
	    int segmentChracterHeight = bmp.getHeight();
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
	public char  testNeuralNetwork(double input[]) 
	{
			OCRguiActivity.nnet.setInput(input);
			OCRguiActivity.nnet.calculate();
			double[] networkOutput = OCRguiActivity.nnet.getOutput();
			set_result = ""+Arrays.toString(networkOutput);
			double get[] = convertStringToDouble(set_result);
			return bestMatch(get);
	}
	public double[] convertStringToDouble(String str)
	{
		int next=0;
		String newstr = "";
		int getLength = str.length();
		String result[] = new String [62];
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
				next++;
			}
		}
		String a = "",b="";
		double twentysix[] = new double[62];
		double g =0.0;
		for(int i =0; i<62 ;i++)
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
	    	b="";
	    	a="";
		}
		return twentysix;
	}
	char bestMatch(double twentysix[])
	{
		double chooose = 0.0;
	    double temp    = 0.0;
	    boolean go     = false;
	    int index      = 0;
		for(int i =0; i<62; i++)
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
		char a = characters[index];
		return a;
	}
}
