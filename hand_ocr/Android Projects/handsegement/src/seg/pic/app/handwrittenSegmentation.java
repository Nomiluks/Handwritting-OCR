package seg.pic.app;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;

public class handwrittenSegmentation {
	public void handwrittenSegmentationMain(Bitmap img,int split[],int dense,int x1, int x2 ,int y1 , int y2 ,int bY1 , int bY2)
	{
		int bHight=bY2-bY1;  //basline hight
		int dist=0;
		for(int z=1;z<split[0];z+=2)
		{
			dist=split[z+1]-split[z];
			if (dist< (dense*2) )
			{
				int roi[]=regionOfIntrest(img, split[z], split[z+1], y1, y2);
				if((roi[3]-roi[2])>=bHight)
				{
					
				}
				//chechForI();
				
			}
		}
	}
	public Bitmap makeBitmap(Bitmap bmp , int x1 , int x2 , int y1 , int y2 )
	{
		//x1-=1;x2+=1;
		int picw=bmp.getWidth();
		int pich=bmp.getHeight();  
		int[] pix = new int[picw * pich];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
		int index=0;
		int [] cop=new int[(y2-y1)*(x2-x1)];
	    int op=0;
	    for(int z=y1;z<y2;z++)
	    {
	    	for(int a=x1;a<x2;a++)
	       	{
		    		index=z*picw+a;
		    		cop[op] = pix[index];
		    		op++;
	        }
	    }
	  return ( Bitmap.createBitmap(cop, x2-x1, y2-y1, Config.ARGB_8888));
	}
	public int[] regionOfIntrest(Bitmap bmp,int xSize,int picw,int ySize,int pich)
	{
	    //int picw = bmp.getWidth();
	    ///int pich = bmp.getHeight();
	    int[] pix = new int[picw * pich];
	    int[] points = new int[4];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	  /*calculating x1 x2 value*/  
	    int R=0, G=0, B=0,index=0;
        int y1=0,y2=0,x1=0,x2=0;
        boolean check = true;
        
        for (int y = ySize; y < pich; y++)
        {
        	for (int x = xSize; x < picw; x++)
            {
        		 index = y * picw + x;
        		
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R == 0 && G == 0 && B == 0)
        		 {
           				 if(check==true)
           				 {
           					 x1=x;
           					 x2=x;
           					 check=false;
           				 }
           				 if(x1>x)
           				 {
           					 x1=x;
           				 }
           				 if(x2<x)
           				 {
           					x2=x; 
           				 }
        		 }
        		pix[index] = 0xff000000 | (R << 16) | (G << 8) | B;
        		
            }	
        }
        
        //***************************calculating y1 and y2 value************************
        check=true;
        for (int y = xSize; y < picw; y++)
        {
        	for (int x = ySize; x < pich; x++)
            {
        		 index = x * picw+y;
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R == 0 && G == 0 && B == 0)
        		 {
           				 if(check==true)
           				 {
           					 y1=x;
           					 y2=x;
           					 check=false;
           				 }
           				 if(y1>x)
           				 {
           					 y1=x;
           				 }
           				 if(y2<x)
           				 {
           					 y2=x;
           				 }
        		 }
        		pix[index] = 0xff000000 | (R << 16) | (G << 8) | B;
            }	
        }
      
        points[0]=x1;  points[1]=x2+1; points[2]=y1;points[3]=y2+1;
      
        return points;
	}
	public void chechForI(Bitmap img,int split[],int dense,int x1, int x2 ,int y1 , int y2 ,int bY1 , int bY2)
	{
		 int index=0;int count=0;
		 boolean butt=true,u=true;int inc=0;
		 int R=0,G=0,B=0;
		 int picw=img.getWidth(),pich=img.getHeight();
		 int counter=0;
		 int[] pix = new int[picw * pich];
		   
		    img.getPixels(pix, 0, picw, 0, 0, picw, pich);
		    for (int x= x1; x < x2; x++)
	        {
		    	count=0;inc=0;
		    	butt=true;u=true;
	        	for (int y = y1; y < y2; y++)
	            {
	        		 index = y * picw+x;
	        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
	        		 G = (pix[index] >> 8) & 0xff;
	        		 B =  pix[index] & 0xff;
	        		 
		        		 if(R == 0 && G == 0 && B == 0)
		        		 {
		        			 if(butt==true)
		        			 {
		        				 if(R == 0 && G == 0 && B == 0)
				        		 {
		        					 count++;
		        					 u=false;
				        		 }
		        			 }
			        		 else
			        			 if(u==false) 
			        			 {
			        				 if(R == 0 && G == 0 && B == 0) 
			        				 {
			        					 inc++;
					        		 }
			        			 }
			        		 }
		        		 else {
		        			 if(u==false)
		        			 {
		        				 //img.setPixel(4, 4, Color.RED);
		        				 butt=false;
		        				 if(inc>0)
		        				 {
		        					 break;
		        				 }
		        			 }
		        		 }
	        	}
	        	if(count>0&&inc>0)
	        	{
			    	
			    }
	        	
	        	
	        	else
	        	{
	        		//img.setPixel(y, ySize, Color.RED);
	        	}
	        }//outer for end
		  //  return store;
	}
	public void simpleSegment(Bitmap img,int x1, int x2 ,int y1 , int y2 ,int bY1 , int bY2)
	{
		
		int index=0;
			
		int R=0,G=0,B=0,counting=1;
		//int []segmentp=new int[500];
		int picw = img.getWidth();
		int pich = img.getHeight();
		int[] pix = new int[picw * pich];
		   
		 img.getPixels(pix, 0, picw, 0, 0, picw, pich);
		 for (int x = x1; x <x2; x++)
	     {
	     	for (int y = y1; y < bY1; y++)
	        {
	     		index = y * picw+x;
	       	 	R = (pix[index] >> 16) & 0xff; //bitwise shifting
	       	 	G = (pix[index] >> 8) & 0xff;
	       	 	B =  pix[index] & 0xff;
	        		 
		     	 if(R == 0 && G == 0 && B == 0)
		     	 {
		     		 
		     	 }
	        }
	     }
	}
	public int[]  resetlbl(int arr[])  // reset label in sequnce //
    {
		
		int count=0,value=0;
		for (int y = 0; y < arr.length; y++){
			if(arr[y]!=0&&count<arr[y])	{
				count++;
	    		value=arr[y];
	    		for (int x = y; x < arr.length; x++) {
	        		if(arr[x]==value){
	        			arr[x]=count;
	        		}
	        	}
	    	}	
	    }
		arr[0]=count+1;
		return arr;
    }
	public int[] conectedCompunent(Bitmap bmp,int x1, int x2 ,int y1 , int y2  )
	{
		int picw = bmp.getWidth(),pich = bmp.getHeight();
	 	int R=0, G=0, B=0,index=0,index2=0;
    	int xvalue = 0;
    	int yvalue = 0;
    	int[] pix = new int[picw * pich];
    	int[] structure = new int[picw * pich];
    	int[] n = new int[picw * pich];
    	//int[] zer = new int[picw * pich];
 
    	/*for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {	
        		index = y * picw + x; 
        		n[index] = 0;
        		//zer[index]=0;
            }
        }*/
    	
    	
    	
    	/** Loop on image */
    	bmp.getPixels(pix, 0, picw, 0, 0, picw, pich);
    	for (int y = y1; y < y2; y++)
        {
        	for (int x = x1 ; x < x2; x++)
            {
        		 index = y * picw + x;   	
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
    	//img.setImageBitmap(bmp);
    	/** Creating image from array*/
    	Bitmap bmp2 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
    //	Bitmap bmp3 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
    	//Bitmap bmp4 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
    	//img.setImageBitmap(bmp4);
    	picw = bmp2.getWidth();
    	pich = bmp2.getHeight();
    	
    	for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		bmp2.setPixel(x, y,Color.WHITE);
        		//bmp3.setPixel(x, y,Color.WHITE);
            }
        }
    	for (int y = y1; y < y2; y++)
        {
        	for (int x = x1; x < x2; x++)
            {
        		index = y * picw + x;
        		if(structure[index] == 1)
        		{	
        			bmp2.setPixel(x, y, Color.BLACK);     		}
            }
        }
    	//img2.setImageBitmap(bmp2);
    	//((((y-1) * picw)+x)+1);	//upper++
    	//((((y-1) * picw)+x)-1);	//upper--
    	int label=1000;
    	for (int y = y1; y < y2; y++)
        {
        	for (int x = x1; x < x2; x++)
            {
	        		index = y * picw + x;
	        		index2 = ((y-1) * picw) + x;
	        		if(structure[index] == 1)
	        		{
	        		  if( x==0 || y==0 )
	        		  {
	        			if(x==0&&y==0)
        				{
        					n[index] = label;
        					label++;
        				}
	        			else if (y==0)
	        			{
	        				if(structure[index]==structure[index-1])
	        				{
	        					n[index] = n[index-1];
	        				}
	        				else
	        				{
	        					n[index]=label;
	        					label++;
	        				}
	        				
	        				
	        			}
	        			else if(x==0)
	        			{
	        				
	        				 if(structure[index]==structure[index2])
	        				{
	        					n[index] = n[index2];
	        				}
	        				else
	        				{
	        					n[index]=label;
	        					label++;
	        				}
	        			 }
	        		  }
	        			else if (structure[index]==structure[index-1]&&structure[index]==structure[index2])
	        			 {
		        					n[index] = n[index-1];
		        					xvalue 	 = n[index-1];
		        					yvalue 	 = n[index2];
		        					if(n[index] != n[index2])
			        				{
		        						for (int c = 0; c < pich; c++)
		        		    	        {
		        		    	        	for (int d = 0; d < picw; d++)
		        		    	            {
		        		    	        		index = c * picw + d;
		        		    	        		if(n[index] == xvalue)
		        		    	        		{
		        		    	        			n[index] = yvalue;
		        		    	        		}
		        		    	            }
		        		    	        }
			        				}
	        			 }
	        			 else if(structure[index]==structure[index-1])
	        			 {
	        				n[index] = n[index-1];
	        			 }
	        			 else if(structure[index]==structure[index2])
	        			 {
	        				n[index] = n[index2];
	        			 }
	        			 else 
	        			 {
	        				n[index]=label;
	        				label++;
	        			 }	
	        		}
        		}
            }
    	return n;
	}
}
