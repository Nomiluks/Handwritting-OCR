package com.gift;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;

public class binrization {
	public Bitmap preproccessingMain (Bitmap img){
		img =  toGray ( img ) ;
		img = umer( img , ( img.getWidth () / 8) , ( img.getHeight() / 7)  );
		return img ;
	}

	//public static int[] gray_array ;
	public static Bitmap umer(Bitmap bmp,int bWight,int bHight)
	{
		Bitmap binarized=Bitmap.createBitmap(bmp.getWidth() , bmp.getHeight() , Config.ARGB_8888);
        //Bitmap binarized = new BufferedImage(bmp.getWidth(), bmp.getHeight(), bmp.getType());
        	binarized = bmp ;
        if(bHight!=0 && bWight != 0){
			int picw=bmp.getWidth();
			int pich=bmp.getHeight();
			//int index=1;
			int  yEnd = 0 ;
			//int x;
			int result [][] = new int [ ( (picw*pich)/ (bWight*bHight))+(pich/bHight) ] [4];
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
				//System.out.println("threshold"+ thrashold);
					binarized =  localBinarize ( binarized  , thrashold, x, xEnd, y, yEnd );
					//System.out.println("s "+index+" "+  x +" "+ xEnd + " " + " "+ y +" "+ yEnd );
					
				}
				xEnd = 0;
			}
		}
		//result[0][0]=index;
		return bmp;
	}
	public static int laiba(Bitmap bmp,int x1,int x2 ,int y1,int y2 )
	{
		int result[]=new int[10];
		//int picw=bmp.getWidth();int pich=bmp.getHeight();
		//int []pix=new int[picw*pich];
		//bmp.getPixels(pix, 0, picw, 0, 0, picw, pich);
		int R=0,G=0,B=0;
		int array[]=new int [(x2-x1)*(y2-y1)];
		int index1=0;
		int picw = bmp.getWidth();
		int pich = bmp.getHeight();
		int[] pix = new int[picw * pich];
		bmp.getPixels(pix, 0, picw, 0, 0, picw, pich);
		for(int y=y1 ; y < y2 ; y++){
			for( int x = x1 ; x < x2 ; x++ )
			{
				 int index = y * picw + x;   	
        	     R = (pix[index] >> 16) & 0xff;
				// R =  new Color(bmp.getRGB(x, y)).getRed();
				/* int pixel = bmp.getPixel(x,y);
			        R = Color.red(pixel);*/
				 
				 
	    	     array[index1]=R;
	    	    // System.out.print(" G "+R);
	    	     index1++;
	    	}
		}
		/*result[0] = min(array);
		result[1] = max(array);
		result[2] = average(array);
		result[3] = (int) standardDevition(array);*/
		//System.out.println(" "+ result[0]+" "+ result[1]+" "+ result[2]+" s "+ result[3]+" "+ array.length);
		int thrashold  = setThrshold ( array );
		return thrashold;
	}
	
	public static Bitmap localBinarize(Bitmap original  , int threshold ,int x1 , int x2 , int y1 ,int y2) {
	   	 
        int red;
        int newPixel;
 
        // threshold = otsuTreshold(original);
       // System.out.println(threshold);
 
        //BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
 
        for(int i=x1; i < x2; i++) {
            for(int j=y1; j < y2 ; j++) {
 
                // Get pixels
            	
            	int pixel = original.getPixel(i,j);
                red = Color.red(pixel);
                int alpha = Color.alpha(pixel);
            	
               // red = new Color(original.getRGB(i, j)).getRed();
                //int alpha = new Color(original.getRGB(i, j)).getAlpha();
                if(red > threshold) {
                    newPixel = 255;
                }
                else {
                    newPixel = 0;
                }
                newPixel = colorToRGB(alpha, newPixel, newPixel, newPixel);
   //            original.setRGB(i, j, newPixel);
                original.setPixel(i, j, newPixel);
 
            }
        }
 
        return original;
 
    }
	
	 static Bitmap toGray(Bitmap original) {
		 
	        int alpha, red, green, blue;
	        int newPixel;
	      //  gray_array = new int [original.getWidth()*original.getHeight()];
			 Bitmap lum=Bitmap.createBitmap(original.getWidth() , original.getHeight() , Config.ARGB_8888);

	        //BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
	        int inc=0;
	        for(int i=0; i<original.getWidth(); i++) {
	            for(int j=0; j<original.getHeight(); j++) {
	 
	                // Get pixels by R, G, B
	                /*alpha = new Color(original.getRGB(i, j)).getAlpha();
	                //System.out.println(alpha);
	                red = new Color(original.getRGB(i, j)).getRed();
	                green = new Color(original.getRGB(i, j)).getGreen();
	                blue = new Color(original.getRGB(i, j)).getBlue();*/
	            	
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
	 public static int colorToRGB(int alpha, int red, int green, int blue) {
		 
	        int newPixel = 0;
	        newPixel += alpha;
	        newPixel = newPixel << 8;
	        newPixel += red; newPixel = newPixel << 8;
	        newPixel += green; newPixel = newPixel << 8;
	        newPixel += blue;
	 
	        return newPixel;
	 
	    }
	 public static int setThrshold( int arr [] )
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
			
			int throshad = (avrage - ( 1+(int) ( 0.2 * ( (standerdDeviation  / R ) -1 )) ))%245-standerdDeviation;
			if(standerdDeviation<10)
				throshad-=standerdDeviation*standerdDeviation*standerdDeviation;
			if(throshad<1)
				throshad=10;
			//int a =(int) (  standerdDeviation * 0.2);
			//System.out.println(" "+ min+" "+ max+" "+ avrage+" s "+( (int)standerdDeviation)+" ");
			//int thrashould = avrage *(1 + ( 0.20  * ( (( (int) standerdDeviation) /R )  -1 ) ) ) ;
			throshad=(throshad+(( avrage +standerdDeviation )/2))/2;
			return ( throshad );
		}
	 public static int min(int arr[])
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
		
		public static int max(int arr[])
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
		
		public static int average(int arr[])
		{
			int avr=0;
			for( int x=0 ; x<arr.length ; x++ )
			{
				avr+=arr[x];
				
			}
			
			avr=avr/arr.length;
			return avr;
			
		}
		public static double standardDevition(int arr[])
		{
			int avrage = average(arr);
			int result=0;
			for(int i = 0 ; i < arr.length ; i++ ){
				result+=square(arr[i]-avrage);
			}
			return(Math.sqrt((double) (result/arr.length)));
		}
		public static  int square(int num)
		{
			return (num*num);
		}
}
