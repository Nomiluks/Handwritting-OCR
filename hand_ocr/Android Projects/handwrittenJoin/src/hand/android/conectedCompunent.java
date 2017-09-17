package hand.android;

import android.graphics.Bitmap;
import android.graphics.Color;

public class conectedCompunent {
	public Bitmap  conectedCompunentMain(Bitmap bmp)
	{
		
		int n[]=conectedCompunent(bmp);
		int n1[] = resetlbl(n);
		Bitmap array[]=getShapes(bmp, n1, 1, n1[0]);
		bmp=invalidObjectRemovel(bmp, array, n1[0]);
		return bmp;
	}
	public Bitmap[] conectedCompunentMain1(Bitmap bmp)
	{
		int n[]=conectedCompunent(bmp);
		int n1[] = resetlbl(n);
		Bitmap array[]=getShapes(bmp, n1, 1, n1[0]);
		Bitmap result[] =sort(array, n1[0]);
		return result;
	}
	public struct conectedCompunentMain2(Bitmap bmp)
	{
		struct result = new struct();
		int n[]=conectedCompunent(bmp);
		int n1[] = resetlbl(n);
		Bitmap array[]=getShapes(bmp, n1, 1, n1[0]);
		result.bmp=sort(array, n1[0]);
		result.counter = n1[0] ;
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
 
    	for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {	
        		index = y * picw + x; 
        		n[index] = 0;
            }
        }
    	
    	
    	
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
    	Bitmap bmp3 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
    	Bitmap bmp4 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
    	//img.setImageBitmap(bmp4);
    	picw = bmp2.getWidth();
    	pich = bmp2.getHeight();
    	
    	for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		bmp2.setPixel(x, y,Color.WHITE);
        		bmp3.setPixel(x, y,Color.WHITE);
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
    	//img2.setImageBitmap(bmp2);
    	//((((y-1) * picw)+x)+1);	//upper++
    	//((((y-1) * picw)+x)-1);	//upper--
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
		handwrittenSegmentation call=new handwrittenSegmentation();
		int picw =bmp.getWidth();
		int pich =bmp.getHeight();
		for(int sa=1;sa<inc;sa++)
    	{
    		int s[]=call.regionOfIntrest(objects[sa], 0, picw, 0, pich);
    		int h=s[3]-s[2];
    		int w=s[1]-s[0];
    		if(h<9 )
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
	public Bitmap [] abc(Bitmap bmp,int n[],int picw,int pich)
	{
		n[0]=100;
		int index,start=0;boolean a=true;
		Bitmap[] sha=new Bitmap[1000];
		Bitmap sh;
		boolean butt =true;
		int arr[] = new int[picw*pich];
		for(int i=0;i<76;i++)
    	{
			sh = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());

			butt=true;
    		a=true;
	    	for (int y = 0; y < pich; y++)
	        {
	        	for (int x = 0; x < picw; x++)
	            {
	        		index = y * picw + x;
	        		if(n[index]!=0){
	        			if(a==true){
	        			
	        			//	BismillahActivity.inc++;
	        				start=n[index];
	        				butt=false;
	        				a=false;
	        			}
	        			
	        			if(start==n[index]){
	        				
	        				//arr[index]=n[index];
	        				n[index]=0;
	        				sh.setPixel(x, y, Color.RED);
	        			}
	        			else
	        				arr[index]=0;
	        		}
	        		else
	        			arr[index]=0;
	            }
	        }
	    	//sha[i]=sh;//ch(bmp,arr);
	    	/*if(butt==true)
	    	{
	    		break;
	    		
	    	}
	    	else*/
	    		sha[i]=sh;
	    	
		}
		return sha;
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
		//Bitmap[] sha1=new Bitmap[500];
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
	/*public int [] neglection(Bitmap extrectedImage[],Bitmap bmp )
	{
		//int br=0;
		int picw=bmp.getWidth();
		int pich=bmp.getHeight();
		int s[]=call.regionOfIntrest(bmp,0, picw,0, pich);
		int line[]=call.findLines(bmp, picw, pich, s[0], s[1], s[2], s[3]);
		for(int a=0;a<line.length;a++)
	    {
	    	if(line[a]==0)
	    	{
	    		br=a;
	    		break;	
	    	}
	    }
		//int sh[];
		//int butt=BismillahActivity.inc;
		int result[]=new int [1000];
		//result[990]=s[3]-s[2];

			for(int i=0;i<BismillahActivity.inc*2;i=i+2)
			{
				int sec[]=call.regionOfIntrest(extrectedImage[i/2],0,picw ,0, pich);
				result[i]=sec[0];
				result[i+1]=(sec[2]);//-(s[3]-s[2]);
				//result [10]=sec[0];
				//result[11]=sec[1];
				
				
			}
			//result[14]=s[0];
			//result[15]=s[2];
			return result;	
		}*/
	public Bitmap [] sort(Bitmap extrectedImages[],int size)
	{
		int pich = extrectedImages[1].getHeight();
		int picw = extrectedImages[1].getWidth();
		handwrittenSegmentation call=new handwrittenSegmentation();
		int result[]= new int [50];
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
    			if( result[a] > result[i] )
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
	
	
	
}
