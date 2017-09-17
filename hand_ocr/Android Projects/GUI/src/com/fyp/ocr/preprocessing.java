package com.fyp.ocr;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;

public class preprocessing {
	static public int [] globeTrashold = new int [5] ;
	static int valutresh;
	public Bitmap preproccessingMain (Bitmap img){
		img =  toGray ( img ) ;
		globleEstimate(img);
		img = umer( img , ( img.getWidth () / 8) , ( img.getHeight() / 7)  );
		return img ;
	}
	
	public void globleEstimate(Bitmap bmp ){
		int gray_array [] = new int[bmp.getWidth() * bmp.getHeight()];
		 bmp.getPixels(gray_array, 0, bmp.getWidth(), 0, 0, bmp.getWidth(), bmp.getHeight());
	       
        globeTrashold[0] = min(gray_array);
        globeTrashold[1] = max(gray_array);
        globeTrashold[2] = average(gray_array);
        globeTrashold[3] = (int) standardDevition(gray_array);
    
	}
	public Bitmap binrizationdynamic (Bitmap img, int val){
		if (val == 0){
			val = 8 ;
		}
		img =  toGray ( img ) ;
		img = umer( img , ( img.getWidth () / val) , ( img.getHeight() / val-1)  );
		return img ;
	}
	public Bitmap getAdaptiveLocalBinarization(int blocksize, int constant, Bitmap bmp)
    {
        Mat imgToProcess2 = Utils.bitmapToMat(bmp);
	    Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1,1,Imgproc.BORDER_DEFAULT);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY);
	    Imgproc.adaptiveThreshold(imgToProcess2, imgToProcess2,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,blocksize,constant);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
	    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
	    Utils.matToBitmap(imgToProcess2, bmpOut2);
		return bmpOut2;
    }

	/*public static Bitmap cannyDetection(Bitmap bmp)
	{
		 Mat imgToProcess2 = Utils.bitmapToMat(bmp);
		    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY); 
		    Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1,1,Imgproc.BORDER_DEFAULT);
		   // Imgproc.Canny(imgToProcess2, imgToProcess2, 90, 100);
		    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
		    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
		    Utils.matToBitmap(imgToProcess2, bmpOut2);
		    return bmpOut2;
	}
	public static Bitmap laplaceDetection(Bitmap bmp)
	{
		Mat imgToProcess2 = Utils.bitmapToMat(bmp);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY); 
	    Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1,1,Imgproc.BORDER_DEFAULT);
	    //Imgproc.Laplacian(imgToProcess2, imgToProcess2, imgToProcess2.depth());
	    Imgproc.Laplacian(imgToProcess2, imgToProcess2, imgToProcess2.depth(),1);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
	    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
	    Utils.matToBitmap(imgToProcess2, bmpOut2);
	    return bmpOut2;
	}
	public static Bitmap sobelDetection(Bitmap bmp)
	{
		Mat imgToProcess2 = Utils.bitmapToMat(bmp);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY); 
	    Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1,1,Imgproc.BORDER_DEFAULT);
	    Imgproc.Sobel(imgToProcess2, imgToProcess2,CvType.CV_8U ,80, 90);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
	    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
	    Utils.matToBitmap(imgToProcess2, bmpOut2);
	    return bmpOut2;
	}*/
	/*public static Bitmap colorRefine(Bitmap bmp,int picw,int pich)
	{
		int[] pix = new int[picw * pich];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    int R=0, G=0, B=0,index=0;
	    for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		 index = y * picw + x;
        		
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R >15  || G >15 || B >15)
        		 {
        			 bmp.setPixel(x, y,Color.WHITE);
        		 }
            }
        }
	    return bmp;
		
	}*/
	 /*public static Bitmap getAdaptiveLocalBinarization(int blocksize, int constant, Bitmap bmp)
	 {
	        Mat imgToProcess2 = Utils.bitmapToMat(bmp);
		    Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1,1,Imgproc.BORDER_DEFAULT);
		    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY);
		    Imgproc.adaptiveThreshold(imgToProcess2, imgToProcess2,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,blocksize,constant);
		    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
		    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
		    Utils.matToBitmap(imgToProcess2, bmpOut2);
			return bmpOut2;
	 }*/
	 public Bitmap umer( Bitmap bmp,int bWight,int bHight)
	 {	
		 int picw = bmp.getWidth() , pich = bmp.getHeight() ;
		// int[] pix = new int[picw * pich];
		  //  bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    Bitmap binarized = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());

	        //Bitmap binarized = Bitmap.createBitmap( bmp , bmp.getHeight(), bmp.getType());
	        binarized = bmp ;
	        if(bHight!=0 && bWight != 0){
				//int picw=bmp.getWidth();
				//int pich=bmp.getHeight();
				//int index=1;
				int  yEnd = 0 ;
				//int x;
				//int result [][] = new int [ ( (picw*pich)/ (bWight*bHight))+(pich/bHight) ] [4];
				//System.out.println("w "+ picw + " h " + pich);
				int xEnd = 0 ;
		        //BufferedImage binarized = new BufferedImage(bmp.getWidth(), bmp.getHeight(), bmp.getType());
				//binarized = bmp;
				for(int y=0 ; y < pich  ; y += bHight )
				{
					yEnd += bHight;
					if(yEnd>pich){
						yEnd-=bHight-(pich%bHight);
					}
					
					for(int  x = 0 ; x < picw ;  x += bWight )
					{
						xEnd  += bWight  ;
						if(xEnd>picw){
							xEnd -= bWight -( picw % bWight );
						}
						int thrashold  = laiba ( bmp , x , xEnd  , y , yEnd );
						//System.out.println(thrashold);
						binarized = localBinarize ( binarized  , thrashold, x, xEnd, y, yEnd );
						//System.out.println("s "+index+" "+  x +" "+ xEnd + " " + " "+ y +" "+ yEnd );
						/*result[index][0] = temp[0];
						result[index][1] = temp[1];
						result[index][2] = temp[2];
						result[index][3] = temp[3];
						
					*/	//index++;
					}
					xEnd = 0;
				}
			}
			//result[0][0]=index;
			return binarized;
		}
	 public int laiba(Bitmap bmp,int x1,int x2 ,int y1,int y2 )
	 {
			int result[]=new int[10];
			//int picw=bmp.getWidth();int pich=bmp.getHeight();
			//int []pix=new int[picw*pich];
			//bmp.getPixels(pix, 0, picw, 0, 0, picw, pich);
			int R=0,G=0,B=0;
			int array[]=new int [(x2-x1)*(y2-y1)];
			int index1=0;
			for(int y=y1 ; y < y2 ; y++){
				for( int x = x1 ; x < x2 ; x++ )
				{
	                R  = Color.red(bmp.getPixel(x, y));
					 //R =  new Color(bmp.getRGB(x, y)).getRed();
		    	     array[index1]=R;
		    	    // System.out.print(" G "+R);
		    	     index1++;
		    	}
			}
			int thrashold  = setThrshold ( array );
			return thrashold;
		}
		public int setThrshold1( int arr [] )
		{
			int R = 128;
			int min , max , avrage , standerdDeviation;
			min = min(arr);
			max = max(arr);
			avrage = average ( arr );
			standerdDeviation = (int) standardDevition ( arr );
			//int s = ( 1+(int) ( 0.1 * ( (standerdDeviation  / R ) -1 )));
			//int  throshad =  ( max - standerdDeviation )/2 ;
			
			//System.out.println(" "+ result[0]+" "+ result[1]+" "+ result[2]+" s "+ result[3]+" "+ array.length);
			
			int throshad = ((avrage - ( 1+(int) ( 0.2 * ( (standerdDeviation  / R ) -1 )) ))%245-standerdDeviation)  ;
			if(standerdDeviation<10)
				throshad-=standerdDeviation*standerdDeviation*standerdDeviation;
			if(throshad<1)
				throshad=10;
			//int a =(int) (  standerdDeviation * 0.2);
			//System.out.println(" "+ min+" "+ max+" "+ avrage+" s "+( standerdDeviation)+" ");
			//int thrashould = avrage *(1 + ( 0.20  * ( (( (int) standerdDeviation) /R )  -1 ) ) ) ;
			throshad=(((throshad+(( avrage +standerdDeviation )/2)))/2)+valutresh;
			return ( throshad + 15 );
		}
		public int setThrshold( int arr [] )
		{
			int globeAvrage = globeTrashold[2] - globeTrashold [3] ;
			if(globeAvrage < 165){
				globeAvrage = 165;
			}
			int R = 128;
			int min , max , avrage , standerdDeviation;
			min = min(arr);
			max = max(arr);
			avrage = average ( arr );
			standerdDeviation = (int) standardDevition ( arr );
			//int s = ( 1+(int) ( 0.1 * ( (standerdDeviation  / R ) -1 )));
			//int  throshad =  ( max - standerdDeviation )/2 ;
			
			//System.out.println(" "+ result[0]+" "+ result[1]+" "+ result[2]+" s "+ result[3]+" "+ array.length);
			
			int throshad = (avrage - ( 1+(int) ( 0.2 * ( (standerdDeviation  / R ) -1 )) ))%245-standerdDeviation;
			if(standerdDeviation<10)
				throshad-=standerdDeviation*standerdDeviation*standerdDeviation;
			if(throshad<1)
				throshad=10;
			//int a =(int) (  standerdDeviation * 0.2);
			//System.out.println(" "+ min+" "+ max+" "+ avrage+" s "+( (int)standerdDeviation)+" ");
			//int thrashould = avrage *(1 + ( 0.20  * ( (( (int) standerdDeviation) /R )  -1 ) ) ) ;
			throshad=((throshad+(( avrage +standerdDeviation )/2)))/2;
			//int throshad1 = ( 2*min + 3*standerdDeviation) / 2;//+standerdDeviation ) ; ;
			//throshad = ( throshad + throshad1 )/2;
			if(avrage < 70){
				throshad = throshad - 20 ;
			}
			else
				if(avrage > globeAvrage && avrage < 210){
					throshad+=15;
				}
				else
					if(avrage > 210){
						throshad=throshad+20;
					}
					else
						throshad += 0 ;
			//throshad = ( 2*min + 3*standerdDeviation) / 2;//+standerdDeviation ) ; ;
			return ( throshad );
		}
		public int min(int arr[])
		{
			int min=arr[0];
			for(int x=1 ;x<arr.length;x++)
			{
				if(min>arr[x])
				{
					min=arr[x];
				}
				
			}
			return min;
			
		}
		public int max(int arr[])
		{
			int max=0;
			for(int x=0 ;x<arr.length;x++)
			{
				if(max<arr[x])
				{
					max=arr[x];
				}
				
			}
			return max;
			
		}
		
		public int average(int arr[])
		{
			int avr=0;
			for( int x=0 ; x<arr.length ; x++ ){
				avr+=arr[x];
			}
			
			avr=avr/arr.length;
			return avr;
		}
		public double standardDevition(int arr[])
		{
			int avrage = average(arr);
			int result=0;
			for(int i = 0 ; i < arr.length ; i++ ){
				result+=square(arr[i]-avrage);
			}
			return(Math.sqrt((result/arr.length)));
		}
		public  int square(int num)
		{
			return (num*num);
		}
		public  Bitmap localBinarize(Bitmap original  , int threshold ,int x1 , int x2 , int y1 ,int y2) {
		   	 
	        int red;
	        int newPixel;
	 
	        // threshold = otsuTreshold(original);
	       // System.out.println(threshold);
	 
	        //BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
	 
	        for(int i=x1; i < x2; i++) {
	            for(int j=y1; j < y2 ; j++) {
	 
	                // Get pixels
	                red  = Color.red(original.getPixel(i, j));
	                
	                //red = new Color(original.getRGB(i, j)).getRed();
	                int alpha = Color.alpha(original.getPixel(i, j));
	                if(red > threshold) {
	                    newPixel = 255;
	                   // original.setPixel(i, j, 255);
	                }
	                else {
	                    newPixel = 0;
	                   // original.setPixel(i, j, 0);
	                }
	                newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
	                //original.(i, j, newPixel);
	                original.setPixel(i, j, newPixel);
	 
	            }
	        }
	 
	        return original;
	 
	    }
		public  Bitmap toGray(Bitmap original) {
			 
		        int alpha, red, green, blue;
		        int newPixel;
		        
		        int [] gray_array = new int [original.getWidth()*original.getHeight()];
				 Bitmap lum=Bitmap.createBitmap(original.getWidth() , original.getHeight() , Config.ARGB_8888);

		        //BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
		        int inc=0;
		        for(int i=0; i<original.getWidth(); i++) {
		            for(int j=0; j<original.getHeight(); j++) {
		 
		              	 int pixel = original.getPixel(i,j);
		                 red = Color.red(pixel);
		                 blue = Color.blue(pixel);
		                 green = Color.green(pixel);
		                 alpha = Color.alpha(pixel);
		                red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
		               // gray_array[inc] = red;
		                inc++;
		                // Return back to original format
		                newPixel = colorToRGB(alpha, red, red, red);
		                // Write pixels into image
		                lum.setPixel(i, j, newPixel);
		 
		            }
		        }
		        //System.out.println("out"+ inc +" "+original.getWidth()*original.getHeight());
		        return lum;
		 
		  }
		 public  int colorToRGB(int alpha, int red, int green, int blue) {
			 
		        int newPixel = 0;
		        newPixel += alpha;
		        newPixel = newPixel << 8;
		        newPixel += red; newPixel = newPixel << 8;
		        newPixel += green; newPixel = newPixel << 8;
		        newPixel += blue;
		 
		        return newPixel;
		 
	   }
		/* private static Bitmap blur( Bitmap original ) {
		    	
		       // BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

		    	float[] blurKernel = {
		    		     1/9f, 1/9f, 1/9f,
		    		     1/9f, 1/9f, 1/9f,
		    		     1/9f, 1/9f, 1/9f
		    		 };
		    	//Bitmap b = 
		    	BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
		    	 binarized = blur.filter(original, new BufferedImage(original.getWidth(),original.getHeight(),original.getType()));
		    	
		        return binarized;
		 
		    }*/
		 public Bitmap toGrayscale(Bitmap bmpOriginal) {
		        int width, height;
		        height = bmpOriginal.getHeight();
		        width = bmpOriginal.getWidth();
		        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
		                Bitmap.Config.RGB_565);
		        Canvas c = new Canvas(bmpGrayscale);
		        Paint paint = new Paint();
		        ColorMatrix cm = new ColorMatrix();
		        cm.setSaturation(0);
		        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		        paint.setColorFilter(f);
		        c.drawBitmap(bmpOriginal, 0, 0, paint);
		        return bmpGrayscale;
		    }
		 public Bitmap rescale(Bitmap bmp)
			{
				Bitmap shah=Bitmap.createScaledBitmap(bmp, bmp.getWidth()+20, bmp.getHeight()+20, false);
		        int[] pix = new int[bmp.getWidth()*bmp.getHeight()];
		        //int[] pix1 = new int[shah.getWidth()*shah.getHeight()];
		        
		       // bmp.getPixels(pix1, 0, shah.getWidth(), 0, 0, shah.getWidth(),bmp.getHeight()); 
		        
		        bmp.getPixels(pix, 0, bmp.getWidth(), 0, 0, bmp.getWidth(),bmp.getHeight()); 
		        int R,G,B,index;
		        
		        for (int y = 0; y < shah.getHeight(); y++) {
		        	for (int x = 0; x < shah.getWidth(); x++){
		        		shah.setPixel(x, y, Color.WHITE);
		            }
		        }
			    for (int y = 0; y < bmp.getHeight(); y++){
		        	for (int x = 0; x < bmp.getWidth(); x++) {
		        		shah.setPixel(x+10, y+10,  bmp.getPixel(x, y));
		            }
		        }
			    return shah;
			}

		 
}
