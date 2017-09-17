package com.gift;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;

public class Segment {
	//public int con;
	public static int counterCantainar[][][]=new int [1][20][15];
	public static int lEnd[]=new int [20];
	 ConectedCompunent con=new ConectedCompunent();
	 Transform rotate = new Transform();
	 
	//BismillahActivity bis=new BismillahActivity();
	
	public  Bitmap[][][] segmentationMain(Bitmap bmp)
	{
		con.conectedCompunentMain(bmp);
		// bmp=rotate.findAngle(bmp);
		Bitmap array[][] = new Bitmap[50][20];int inc=0;
		int roi[]=regionOfIntrest(bmp,0, bmp.getWidth(), 0, bmp.getHeight());
		int lineSplit[][]=findLines(bmp, roi[0], roi[1], roi[2], roi[3]);
		int wordSplit[][]=findWord(bmp, lineSplit, lineSplit[0][0]);
		
		inc=lineSplit[0][0]-1;
		counterCantainar[0][0][0]=inc;
		Bitmap [][][] result=new Bitmap[20][500][20];
		int inc1=0;
		for(int i=0;i<inc;i++)
		{
			inc1=0;
			for(int z=1;z<wordSplit[i][0];z=z+2)
			{
				array[i][z/2]=makeBitmap(bmp, wordSplit[i][z], wordSplit[i][z+1], lineSplit[i+1][2], lineSplit[i+1][3]);
				Bitmap temp =  array [i][z/2] . copy( Config.ARGB_8888 , true);
				result[i][inc1]=con.conectedCompunentMain1(temp);
				//int sha=result[0][0].length;
				counterCantainar[0][i+1][inc1+1]=result[i][inc1].length;
				inc1++;
				//contres[inc][z/2]=10;
			}
			//lEnd[i]=inc1-1;
			counterCantainar[0][i+1][0]=inc1;
			
		}
	//	Bitmap s[]=con.conectedCompunentMain1(bis.getAdaptiveLocalBinarization(9, 1, array[0][0]));
	//	
		//Bitmap ba=makeCharcter(bmp, wordSplit[0][1], wordSplit[0][2], lineSplit[1][2], lineSplit[1][3]);
		   //result[0][0]=con.conectedCompunentMain1(bis.getAdaptiveLocalBinarization(15, 1, array[0][0]));
		return result;
		
	}
	public Bitmap rescale(Bitmap bmp)
	{
		Bitmap shah=Bitmap.createScaledBitmap(bmp, bmp.getWidth()+20, bmp.getHeight()+20, false);
        int[] pix = new int[bmp.getWidth()*bmp.getHeight()];
        //int[] pix1 = new int[shah.getWidth()*shah.getHeight()];
        
       // bmp.getPixels(pix1, 0, shah.getWidth(), 0, 0, shah.getWidth(),bmp.getHeight()); 
        
        bmp.getPixels(pix, 0, bmp.getWidth(), 0, 0, bmp.getWidth(),bmp.getHeight()); 
        int R,G,B,index;
        
        for (int y = 0; y < shah.getHeight(); y++)
        {
        	for (int x = 0; x < shah.getWidth(); x++)
            {
        		shah.setPixel(x, y, Color.WHITE);
            }
        }
	    for (int y = 0; y < bmp.getHeight(); y++)
        {
        	for (int x = 0; x < bmp.getWidth(); x++)
            {
        	
        		shah.setPixel(x+10, y+10,  bmp.getPixel(x, y));
        		
            }
        }
	    return shah;
	}
	public int[] regionOfIntrest(Bitmap bmp,int xSize,int picw,int ySize,int pich)
	{
	    //int picw = bmp.getWidth();
	    ///int pich = bmp.getHeight();
	    int[] pix = new int[picw * pich];
	    int[] points = new int[4];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	  /*calculating x1 x2 value*/  
	    int R=0, G=0, B=0,index=0,count=0;
        int y1=0,y2=0,x1=0,x2=0,inc=0;
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
	public int[][] findLines(Bitmap bmp,int x1,int x2,int y1,int y2)
	{
		int picw=bmp.getWidth();
		int pich=bmp.getHeight();
		//System.out.println(x1+" "+x2+" "+y1+"  "+y2+" "+picw +" "+pich);
		  int[] pix = new int[picw * pich];
		    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
		    int R=0, G=0, B=0,index=0,count=0, inc=1,z=0;
			int previous=y1-1;
			int [][] lineSplit = new int [70][4];
			int array[] =new int [150];
			
			for(int s=y1;s<y2+1;s++)
			{
				count=0;
	        	for( z=x1;z<x2;z++)	{
	        		index=s*picw+z;
				    R = (pix[index] >> 16) & 0xff; //bitwise shifting
				    G = (pix[index] >> 8) & 0xff;
				    B =  pix[index] & 0xff;    		 
				   	if(R == 0 && G == 0 && B == 0)
		     		{
				   		if((previous+1)==s) {			
				   			//bmp.setPixel(10, s,  Color.RED);
							array[inc]=previous;
							inc++;
							break;
						}
						else
							break;
		     		}
				    else{
				    	//bmpOut2.setPixel(z, l[2], Color.RED);
				        count++;	
					}
				        		
	        	}
		       	if(count==(x2-x1)){	
			  		if((previous+1) != s ){
			  			//bmp.setPixel(10, s, Color.RED);
			  			array[inc]=s;
		    			inc++;
		    		}
				    previous=s;
			  	}     		        	
		    }		
			int inc1=1;
			for(int s=1;s<inc;s=s+2)
			{
				lineSplit[inc1]=regionOfIntrest(bmp, x1, x2, array[s], array[s+1]);
				inc1++;
			}
			lineSplit[0][0]=inc1;
			//conectedCompunent.contres[0][0]=inc1;
			//counterCantainar[0][0][0]=inc-1;
			return lineSplit;
		
	}
	public int[][] findCharecter(Bitmap bmp,int picw,int pich,int x1,int x2,int []lineSplit,int br)
	{	    
		int[] pix = new int[picw * pich];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    int R=0, G=0, B=0,index=0,count=0, inc=0,z=0;
		int previous,y1,y2;
		int a;
	   // int [] ysplit = new int [200];
	    int chracterSplit[][] = new int [200][400];
	    a=0;
	    for(int j=0;j<br;j=j+2)
	    {
		    y1=lineSplit[j];
		    y2=lineSplit[j+1];
		    previous=x1-1;
		    //chracterSplit[a][0]=inc;
		    inc=1;
	        for(int i=x1;i<x2+1;i++)
	        {
	        	count=0;
	        	for( z=y1;z<y2;z++)
	        	{
	        		 index=z*picw+i;
	        		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	        		 G = (pix[index] >> 8) & 0xff;
	        		 B =  pix[index] & 0xff;
	        		if(R == 255 && G == 255 && B == 255)
	        		{
						if((previous+1)==i)
						{					
							//bmpOut2.setPixel(i, y1, Color.RED);
							chracterSplit[a][inc]=previous;
							inc++;
							break;
						}
						else
							break;
	        		}
	        		else
	        		{
	        			count++;	
	        		}
	        		
	        	}
	        	if(count==(y2-y1))
	        	{	
	        		if((previous+1)!=i)
	        		{
	        			//bmpOut2.setPixel(i, y1, Color.RED);
	        			chracterSplit[a][inc]=i;
	        			inc++;
	        		}
	        		previous=i;
	        	}
	        	
	        }
	        chracterSplit[a][0]=inc-1;
	       a++;
	    }
	   // chracterSplit[0][0]=inc;
	    return chracterSplit;	
	}
	public int[][] findWord(Bitmap bmp,int [][]lineSplit,int size)
	{	
		int picw=bmp.getWidth();
		int pich=bmp.getHeight();    
		int[] pix = new int[picw * pich];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    int R=0, G=0, B=0,index=0,count=0, count1=0, inc=1 ,z=0  , max=0, min =0 ;
		int previous,y1,y2;
		int a,x1,x2;
	    int wordSplit[][] = new int [70][70];
		int distBetweenChar[]=new int [1000];
	    a=0;
	    for(int j=1;j<size;j=j+1)
	    {
	    	x1=lineSplit[j][0];
	    	x2=lineSplit[j][1];
		    y1=lineSplit[j][2];
		    y2=lineSplit[j][3];
		    previous=0;
		   
		    inc=1;
	        for(int i=x1;i<x2+1;i++)
	        {
	        	count=0;
	        	for( z=y1;z<y2;z++)
	        	{
	        		 index=z*picw+i;
	        		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	        		 G = (pix[index] >> 8) & 0xff;
	        		 B =  pix[index] & 0xff;
	        		if(R == 0 && G == 0 && B == 0)
	        		{
						if(	(( previous + 1 )== i ) && ( count1 > 0 ) )
						{	
							distBetweenChar[inc]=count1;
							inc++;
							distBetweenChar[0] =inc;
							count1=0;				
							//bmp.setPixel(i, y1, Color.RED);
							
							break;
						}
						else
							break;
	        		}
	        		else
	        		{
	        			count++;	
	        		}
	        		
	        	}
	        	if(count==(y2-y1))
	        	{	
	        		
	        		count1++;
	        		previous=i;
	        		//bmp.setPixel(i, y1, Color.RED);
	        	}
	        	
	        }
	        //chracterSplit[a][0]=inc-1;
	      // a++;
	    }
	    min=min(distBetweenChar);
	    max= max(distBetweenChar);
	    int dist = max - min ;
	    double estimate;
	    if((dist)<min)
	    	estimate=100;
	    else 
	    	estimate = dist / 2 ;
		
	    
		
		estimate= (int) (estimate+.5);
		/************************************************************************/
		
		int fistend;
		//bmp.setPixel(lineSplit[1][0], 10, Color.RED);
		for(int j=1;j<size;j++)
	    {
			count1 = 0;
	    	x1=lineSplit[j][0];
	    	x2=lineSplit[j][1];
		    y1=lineSplit[j][2];
		    y2=lineSplit[j][3];
		    previous=x1;
		    fistend=x1;
		    wordSplit[a][1]=x1;
		    inc=2;
	        for(int i=x1;i<x2;i++)
	        {
	        	count=0;
	        	for( z=y1;z<y2;z++)
	        	{
	        		 index=z*picw+i;
	        		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	        		 G = (pix[index] >> 8) & 0xff;
	        		 B =  pix[index] & 0xff;
	        		if(R == 0 && G == 0 && B == 0)
	        		{
						if(	( ( previous + 1 ) == i ) && ( count1 > (estimate) ))
						{	
							//bmp.setPixel(previous, y1, Color.RED);
							wordSplit[a][inc]=fistend;
							inc++;
							wordSplit[a][inc]=previous;
							inc++;
							
							//bmp.setPixel(fistend, y1, Color.BLUE);
							count1=0;				
							
							break;
						}
						else{
							count1 = 0;
							break;
							}
	        		}
	        		else
	        		{
	        			count++;	
	        		}
	        		
	        	}
	        	if(count==(y2-y1))
	        	{	
	        		if((previous+1)!=i)
	        		{
	        			fistend=i;
	        			//bmp.setPixel(i, y1, Color.RED);
	        			//inc++;
	        		}
	        		count1++;
	        		previous=i;
	        		//bmp.setPixel(i, y1, Color.RED);
	        	}
	        	
	        }
	        wordSplit[a][inc]=x2;
	        wordSplit[a][0]=inc+1;
	        a++;
	    }
		
		//distBetweenChar[0]= (int) estimate;
		
		//distBetweenChar[2]=min;
	    return wordSplit;	
	}
	public int min(int arr[]){
		int min=arr[1];
		for(int x=1 ;x<arr[0];x++){
			if(min>arr[x])	{
				min=arr[x];
			}
			
		}
		return min;
		
	}
	
	public int max(int arr[]){
		int max=arr[1];
		for(int x=1 ;x<arr[0];x++){
			if(max<arr[x]){
				max=arr[x];
			}
			
		}
		return max;
		
	}
	public Bitmap getCropedRegion(Bitmap orignalImage , int x1 , int x2 , int y1 , int y2 )
	{
		int picw = orignalImage.getWidth();
		int pich = orignalImage.getHeight();
		Bitmap bmp = Bitmap.createBitmap(orignalImage, 0, 0, orignalImage.getWidth(), orignalImage.getHeight());

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
}
