package pkg.app.ocr;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;

public class ImageBasicOperations {
	
	public int[]  NoiseRemoverforROI(Bitmap bmp)
	{
		int roi[] = {};
		bmp            = bmp.copy(Config.ARGB_8888, true);
		int n[]        = conectedCompunent(bmp);
		int n1[]       = resetlbl(n);
		Bitmap array[] = getShapes(bmp, n1, 1, n1[0]);
		bmp            = invalidObjectRemovel(bmp, array, n1[0]);
		roi            = regionOfIntrest(bmp);
		return roi;
	}
	public int[] getROIpoints(Bitmap bmp)
	{
		int roi[] = {};
		roi       = regionOfIntrest(bmp);
		return roi;
	}
	public Bitmap  NoiseRemover(Bitmap bmp)
	{
		int roi[] = {};
		bmp            = bmp.copy(Config.ARGB_8888, true);
		int n[]        = conectedCompunent(bmp);
		int n1[]       = resetlbl(n);
		Bitmap array[] = getShapes(bmp, n1, 1, n1[0]);
		bmp            = invalidObjectRemovel(bmp, array, n1[0]);
		roi            = regionOfIntrest(bmp);
		bmp            = getCropedRegion(bmp, roi[0], roi[1], roi[2], roi[3]);
		return bmp;
	}
	public Bitmap [] getCharactersLatest(Bitmap bmp)
	{
		bmp = bmp.copy(Config.ARGB_8888, true);
		int Chracter_roi[] = {};
		int n[]=conectedCompunent(bmp);
		int n1 [] = resetLable1(bmp, n);
		Bitmap result []=getShapes(bmp, n1, 1, n1[0]);
		for(int i = 1 ; i<result.length; i++)
		{
		    Chracter_roi = regionOfIntrest(result[i]);
		    result[i] = getCropedRegion(result[i], Chracter_roi[0], Chracter_roi[1], Chracter_roi[2], Chracter_roi[3]);
		    result[i] = result[i].copy(Config.ARGB_8888, true);
		    result[i] = rescaleer(result[i], 15, 16);
		}
		return result;
	}
	public Bitmap[] getCharacters(Bitmap bmp)
	{
		bmp = bmp.copy(Config.ARGB_8888, true);
		int Chracter_roi[] = {};
		int    n[]      =	conectedCompunent(bmp);
		int    n1[]     = 	resetlbl(n);
		Bitmap array[]  =	getShapes(bmp, n1, 1, n1[0]);
		Bitmap result[] = 	new Bitmap [n1[0]];
		result          =	sort(array, n1[0]);
		for(int i = 1 ; i<result.length; i++)
		{
		    Chracter_roi = regionOfIntrest(result[i]);
		    result[i] = getCropedRegion(result[i], Chracter_roi[0], Chracter_roi[1], Chracter_roi[2], Chracter_roi[3]);
		    result[i] = result[i].copy(Config.ARGB_8888, true);
		    result[i] = rescaleer(result[i], 15, 16);
		}
		return result;
	}
	public int[]  resetLable1( Bitmap img ,int arr[])  // reset label in sequnce //
    {
		SegmentationProcess seg = new SegmentationProcess();
		int picw = img.getWidth() , pich = img.getHeight();
		int roi[] = seg.regionOfIntrest(img, 0, picw, 0, pich);
		int mid =  ( roi[2] + roi [3] ) / 2;
		int  lable =1,value=0;
		int index = 0;
		for (int x = roi[0] ; x < roi[1] ; x++) 
		{
			index = (  mid * picw ) + x ;
			if(  lable < arr [index] )	
			{
	    		value =arr[ index ];
	    		for ( int i = 0 ; i < arr.length ; i++  )
	    		{
	        		if(  arr [i] ==  value  )
	        		{
	        			arr [i] = lable ;
	        		}
	        	}
	    		lable ++;
	    	}	
	    }
		arr[0] = lable ;
		return arr;
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

		
		Bitmap bmp2 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
		Bitmap bmp3 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
		Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
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
	/*public int[] conectedCompunent(Bitmap bmp)
	{
		boolean com= false;
		int picw = bmp.getWidth(),pich = bmp.getHeight();
	 	int R=0, G=0, B=0,index=0,index1 = 0 , index2=0  , index3 = 0 ;
    	int xvalue = 0;
    	int yvalue = 0;
    	int[] pix = new int[picw * pich];
    	int[] structure = new int[picw * pich];
    	int[] n = new int[picw * pich];
    	
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
    	Bitmap bmp2 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
    	//img.setImageBitmap(bmp4);
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
    	//img2.setImageBitmap(bmp2);
    	//((((y-1) * picw)+x)+1);	//upper++
    	//((((y-1) * picw)+x)-1);	//upper--
    	int label=2000;
    	for (int y = 1; y < pich; y++)
        {
        	for (int x = 1; x < picw; x++)
            {
        		
	        		index = y * picw + x;

	        		index1 = ( ( (y - 1 ) * picw) + x )-1 ;
	        		index2 = ((y-1) * picw) + x;
	        		index3 =  (((y-1) * picw) + x ) + 1 ;
	        		
	        		if(structure[index] == 1)
	        		{
	        			if (( structure[index] == structure [index-1] && structure[index] == structure[index2]))
	        			{ 				com = true;
								yvalue 	 = n[index2];	
						}
	        			else if((structure[index] == structure[index-1] && structure[index] == structure[index3]) )
	        			{			com = true;
	        						yvalue 	 = n[ index3];}
	        		  	if(com == true)
	        			 {
	        		  		com = false;
		        					n[index] = n[index-1];
		        					xvalue 	 = n[index-1];
		        					//yvalue 	 = n[index2];
		        					if(n[index] != yvalue)
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
	        			 else if(structure[index]==structure[index1])
	        			 {
	        				n[index] = n[index1];
	        			 }
	        			 else if(structure[index]==structure[index3])
	        			 {
	        				n[index] = n[index3];
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
	}*/

	public int[]  resetlbl(int arr[])  // reset label in sequence //
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
	public Bitmap[] getShapes(Bitmap bmp,int n[],int start,int end)
	{
		int picw = bmp.getWidth();
		int pich = bmp.getHeight();
		int index=0;
		Bitmap bmpCopy = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
		
		for (int y = 0; y < pich; y++)
	    {
	    	for (int x = 0; x < picw; x++)
	        {
	    		bmpCopy.setPixel(x, y, Color.WHITE);
	        }
	    }	
		
		Bitmap[] sha=new Bitmap[end];
		Bitmap sh;
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
	public Bitmap invalidObjectRemovel(Bitmap bmp,Bitmap []objects,int inc)
	{
		int index=0;
		int R=0,G=0,B=0;
		int picw =bmp.getWidth();
		int pich =bmp.getHeight();
		for(int sa=1;sa<inc;sa++)
		{
			int s[] = regionOfIntrest(objects[sa]);
			int h=s[3]-s[2];
			int w=s[1]-s[0];
			if(h<10 && w<16)
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
					}
			}
			
		}
		return bmp;
	}
	public Bitmap [] sort(Bitmap extrectedImages[],int size)
	{
		int result[]= new int [50];
		int roi[]=new int [4];
		for(int i=1;i<size;i++)
		{
			roi = regionOfIntrest(extrectedImages[i]);
			result[i] = roi[1];
		}
		
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
				}
			}
		}
		return extrectedImages;
	}
	public int[] regionOfIntrest(Bitmap bmp)
	{
		int picw     = bmp.getWidth();
		int pich     = bmp.getHeight();
	    int[] pix = new int[picw * pich];
	    int[] points = new int[4];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    int R=0, G=0, B=0,index=0;
	    int y1=0,y2=0,x1=0,x2=0;
	    boolean check = true;
	    
	    for (int y = 0; y < pich; y++)
	    {
	    	for (int x = 0; x < picw; x++)
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
	    for (int y = 0; y < picw; y++)
	    {
	    	for (int x = 0; x < pich; x++)
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
	    int x = x2-x1 ;
	    int y = y2 - y1 ;
	    if (x<= 0){
	    	x = 1 ;
	    }
	    if(y<= 0){
	    	y = 1 ;
	    }
	    Bitmap temp = Bitmap.createBitmap(cop, x, y, Config.ARGB_8888);
	  return (temp.copy(Config.ARGB_8888, true ));
	}
	public Bitmap getBaseline(Bitmap bmp)
	{
		//Bitmap bmpout = bmp;//copying image to new bitmap
		int width  = bmp.getWidth();//getting new width of an image
		int height = bmp.getHeight ();//getting new height of an image 
		int[] str = new int[width * height];//making an array of image size
		Bitmap bmpout = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
		int[] pix = new int[width * height];//making an array of image size
		
		bmpout.getPixels(pix, 0, width, 0, 0, width, height); //getting pixels of an image into array
		
		int R=0, G=0, B=0,index=0;//loop variables
	    for (int y = 0; y < height; y++)//loop for making a one zero array from image
	    {
	    	for (int x = 0; x < width; x++)
	        {
	    		 index = y * width + x;
	    		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	    		 if(R ==0  || G ==0 || B ==0)
	    		 {
	    			 str[index] = 1;
	    		 }
	    		 else
	    		 {
	    			 str[index] = 0;
	    		 }
	        }
	    }
	    Bitmap final_bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);//making new image from array 
	    for (int y = 0; y < height; y++)//writing on image by checking one and zero position in array
	    {
	    	for (int x = 0; x < width; x++)
	        {
	    		 index = y * width + x;
	    		 if(str[index] == 1)
	    		 {
	    			 final_bitmap.setPixel(x, y, Color.BLACK);
	    		 }
	    		 else
	    		 {
	    			 final_bitmap.setPixel(x, y, Color.WHITE);
	    		 }
	        }
	    }

		int line = height/2;//getting center of an image
		///loop variables
		int make_array_one = 0;
		int count = 0;
		boolean set = false;
	    for (int y = line; y >= 0; y--)//setting threshold for baseline from center to upward
	    {
	    	for (int x = width; x >= 0; x--)
	    	{	
	    		 index = y * width+x;
	    		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	        	if(R ==0  || G ==0 || B ==0)
	        	{
	        		count++;
	        	}
	    	}
	    	make_array_one++;
	    	//text.append(" "+count);
	    	count = 0;
	    }
	    
	    int [] set_upper_thresh = new int [make_array_one];
	    int inc = 0;
	    for (int y = line; y >= 0; y--)//setting threshold for baseline from centre to upward
	    {
	    	for (int x = width; x >= 0; x--)
	    	{	
	    		 index = y * width+x;
	    		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	        	if(R ==0  || G ==0 || B ==0)
	        	{
	        		count++;
	        	}
	    	}
	    	set_upper_thresh[inc] = count;
	    	count=0;
	    	inc++;
	    }
	    int subtracter = 0;
	    subtracter = set_upper_thresh.length/2;
	    int n = set_upper_thresh.length-subtracter;
	    int swapping [] = new int [subtracter];
	    int increment = 0, temp = 0;
	    for(int i=set_upper_thresh.length-1; i>=n; i--)
	    {
	    	swapping[increment] = set_upper_thresh[i];
	    	increment++;
	    }
	    swapping[0] = 1000;
	    swapping[1] = 1000;
	    for(int i=0;i<swapping.length;i++)
	    {
	    	temp = swapping[i];
	    	for(int j=0;j<swapping.length;j++)
	    	{
	    		if(temp>swapping[j])
	    		{
	    			temp = swapping[j];
	    		} 
	    	}
	    }
	    for (int y = line; y >= 0; y--)//setting threshold for baseline from center to upward
	    {
	    	for (int x = width; x >= 0; x--)
	    	{	
	    		 index = y * width+x;
	    		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	        	if(R ==0  || G ==0 || B ==0)
	        	{
	        		count++;
	        	}
	    	}
	    	if(count == temp)
	    	{
	    		set = true;
	    		/*for (int x = 0; x < width; x++)
	        	{
	        		 final_bitmap.setPixel(x, y, Color.RED);
	        	}*/
	    		for(int a=0; a<=(y);a++)
	    		{
	    			for (int x = 0; x < width; x++)
	            	{
	    				index = a * width+x;
	           		 	R = (pix[index] >> 16) & 0xff; //bitwise shifting
	           		 	G = (pix[index] >> 8) & 0xff;
	           		 	B =  pix[index] & 0xff;
	           		 
	           		 	if(R ==0  || G ==0 || B ==0)
	           		 	{
	           		 		final_bitmap.setPixel(x, a, Color.LTGRAY);
	           		 	} 
	            	}
	    		}
	    	}
	    	count = 0;
	    	if(set == true)
	    	{
	    		break;
	    	}
	    }
	    //////////////////////////////////////////////////////////////
	    set = false;
	    int make_array_two = 0;
	    for (int y = line; y < height; y++)//setting threshold for baseline from centre to downward
	    {
	    	for (int x = 0; x < width; x++)
	    	{	
	    		 index = y * width+x;
	    		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	        	if(R ==0  || G ==0 || B ==0)
	        	{
	        		count++;
	        	}
	    	}
	    	make_array_two++;
	    	count = 0;
	    }
	    int [] set_lower_thresh = new int [make_array_two];
	    inc = 0; count = 0;
	    for (int y = line; y < height; y++)//setting threshold for baseline from centre to downward
	    {
	    	for (int x = 0; x < width; x++)
	    	{	
	    		 index = y * width+x;
	    		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	        	if(R ==0  || G ==0 || B ==0)
	        	{
	        		count++;
	        	}
	    	}
	    	set_lower_thresh[inc] = count;
	    	count=0;
	    	inc++;
	    }
	    subtracter = 0;
	    subtracter = set_lower_thresh.length/2;
	    n = set_upper_thresh.length-subtracter;
	    int swapping2 [] = new int [subtracter];
	    n = set_lower_thresh.length-subtracter;
	    increment = 0;
	    for(int i=set_lower_thresh.length-1; i>=n; i--)
	    {
	    	swapping2[increment] = set_lower_thresh[i];
	    	increment++;
	    }
	    
	    temp=0;
	    swapping2[0] = 1000;
	    for(int i=0;i<swapping2.length;i++)
	    {
	    	temp = swapping2[i];
	    	for(int j=0;j<swapping2.length;j++)
	    	{
	    		if(temp>swapping2[j])
	    		{
	    			temp = swapping2[j];
	    		} 
	    	}
	    }
	    for (int y = line; y < height; y++)//setting threshold for baseline from centre to downward
	    {
	    	for (int x = 0; x < width; x++)
	    	{	
	    		 index = y * width+x;
	    		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	        	if(R ==0  || G ==0 || B ==0)
	        	{
	        		count++;
	        	}
	    	}
	    	if(count == temp)
	    	{
	    		set = true;
	    		/*for (int x = 0; x < width; x++)
	        	{
	        		 final_bitmap.setPixel(x, y, Color.RED);
	        	}*/
	    		for(int a=y; a<height;a++)
	    		{
	    			for (int x = 0; x < width; x++)
	            	{
	    				 index = a * width+x;
	            		 R = (pix[index] >> 16) & 0xff; //bitwise shifting
	            		 G = (pix[index] >> 8) & 0xff;
	            		 B =  pix[index] & 0xff;
	            		 
	                	if(R ==0  || G ==0 || B ==0)
	                	{
	                		 final_bitmap.setPixel(x, a, Color.LTGRAY);
	                	}
	            	}
	    		}
	    	}
	    	count = 0;
	    	if(set == true)
	    	{
	    		break;
	    	}
	    }
	    return final_bitmap;
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
	        int x_ratio = (int)((w1<<16)/w2) +1;
	        int y_ratio = (int)((h1<<16)/h2) +1;
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

	public Bitmap changeColor( Bitmap orignalImage ,Bitmap img , int x1 , int x2 , int y1 , int y2 )
	{
		int picw = img.getWidth();	int pich = img.getHeight();

		int[] pix = new int[picw * pich];
	    img.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    int R=0, G=0, B=0,index=0;
	    for (int y = y1 ; y < y2; y++) {
	    	for (int x = x1 ; x < x2 ; x++ ) {
	    		 index = y * picw + x;
	    	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	    		 if(R == 0 || G == 0 || B == 0){
	    			 orignalImage.setPixel(x, y, Color.WHITE);
	    		 }
	    	}
	    }
	    return orignalImage;
	}
	public int thickness(Bitmap img,int xSize,int picw,int ySize,int pich)
	{
		
		 int index=0;int count=0,dense=0;
		 boolean butt=true,u=true;int inc=0;
		 int R=0,G=0,B=0;
		// int picw=img.getWidth(),pich=img.getHeight();
		 int []store=new int[55];
		 int counter=0;
		 int[] pix = new int[picw * pich];
		   
		    img.getPixels(pix, 0, picw, 0, 0, picw, pich);
		    for (int y = xSize; y < picw; y++)
	        {
		    	count=0;inc=0;dense=0;
		    	butt=true;u=true;
	        	for (int x = ySize; x < pich; x++)
	            {
	        		 index = x * picw+y;
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
	        	}//inner for end
	        	/*if(inc>0)	{
	        		break;
	        	}*/
	        	if(count>0&&inc>0&&counter<50)
	        	{
	        	
	        	
	        	if(count>inc)
			    {
			    	dense=inc;
			    }
			    else 
			    {
			    	dense=count;
			    	
			    }
	        	counter++;
	        	store[counter]=dense;
	        	//counter++;
	        	}
	        	else
	        	{
	        		//img.setPixel(y, ySize, Color.RED);
	        	}
	        	if(counter>50)
	        	{
	        		break;
	        	}
	        }
		    
		    store[0]=counter;
		    int avg=0;
		    for(int i =1 ; i<counter ; i++)
	    	{
	    		avg+=store[i];
	    	}
		    return (avg/counter);
	}
	public int [] handWritenSegment(Bitmap img,int xSize,int picw,int ySize,int pich ,int dense)
	{
		
		 int index=0;int count=0;
		 boolean butt=true,u=true,first=true,last=true;int inc=0;
		 int R=0,G=0,B=0;
		 int []split=new int[500];
		// int picw=img.getWidth(),pich=img.getHeight();
		 int counter=0,a=0;
		 int[] pix = new int[picw * pich];
		   
		    img = img.copy(Config.ARGB_8888, true);
		    img.getPixels(pix, 0, picw, 0, 0, picw, pich);
		    for (int y = xSize; y < picw; y+=1)
	        {
		    	count=0;inc=0;
		    	butt=true;u=true;
	        	for (int x = ySize; x < pich; x++)  {
	        		 //index = x * picw+y;
	        		 R  = Color.red(img.getPixel(y, x));
		        		 if(R == 0 ) {
		        			 if(butt==true)
		        			 {
		        				 if(R == 0 ) {
		        					 count++;
		        					 u=false;
				        		 }
		        			 }
			        		 else
			        			 if(u==false)  {
			        				 if(R == 0 )  {
			        					 inc++;
					        		 }
			        			 }
			        		 }
		        		 else {
		        			 if(u==false) {
		        				 //img.setPixel(4, 4, Color.RED);
		        				 butt=false;
		        				 if(inc>0){
		        					 break;
		        				 }
		        			 }
		        		 }
	        	}//inner for end
	        	/*if(inc>0)	{
	        		break;
	        	}*/
	        	if(count > 0 && inc > 0){
	        		if( first == true ){
	        			counter++;
	        			split[ counter ] = a;
	        			first=false;
	        			for(int i = ySize;i<pich-10;i++){
		        			//img.setPixel(a, i, Color.RED);
		        		}
	        			//img.setPixel(y-2, ySize, Color.GREEN);
	        		}
	        	//store[counter]=dense;
	        	//counter++;
	        	}
	        	else{
	        		if(inc < (dense *3) && count < (dense * 2)){
		        		if(first==false){
		        			counter++;
		        			split[counter]=y;
			        		for(int i = ySize+10;i<pich;i++){
			        			//img.setPixel(y, i, Color.GREEN);
			        		}
		        		}
		        		first=true;
		        	}
	        		if(first==true){
	        			a=y-1;
	        		}
	        	}
	       
	        }
		    if( ( counter % 2 ) == 1 ) {
		    	counter++;
		    	split[counter]=picw-2;
		    }
		    counter++;
		    split[0]=counter;
		    
		    
		    /*for (int y =1 ; y < split[0]; y+=2){
		    	for(int z=split[y];z>split[y]-2;z--){
		    		count=0;
		        	for (int x = ySize; x < pich; x++){
		        		R  = Color.red(img.getPixel(z, x));
		        		 
			        		 if(R == 0 ){
			        			 count++;
			        		 }
		            }
		        	if(count>dense*2){
		        		split[y]=z-1;
		        		//img.setPixel(z-1, ySize, Color.GREEN);
		        	}
		        	else
		        		break;
		    	}
	        	
	        }*/
		    return split;
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
	public int [] refineSplit1 ( Bitmap orignalImage, Bitmap baseLineImages ,int cracterSplit[] , int dense)
    {
    	//bmp.setPixel(result[3], roi[2], Color.BLUE);
    	int orignalImageRoi[] = regionOfIntrest(orignalImage, 0, orignalImage.getWidth() , 0 , orignalImage.getHeight());
    	int baseImageRoi[]    = regionOfIntrest(baseLineImages, 0 , baseLineImages.getWidth(), 0 ,  baseLineImages.getHeight());
    	//Bitmap zar=seg.makeBitmap(bmp, result[15], result[16], roi[2], roi[3]);
   	 	int baseLineHight=baseImageRoi[3]-baseImageRoi[2];boolean pre=false;
   	 	for(int i =1 ; i<cracterSplit[0] ; i+=2)
   	 	{
   	 		if( cracterSplit[i+1]- cracterSplit[i] < dense*3)
	   		 {
			    	 int roi2[] = regionOfIntrest(orignalImage, cracterSplit[i], cracterSplit[i+1], orignalImageRoi[2], orignalImageRoi[3]);
			    	 int cracterHight = roi2[3]-roi2[2];
			    	 if( ( cracterHight ) < baseLineHight+20 )
			    	 {
			    		 int arr [] =  conectedCompunent(orignalImage, cracterSplit[i], cracterSplit[i+1], orignalImageRoi[2], orignalImageRoi[3]);
			    		 arr        =  resetlbl(arr);
				    	 if(arr[0]<3) {
				    		 int distBet2Caracter= cracterSplit[i+2]- cracterSplit[i];
				    		 //text.append(" indexi  "+i+"  d " + dist +" h "+hDist + " ");
				    		 if(distBet2Caracter > (cracterHight)) {
				    			 int sPoint = refineSecSplit(orignalImage, ( cracterSplit[i+1] + ( dense * 2) ) , ( cracterSplit[i+2]-3), orignalImageRoi[2], orignalImageRoi[3], dense);
				    			 cracterSplit [i+1] = sPoint;//result[i]+(hDist);
				    			 //bmp.setPixel(result[i+1], roi[2]-2, Color.MAGENTA);
				    			 pre=false;
				    		 }
				    		 else{
				    			 if(pre==true){
				    				 cracterSplit [i-1]=0;
				    				 cracterSplit [i]=0;
					    			// bmp.setPixel(result[i+1], roi[2]-2, Color.MAGENTA);
					    			 pre=false;
				    			 }
				    			 else
				    				 pre=true;
				    			 }
				    		 }
				    	 else
				    		 pre=false;
			    	 }
			    	 else {
			    		// text.append(" indexo  "+i+"  d " + bHight +" h "+hDist + " ");
			    		 pre=false;
			    		 if(( cracterSplit[i+2]- cracterSplit[i+1])>(dense*3)){
			    			 int sPoint = refineSecSplit(orignalImage, ( cracterSplit[i+1] + ( dense * 2) ), ( cracterSplit[i+2]-3), orignalImageRoi[2], orignalImageRoi[3], dense);
			    			 cracterSplit[i+1]=sPoint;
			    		 }
			    		 if(( cracterSplit[i+2]==0 && (orignalImageRoi[1] - cracterSplit[i+1]) > (dense*2)+2)){
			    			 int sPoint = refineSecSplit(orignalImage, ( cracterSplit[i+1] + ( dense * 2) ), (orignalImageRoi[1]-3), orignalImageRoi[2] , orignalImageRoi[3], dense);
			    			 cracterSplit[i+1]=sPoint;
			    		 }
			    		// bmp.setPixel(result[i+1], roi[2]-2, Color.RED);
			    	 }
	   		 }
   	 
   	 	}
   	 int inc=0;
	 	int newCracterSplit[] = new int [cracterSplit[0]];
	 	for(int i =1 ; i<cracterSplit[0] ; i++){
	   		 if(cracterSplit[i]!=0)
	   		 {
	   			 inc++;
	   			 newCracterSplit[inc]= cracterSplit[i];
	   		 }
		
	 	}
	 	newCracterSplit[0] = inc+1;
	 	newCracterSplit=refineFirstSplit (orignalImage , newCracterSplit, dense, orignalImageRoi[2], orignalImageRoi[3]) ;
	 	return newCracterSplit;

   	 	//return newCracterSplit;
    }
	public int [] refineFirstSplit(Bitmap img,int chrSplit []  ,int dence , int y1 , int y2)
	{
		 int R=0 ;
		    int count=0 ;
		    for(int i = 3 ; i < chrSplit[0] ; i += 2){
		        for (int x = chrSplit[i-1]; x < chrSplit[i] ; x++ ) {
		        	count=0;
		        	for (int y = y1; y < y2; y++) {
		        		 R  = Color.red( img .getPixel(x, y));
		        		 if(R == 0 ){
		        			 count++;
		        		 }
		            }
		        	if(count > ( dence * 2 )){
		        		chrSplit[i] = x -2 ;
		        		//bestSplitPoint = x-2;
		        		break;
		        	}
		        }
		    }
		return chrSplit  ;
	}
	public int refineSecSplit( Bitmap img,int x1 , int x2 , int y1 , int y2 , int dense )
    {
    	int picw = img.getWidth();	int pich = img.getHeight();
    	//int[] pix = new int[picw * pich];
	  //  img.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    int R=0 ;
	    int count=0 , bestSplitPoint = x2;
        for (int x = x2; x > x1; x-- ) {
        	count=0;
        	for (int y = y1; y < y2; y++) {
        		 R  = Color.red( img .getPixel(x, y));
        		 if(R == 0 ){
        			 count++;
        		 }
            }
        	if(count > ( dense * 2 )){
        		bestSplitPoint = x+3;
        		break;
        	}
        }
    	return bestSplitPoint;
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
	public Bitmap[]  handwrittenSegmentation(Bitmap bmp)
	{
		//int picw           = bmp.getWidth(),pich=bmp.getHeight();
		int roi[]            = regionOfIntrest(bmp);
		Bitmap basLineImage  = getCropedRegion(bmp,  roi[0], roi[1] , roi[2] , roi[3]);
		basLineImage         = getBaseline(basLineImage);
		int bRoi[]           = regionOfIntrest(basLineImage);
		int dense            = thickness(bmp , roi[0] ,roi[1] ,roi [2] , roi [3]);
    	int split[]          = handWritenSegment( bmp , roi[0] ,roi[1] , ( roi[2] + ( bRoi[2])-2  ) , roi [3] ,dense);
    	int []result         = refineSplit1( bmp, basLineImage , split, dense);
    	for(int i =1 ; i < result[0]-2 ; i+=2)
    	{
			for(int y = bRoi[2] ; y < bRoi[3] ; y++)
			{
				for(int x = result[i+1] ; x < result[i+2] ;x+=1 )
				{
					bmp.setPixel(  x , roi[2] + y , Color.WHITE);
				}
			}
      	}
    	for(int y = bRoi[2] ; y < bRoi[3] ; y++)
		{
			for(int x = result[(result[0]-1)] ; x < roi[1] ; x += 1 )
			{
				bmp.setPixel(  x , roi[2] + y , Color.WHITE);
			}
			
		}
    	bmp                 =  NoiseRemover(bmp);
    	Bitmap characters[] =  getCharacters(bmp);
    	characters[0]       =  bmp;
		return characters;
    	//return bmp;
	}
}
