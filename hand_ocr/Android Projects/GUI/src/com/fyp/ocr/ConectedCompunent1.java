package com.fyp.ocr;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ConectedCompunent1 {
	public Bitmap  conectedCompunentMain(Bitmap bmp)
	{
		
		int n[]=conectedCompunent(bmp);
		int n1[] = resetlbl(n);
		Bitmap array[]=getShapes(bmp, n1, 1, n1[0]);
		bmp=invalidObjectRemovel(bmp, array, n1[0]);
		return bmp;
	}
	public Bitmap []  conectedCompunentMain3(Bitmap img)
	{
		
		int n[]=conectedCompunent(img);
		int n1[] = resetlbl(n);
		android.util.Log.i(" sha " , " "+ n1[0]+" e" );
		Bitmap array[]=getShapes(img, n1, 1, n1[0]);
		return array;
	}
	public Bitmap [] conectedCompunentMain1(Bitmap bmp)
	{
		int n[]=conectedCompunent(bmp);
		int n1 [] = resetLable1(bmp, n);
		Bitmap result []=getShapes(bmp, n1, 1, n1[0]);
		return result;
	}
	
	public int[] conectedCompunent(Bitmap bmp)
	{
		int picw = bmp.getWidth(),pich = bmp.getHeight();
	 	int R=0, G=0, B=0,index=0,index2=0;
    	int xvalue = 0;
    	int yvalue = 0;
    	int[] pix = new int[picw * pich];
    	int[] structure = new int[picw * pich];
    	int[] n = new int[picw * pich];
    	
    	/** Loop on image */
    	bmp.getPixels(pix, 0, picw, 0, 0, picw, pich);
    	for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
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
    	/*Bitmap bmp3 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
    	Bitmap bmp4 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
    	*///img.setImageBitmap(bmp4);
    	picw = bmp2.getWidth();
    	pich = bmp2.getHeight();
    	
    	for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		bmp2.setPixel(x, y,Color.WHITE);
        //		bmp3.setPixel(x, y,Color.WHITE);
            }
        }
    	for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		index = y * picw + x;
        		if(structure[index] == 1)
        		{	
        			bmp2.setPixel(x, y, Color.BLACK);     		}
            }
        }
    	int label=1000;
    	for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
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
	public Bitmap invalidObjectRemovel(Bitmap bmp,Bitmap []objects,int inc)
	{
		int index=0;
		int R=0,G=0,B=0;
		Segment call=new Segment();
		int picw =bmp.getWidth();
		int pich =bmp.getHeight();
		for(int sa=1;sa<inc;sa++)
    	{
    		int s[]=call.regionOfIntrest(objects[sa], 0, picw, 0, pich);
    		int h=s[3]-s[2];
    		int w=s[1]-s[0];
    		if(h<10  || w> (h*2))
    		{
    			int[] pix = new int[picw * pich];
    		    objects[sa].getPixels(pix, 0, picw, 0, 0, picw, pich); 
    			for (int y = s[2]; y < s[3]; y++)
				{
					for (int x = s[0]; x < s[1]; x++)
					{
						index = y * picw + x;
						 R = (pix[index] >> 16) & 0xff; //bitwise shifting
						 G = (pix[index] >> 8) & 0xff;
						    B =  pix[index] & 0xff;    		 
						   	if(R == 0 && G == 0 && B == 0)
				     		{
						   		bmp.setPixel(x, y, Color.WHITE);
				     		}
				     	}
						//bmp.setPixel(x, y, Color.WHITE);
					
				}
    		}
    		
    	}
		return bmp;
	}
	
	public Bitmap [] getShapes(Bitmap bmp,int n[],int start,int end)
	{
		int picw = bmp.getWidth();
		int pich = bmp.getHeight();
		int index=0;
		Bitmap bmpCopy = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
		
		for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		index = y * picw + x;
        		//if(arr[index]!=0)
        		{
        			
        			bmpCopy.setPixel(x, y, Color.WHITE);
        		}
        		//arr[index]=0;
            }
        }	
		
		
		//Bitmap[][] sha=new Bitmap[(5)][80];
		Bitmap[] sha=new Bitmap[end];
		Bitmap[] sha1=new Bitmap[500];
		Bitmap sh;
		//int arr[] = new int[picw*pich];
		for(int i=start;i<end;i++)
    	{
			sh = Bitmap.createBitmap(bmpCopy, 0, 0, picw, pich);

    		for (int y = 0; y < pich; y++){
            	for (int x = 0; x < picw; x++) {
            		index = y * picw + x;
            		if(n[index]==i){
            			sh.setPixel(x, y, Color.BLACK);
            			}
            		}
            }
    		sha[i]=sh; 
    		sh=null;
 
		}
		return sha;
	}
	public int[] removal( Bitmap img , int arr [] )
	{
		int picw = img.getWidth() , pich = img .getHeight() ; 
		int index = 0 ;
		for( int x = 0 ; x < picw ; x++ )
		{
			for(int y = 0 ; y < pich ; y++ )
			{
				index = ( y  * picw ) + x ;
				if ( arr [ index ] > 1900 ){
					arr = countPixel( arr, arr [index ], picw, pich, x - 20, x + 20 , y-20 , y + 20 ); 
				}
			}
		}
		return arr ;
	}
	public int[] countPixel ( int arr[] , int num , int picw ,int pich , int x1 , int x2 , int y1 , int y2 )
	{
		int index = 0  , count = 0 , limit = 10  ;
		boolean noise = true ;
		if ( x1 < 0 )    /// cheching array not exeead from bound limit
			x1 = 0 ;
		if ( x2 > picw )
			x2 = picw ;
		if ( y1 < 0 )
			y1 = 0 ;
		if ( y2 > pich )
			y2 = pich ;
		for ( int x = x1 ; x < x2 ; x++ ){
			for ( int y = y1 ; y < y2 ; y++ ){
				index = (y * picw ) + x ;
				if ( arr [index ] == num ){
					count ++ ;
				}
			}//iner for end
			if ( count > limit ){
				noise =  false ;
				break ; 
			}
		}//outer for end 
		if ( noise == true ){
			arr =  resetArrayIndex(arr, num, picw, x1, x2, y1, y2,  ( 0 ) );
		}
		/*else{
			android.util.Log.i("r",""+ num );
			return resetArrayIndex(arr, num, picw, x1, x2, y1, y2, 0 );
		}*/
		return arr ;
	}
	public int [] resetArrayIndex (int arr [] , int num , int picw , int x1 , int x2 , int y1 , int y2  ,
			int assigenNo){
		int index = 0;
		for ( int x = x1 ; x < x2 ; x++ ){
			for ( int y = y1 ; y < y2 ; y++ ){
				index = (y * picw ) + x ;
				if ( arr [index ] == num ){
					arr[index] = assigenNo ;
				}
			}//iner for end
		}//outer for end 
		return arr ;
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
	public int[]  resetLable( Bitmap img ,int arr[])  // reset label in sequnce //
    {
		Segment seg = new Segment();
		int picw = img.getWidth() , pich = img.getHeight();
		int roi[] = seg.regionOfIntrest(img, 0, picw, 0, pich);
		int mid =  ( roi[2] + roi [3] ) / 2;
		int  lable =1,value=0;
		int index = 1;
		for (int x = roi[0] ; x < roi[1] ; x++) {
			 int R  = Color.red ( img.getPixel ( x , mid ) );
			index = (  mid * picw ) + x ;
			
			if( R == 0 && lable < arr[ index ] )	{
				//int nem = arr [ index ];
				//lable ++;
	    		value =arr[ index ];
	    		for ( int i = 0 ; i < arr.length ; i++  )
	    		{
	        		if(  arr [i] ==  value  ){
	        			arr [i] = lable ;
	        		}
	        	}
	    		lable ++ ;
	    	}	
	    }
		android.util.Log.i("Next",""+ lable);
		arr[0] = lable ;
		return arr;
    }
	public int[]  resetLable1( Bitmap img ,int arr[])  // reset label in sequnce //
    {
		Segment seg = new Segment();
		int picw = img.getWidth() , pich = img.getHeight();
		int roi[] = seg.regionOfIntrest(img, 0, picw, 0, pich);
		int mid =  ( roi[2] + roi [3] ) / 2;
		int  lable =1,value=0;
		int index = 0;
		for (int x = roi[0] ; x < roi[1] ; x++) {
			 //int R  = Color.red ( img.getPixel ( x , mid ) );
			index = (  mid * picw ) + x ;
			//android.util.Log.i("Next",""+ R);
			if(  lable < arr [index] )	{
				//int nem = arr [ index ];
				
	    		value =arr[ index ];
	    		for ( int i = 0 ; i < arr.length ; i++  )
	    		{
	        		if(  arr [i] ==  value  ){
	        			arr [i] = lable ;
	        		}
	        	}
	    		lable ++;
	    	}	
	    }
		android.util.Log.i("Next",""+ lable);
		arr[0] = lable ;
		return arr;
    }
	
	public Bitmap [] sort(Bitmap bmp,Bitmap extrectedImages[],int size)
	{
		int pich = bmp.getHeight();
		int picw = bmp.getWidth();
		Segment call=new Segment();
		int result[]= new int [size];
		int roi[]=new int [4];
		for(int i=1;i<size;i++)
		{
			roi=call.regionOfIntrest(extrectedImages[i] , 0 , picw ,0, pich);
			result[i] = roi[1];
		}
		//int finalArray[]=new int[1000];
		int temp=0;
		Bitmap temp1;
		for(int i=1;i<size;i++)
    	{
    		for(int a=1;a<size;a++)
    		{
    			if(result[a]>result[i])
    			{
    				temp=result[i];
    				result[i]=result[a];
    				result[a]=temp;
    				
    				temp1=extrectedImages[i];
    				extrectedImages[i]=extrectedImages[a];
    				extrectedImages[a]=temp1;
    				
    				/*finalArray[inde]=butt[a];
    				finalArray[inde+1]=i;
    				inde=inde+2;*/
    			}
    		}
    	}
		//result[0]=size;
		return extrectedImages;
	}
	public int [] umer(Bitmap bmp,int whight,int wWidth)
	{
		int picw=bmp.getWidth();
		int pich=bmp.getHeight();
		int index=0;
		int xStart=0,yEnd=0;
		int x;
		int []value=new int[(3*(picw*pich)/(whight*wWidth))];
		for(int y=0 ;y< pich-(pich%8) ; y+=whight)
		{	yEnd+=whight;
			for( x=0 ; x < picw-(picw%8);x+=wWidth)
			{
				//index=y*picw+x;
				int temp []=laiba(bmp,y,yEnd,xStart,x);
				value[index]=temp[0];
				value[index+1]=temp[1];
				value[index+2]=temp[2];
				index+=3;
				xStart=x;
			}
			xStart=0;
			//pre=((y+8)*picw)+1;
		}
		return value;
	}
	public int[] laiba(Bitmap bmp,int starty1,int endy1,int startx1,int endx1)
	{
		int endValue[]=new int[50];
		int picw=bmp.getWidth();int pich=bmp.getHeight();
		int []pix=new int[picw*pich];
		bmp.getPixels(pix, 0, picw, 0, 0, picw, pich);
		int R=0,G=0,B=0;
		int array[]=new int [64];
		int index=0;
		int index1=0;
		//int z=end-start;
		for(int y=starty1 ;y<endy1;y++)
		{
			for(int x=startx1 ;x<endx1;x++)
			{
				 index = y * picw + x;   	
	    	     R = (pix[index] >> 16) & 0xff;
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 //index1=y*z+x;
	    		 array[index1]=R+G+B;
	    		 index1++;

	    		 // end++;
	    		// pix[index] = 0xff000000 | (R-R << 16) | (G-G << 8) | B-B;
	    		// bmp.setPixel(8,8, Color.GRAY);
			}
    		// start++;
		}
		//bmp= Bitmap.createBitmap(pix, picw, pich, Config.ARGB_8888);
		endValue[0]=min(array);
		endValue[1]=max(array);
		endValue[2]=average(array);
		return endValue;
	}
	public int min(int arr[])
	{
		int min=arr[0];
		for(int x=0 ;x<arr.length;x++)
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
		int max=arr[0];
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
		for(int x=0 ;x<arr.length;x++)
		{
			avr+=arr[x];
			
		}
		avr=avr/arr.length;
		return avr;
		
	}
	public Bitmap  image(Bitmap img , int arr [] ){
		int index = 0 ,  picw = img.getWidth() , pich = img.getHeight();
		for (int x = 0 ; x < picw ; x ++ ){
			for ( int y = 0 ; y < pich ; y ++){
				index = ( y * picw ) + x ;
				if ( arr [index ]  > 0 ){
					img.setPixel(x, y, Color.RED);
				}
			}
		}
		return img ;
	}
	
	
	
}
