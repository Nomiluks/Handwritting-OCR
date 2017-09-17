package pkg.ocr.app;
import android.graphics.Bitmap;
import android.graphics.Color;

public class Transform {
	public Bitmap findAngle(Bitmap img)
	{
		double PI=3.14;
		double angle = 0 ;
		
		SegmentationProcess seg=new SegmentationProcess();
		int picw = img.getWidth();
        int pich = img.getHeight();
       
       // int pix [] = new int [10];
        int half = 0 , fisHalf = 0 , secHalf = 0 , index = 0 , R =0 , G = 0 , B = 0 , estimate=0;
        double slope;// =(roi[3]-roi[2])/(roi[1]-roi[0]);
        int[] pix = new int[picw * pich];
        int roi[]=seg.regionOfIntrest(img, 0, picw, 0, pich);
        int x1 = roi[0] , x2 = roi[1] , y1 = roi[2] , y2 = roi[3] ;
        
        half = ( roi[1] - roi[0] ) / 2;
        
        img.getPixels(pix, 0, picw, 0, 0, picw, pich); 
       
	    for (int y = roi[2]; y < roi[3]; y++)
        {
        	for (int x = roi[0]; x < roi[1]; x++)
            {
        		 index = y * picw + x;
        		
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R == 0 && G == 0 && B == 0) {
        			 if(x<half){
        				
        				 angle= lastEndAngle( img, pix, x, x2, y, y2, picw ,half);
        				 break;
        			 }
        			 else {
        				angle= fistEndAngle( img, pix, x1, x, y, y2, picw , half );
        				//angle+=270;
        				 break;
        			 }
        		 }
        		
            }
        	if(R == 0 && G == 0 && B == 0){
        		break;
        	}
        }   
	   // if(angle>2)
	    {
	    	img=rotate(img, angle);
	    }
        return img;
	}
	
	public double lastEndAngle(Bitmap img , int pix [] , int x1,int x2 ,int y1 ,int y2, int picw,int half)
	{
		int  fisHalf = 0 , secHalf = 0 , index = 0 , R =0 , G = 0 , B = 0 , estimate=0;
		 
		double slope;
		double PI=3.14;
		
		 estimate=( x2 - x1 ) / 15;
	        
	        for ( int y = y1; y < y2; y = y + 2 )
	        {	
	        	fisHalf=0; secHalf = 0;
		         slope =( (double)( y-y1) / (double )(x2-x1) );
		      	 for (int x = x1; x < x2; x++){
		        		
		        		y  =  (int)   ( ( ( slope  * ( x - x1 ) ) +  y1 ) + 0.5 );
		        		//img.setPixel(x, y, Color.RED); 	
		        		 index = y * picw + x;
		         		
		        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
		        		 G = (pix[index] >> 8) & 0xff;
		        		 B =  pix[index] & 0xff;
		        		 
		        		 if(R == 0 && G == 0 && B == 0)
		        		 {
		        			img.setPixel(x, y, Color.RED); 
		        			if(x<half) 
			        			fisHalf++;
		        			else
		        				secHalf++;
		        			
		        		 }
		        		
		           }
		      	  if ( secHalf > estimate)
		      	  {
		      		  y2=y;
		      		  break;
		      	  }
	        }
		double angle = Math.atan2(y2 - y1, x2 - x1) * 180 / PI;
		return angle;
	}

	
	public double fistEndAngle(Bitmap img , int pix [] , int x1,int x2 ,int y1 ,int y2, int picw , int half)
	{
		 int  fisHalf = 0 , secHalf = 0 , index = 0 , R =0 , G = 0 , B = 0 , estimate=0;
		//int fisHalf,secHalf , index;
		double slope;
		
		double PI=3.14;
		
		estimate=( x2 - x1 ) / 15;
		
		for ( int y = y1; y < y2; y = y +2 )
        {	
        	fisHalf=0; secHalf = 0;
	         slope =( (double)( y-y1) / (double )(x2-x1) );
	      	 for (int x = x2; x > x1; x--){
	        		
	        		y  =  (int)   ( ( ( slope  * ( x2 - x ) ) +  y1 ) + 0.5 );
	        	//	img.setPixel(x, y, Color.RED); 	
	        		 index = y * picw + x;
	         		
	        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
	        		 G = (pix[index] >> 8) & 0xff;
	        		 B =  pix[index] & 0xff;
	        		 
	        		 if(R == 0 && G == 0 && B == 0)
	        		 {
	        			img.setPixel(x, y, Color.RED); 
	        			if(x<half) 
		        			fisHalf++;
	        			else
	        				secHalf++;
	        			
	        		 }
	        		
	           }
	      	  if (fisHalf > estimate)
	      	  {
	      		  y2=y;
	      		  break;
	      	  }
        }
		double angle = Math.atan2(y2 - y1, x2 - x1) * 180 / PI;
		//return (angle=90-angle);
		return -angle;
	}
	
	/*************************************************************************
	 *  Compilation:  javac Rotation.java
	 *  Execution:    java Rotation filename angle
	 *
	 *  Rotate image a given number of degrees counterclockwise.
	 * 
	 *  % java Rotation baboon.jpg 30
	 *
	 *
	 *************************************************************************/

	public Bitmap rotate(Bitmap pic1,double angles){
       // Picture pic1 = new Picture(args[0]);
      //  pic1.show();
        int width  = pic1.getWidth();
        int height = pic1.getHeight();

        double angle = Math.toRadians(angles);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        double x0 = 0.5 * (width  - 1);     // point to rotate about
        double y0 = 0.5 * (height - 1);     // center of image

        //Picture pic2 = new Picture(width, height);
        Bitmap pic2 = Bitmap.createBitmap(pic1, 0, 0, pic1.getWidth(), pic1.getHeight());
       // pic2=(Bitmap.createScaledBitmap(pic2, pic2.getWidth(), pic2.getHeight(), false));
        // rotation
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double a = x - x0;
                double b = y - y0;
                int xx = (int) (+a * cos - b * sin + x0);
                int yy = (int) (+a * sin + b * cos + y0);

                // plot pixel (x, y) the same color as (xx, yy) if it's in bounds
                if (xx >= 0 && xx < width && yy >= 0 && yy < height) {
                    pic2.setPixel(x, y, pic1.getPixel(xx, yy));
                }
            }
        }

        return pic2;
    }
 

   
}
