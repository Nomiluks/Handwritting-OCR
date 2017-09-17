package app.join;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class HandwrittenJoinActivity extends Activity {
	Bitmap bmp,bmp1;
	ImageView img1,img,img2;
	EditText text;
   	conectedCompunent con = new conectedCompunent() ;
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        img1  = (ImageView)findViewById(R.id.imageView1);
        img2  = (ImageView)findViewById(R.id.imageView4);
        img   = (ImageView)findViewById(R.id.imageView2);
        text  = (EditText)findViewById(R.id.editText1);
        bmp = BitmapFactory.decodeResource( getResources(), R.drawable.chk_25 );//Get image from drawable
        bmp = getAdaptiveLocalBinarization(17,14,bmp);//binirization of image
   
        img1.setImageBitmap(bmp);
        int picw = bmp.getWidth(),pich = bmp.getHeight();//getting height and width of an image
    	bmp1 = bmp.copy(Config.ARGB_8888, true);
    	int roi[] = regionOfIntrest(bmp1, 0 , picw,  0 , pich);//getting of interest from image into an array
    	bmp1 = getCropedRegion(bmp1, roi[0], roi[1], roi[2], roi[3]);//returning selected region(cropped) image 
    	bmp1 = getBaseline(bmp1);
    	int bRoi[] = regionOfIntrest(bmp1, 0, bmp1.getWidth(), 0, bmp1.getHeight() ) ;
    	img.setImageBitmap(bmp1);
    	int dense = thickness(bmp , roi[0] ,roi[1] ,roi [2] , roi [3]);
    	int split[] = handWritenSegment( bmp , roi[0] ,roi[1] , ( roi[2] + ( bRoi[2])-2  ) , roi [3] ,dense);
    	int []result = refineSplit1( bmp, bmp1 , split, dense);
    	text.append(" "+result.length +" "+result[0] +"  "+result[(result[0]-1)] + " " + roi [1]);
    	for(int i =1 ; i < result[0]-2 ; i+=2){
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
    	bmp = con.conectedCompunentMain(bmp);
    	Bitmap resul[] =  con.conectedCompunentMain1(bmp);
    }

    public Bitmap refill(Bitmap img , int x1 , int x2 ,int y1 , int y2){
    	int R = 0  , R1 = 0 , R2 = 0 ;
    	for(int x = x1 ; x < x2 ; x++ ){
    		for( int y = y1 ; y < y2 ; y++){
    			 R  = Color.red( img .getPixel(x, y));
    			 if ( R == 255 ){
    				 R1  = Color.red( img .getPixel( x - 1 , y));
    				 R2  = Color.red( img .getPixel( x + 1 , y));
    				 if( R1 == 0 && R2 == 0){
    					 img . setPixel(x, y, Color . BLACK );
    				 }
    			 }
    		}
    	}
    	return img ;
    }
    public struct  handWrittenSegmentation(Bitmap orignalImage , Bitmap baseLineImage)
    {
    	struct s = new struct();
    	int inc = 0;
    	handwrittenSegmentation seg = new handwrittenSegmentation();
    	conectedCompunent con = new conectedCompunent();
    	
    	int roi[] = seg .regionOfIntrest(orignalImage, 0, orignalImage.getWidth() , 0,  orignalImage.getHeight());
    	int dense=thickness(orignalImage , roi[0] ,roi[1] ,roi [2] , roi [3]);
    	Bitmap final_bitmap = getBaseline(baseLineImage);
    	int roi1[] = seg.regionOfIntrest(final_bitmap, 0,final_bitmap.getWidth(),0, final_bitmap.getHeight());

    	int result[] = new int[500];
    	struct cha =con.conectedCompunentMain2(orignalImage);
    	//int z=1;
    	for (int z = 1 ; z< cha .counter ; z++ )
    	{
    		
    		int E_C_Roi[]= seg.regionOfIntrest(cha.bmp [z], 0, cha.bmp[z].getWidth() , 0,  cha .bmp[z].getHeight());
    		roi = seg .regionOfIntrest(orignalImage, 0, orignalImage.getWidth() , 0,  orignalImage.getHeight());
    	{
    		Bitmap bi = getBaseline(cha.bmp[z]);
        	int broi[] = seg.regionOfIntrest( bi , 0, bi.getWidth(),0, bi.getHeight());
        	result = handWritenSegment( cha .bmp[z] , E_C_Roi[0] ,E_C_Roi[1] , ( roi[2] + ( broi[2]) -  roi[2] ) , roi [3] ,dense);
        	result = refineSplit( cha.bmp[z], bi , result, dense);
        	if( result[0] <= 3 ){
        		//orignalImage =cha[z];
        		//finalResult[inc]=cha[z];
        		s.bmp[inc] = cha.bmp[z];
        		inc++;
    			orignalImage = changeColor( orignalImage ,cha.bmp[z], E_C_Roi[0], E_C_Roi[1], E_C_Roi[2], E_C_Roi[3]);
        	}
        	else
        	{ 
        		for(int i =1 ; i<result[0] ; i+=2){
        			for(int j = roi1[2] ; j < roi1[3] ; j++)
        			{
        				orignalImage.setPixel( result[i] , roi[2]+j , Color.WHITE);//(orignalImage, result[i], result[i+1], roi[2], roi[3] );
        				orignalImage.setPixel( result[i+1] , roi[2]+j, Color.WHITE);
        				
        			}
               	}
        		Bitmap temp = getCropedRegion(orignalImage, E_C_Roi[0], E_C_Roi[1], roi[2], roi[3] );
        		struct temp1  = con.conectedCompunentMain2(temp);
        		for(int i= 1; i< temp1.counter ;i++)
        		{
        			s.bmp[inc]  = temp1.bmp [i];
        			inc ++;
        		}
        		orignalImage = changeColor( orignalImage ,cha .bmp [z], E_C_Roi[0] , E_C_Roi[1] , E_C_Roi[2], E_C_Roi[3]);

        	}
        	
    	}
    	}// for end
    	s.counter = inc;
    	return s;
    	//return result[0];
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
    
    public int [] refineSplit ( Bitmap orignalImage, Bitmap baseLineImages ,int cracterSplit[] , int dense)
    {
    	handwrittenSegmentation seg= new handwrittenSegmentation();
    	int orignalImageRoi[] = seg.regionOfIntrest(orignalImage, 0, orignalImage.getWidth() , 0 , orignalImage.getHeight());
    	int baseImageRoi[]=seg.regionOfIntrest(baseLineImages, 0 , baseLineImages.getWidth(), 0 ,  baseLineImages.getHeight());
   	 	int baseLineHight=baseImageRoi[3]-baseImageRoi[2];boolean pre=false, pre1=false;
   	 	for(int i =1 ; i<cracterSplit[0] ; i+=2)
   	 	{
   	 		if( ( cracterSplit[i+1] - cracterSplit[i] ) < (dense*3) )
	   		 {
			    	 int roi2[]=seg.regionOfIntrest( orignalImage, cracterSplit[i], cracterSplit[i+1], orignalImageRoi[2], orignalImageRoi[3]);
			    	 int cracterHight = roi2[3]-roi2[2];
			    	
			    	 if( ( cracterHight ) < baseLineHight  )
			    	 { 
			    		 android.util.Log.i(" m ", "  " +i);
			    		 int arr [] = seg.conectedCompunent(orignalImage, cracterSplit[i], cracterSplit[i+1], orignalImageRoi[2], orignalImageRoi[3]);
			    		  arr =seg.resetlbl(arr);
				    	
			    		  if(arr[0]<3) {
				    		 int distBet2Caracter= cracterSplit[i+2]- cracterSplit[i];
				    		 if(distBet2Caracter > (dense * 3)) {
				    			 android.util.Log.i(" sec ", "  " +i);
				    			 int sPoint = refineSecSplit(orignalImage, ( cracterSplit[i+1] + ( dense * 2) ) , ( cracterSplit[i+2]-3), orignalImageRoi[2], orignalImageRoi[3], dense);
				    			 cracterSplit [i+1] = sPoint;
				    			 pre=false;
				    		 }
				    		 else{
				    			 if(pre==true || pre1 == true){
				    				 cracterSplit [i-1]=0;
				    				 cracterSplit [i]=0;
					    			 pre=false;
				    			 }
				    			 else
				    				 pre=true;
				    			 }
				    		 }
				    	 else
				    		 pre=false;
				    	 pre1=false;
			    	 }
			    	 else {
			    		 if(pre1==true || pre ==true)
			    		 {
			    			 cracterSplit [i-1]=0;
		    				 cracterSplit [i]=0;
			    			 pre1=false;
			    		 }
			    		 else
			    			 pre1=true;
			    		 pre=false;
			    		 if(( cracterSplit[i+2]- cracterSplit[i+1])>(dense*3)){
			    			 int sPoint = refineSecSplit(orignalImage, ( cracterSplit[i+1] + ( dense * 2) ), ( cracterSplit[i+2]-3), orignalImageRoi[2], orignalImageRoi[3], dense);
			    			 cracterSplit[i+1]=sPoint;
			    			 pre1=false;
			    		 }
			    		// else
			    		 if(( cracterSplit[i+2]==0 && (orignalImageRoi[1] - cracterSplit[i+1]) > (dense*2)+2)){
			    			 int sPoint = refineSecSplit(orignalImage, ( cracterSplit[i+1] + ( dense * 2) ), (orignalImageRoi[1]-3), orignalImageRoi[2] , orignalImageRoi[3], dense);
			    			 cracterSplit[i+1]=sPoint;
			    			 pre1=false;
			    		 }
			    		
			    		 
			    		// bmp.setPixel(result[i+1], roi[2]-2, Color.RED);
			    	 }
	   		 }
   	 		else
   	 		{//pre1=false;pre=false;
   	 			
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
   	 	//newCracterSplit=refineFirstSplit (orignalImage , newCracterSplit, dense, orignalImageRoi[2], orignalImageRoi[3]) ;
   	 	return newCracterSplit;
    }
    public int [] refineSplit1 ( Bitmap orignalImage, Bitmap baseLineImages ,int cracterSplit[] , int dense)
    {
    	handwrittenSegmentation seg= new handwrittenSegmentation();
    	//bmp.setPixel(result[3], roi[2], Color.BLUE);
    	int orignalImageRoi[] = seg.regionOfIntrest(orignalImage, 0, orignalImage.getWidth() , 0 , orignalImage.getHeight());
    	int baseImageRoi[]=seg.regionOfIntrest(baseLineImages, 0 , baseLineImages.getWidth(), 0 ,  baseLineImages.getHeight());
    	//Bitmap zar=seg.makeBitmap(bmp, result[15], result[16], roi[2], roi[3]);
   	 	int baseLineHight=baseImageRoi[3]-baseImageRoi[2];boolean pre=false;
   	 	for(int i =1 ; i<cracterSplit[0] ; i+=2)
   	 	{
   	 		if( cracterSplit[i+1]- cracterSplit[i] < dense*3)
	   		 {
			    	 int roi2[]=seg.regionOfIntrest(orignalImage, cracterSplit[i], cracterSplit[i+1], orignalImageRoi[2], orignalImageRoi[3]);
			    	 int cracterHight = roi2[3]-roi2[2];
			    	 if( ( cracterHight ) < baseLineHight+20 )
			    	 {
			    		 int arr [] = seg.conectedCompunent(orignalImage, cracterSplit[i], cracterSplit[i+1], orignalImageRoi[2], orignalImageRoi[3]);
			    		 arr =seg.resetlbl(arr);
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
	    Bitmap temp = Bitmap.createBitmap(cop, x2-x1, y2-y1, Config.ARGB_8888) ;
	    temp = temp .copy(Config.ARGB_8888, true ) ;
	  return (temp );
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
	public int[] segment(Bitmap bmp,int xSize,int picw,int ySize,int pich,int dense)
	{
		 int index=0;
		
		 int R=0,G=0,B=0,counting=1;
		 int []segmentp=new int[500];
		
		 int[] pix = new int[picw * pich];
		   
		    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich);
		  /*  for (int y = xSize; y < picw; y++)
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
		        				 butt=false;
		        				 if(inc>0)
		        				 {
		        					 break;
		        				 }
		        			 }
		        		 }
	        	}//inner for end
	        	if(inc>0)	{
	        		break;
	        	}
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
	        	store[counter]=dense;
	        	counter++;
	        	}
	        	if(counter==50)
	        	{
	        		break;
	        	}
	        }
		*/   /* int dense;
		    if(count>inc)
		    {
		    	dense=inc;
		    }
		    else 
		    {
		    	dense=count;
		    }*/
		    
		    int per=0;
		    int sh=0;
		    per=(pich-ySize)/6;
		    for (int y = xSize+per; y < picw; y=y+per)
	        {
		    	sh=0;
	        	for (int x = ySize; x < pich; x++)
	            {	
	        		 index = x * picw+y;
	        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
	        		 G = (pix[index] >> 8) & 0xff;
	        		 B =  pix[index] & 0xff;
	        		 if(R == 0 && G == 0 && B == 0)
	        		 {
	        			 sh++;
	        		 }//end outer if
	            }//end for
	        	if(sh<dense+2)
	        	{
	        		//bmp.setPixel(y, 10, Color.RED);
	        		if((y-segmentp[counting-1])>per+2)
	        		{
	        			segmentp[counting]=y;
	        		//	.setPixel(y, 5, Color.RED);
	        			counting++;
	        		}
	        		else
	        			segmentp[counting]=y;
	        	}
	        }
		    segmentp[0]=counting;
		    return segmentp;
	}
	
	Bitmap returnSegmentedImage(int []sha,Bitmap final_bitmap)
	{
	    int pix[] = new int[final_bitmap.getWidth()*final_bitmap.getHeight()];
	    final_bitmap.getPixels(pix, 0, final_bitmap.getWidth(), 0, 0, final_bitmap.getWidth(),final_bitmap.getHeight());
	    int index = 0,R=0,G=0,B=0;
	    
	    for(int i=1;i<sha[0];i++)
	 	{
	 		for(int j=0;j<final_bitmap.getHeight();j++)
	 		{
	 			index = j*final_bitmap.getWidth()+sha[i];
	 			R = (pix[index] >> 16) & 0xff; //bitwise shifting
	 			G = (pix[index] >> 8) & 0xff;
	 			B =  pix[index] & 0xff;
	 			//final_bitmap.setPixel(sha[i], j, Color.RED);
	 			if(R==0 && B==0&& G==0)
	 			{
	 				final_bitmap.setPixel(sha[i], j, Color.RED);
	 			}
	 		}
	 	}
	    /*for (int y = 0; y < final_bitmap.getHeight(); y++)
        {
        	for (int x = 0; x < final_bitmap.getWidth(); x++)
            {
        		 index = y*final_bitmap.getWidth() + x;
        		
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R == 204 && G == 204 && B == 204)
        		 {
        			 final_bitmap.setPixel(x, y, Color.WHITE);
        		 }
            }
        }*/
		return final_bitmap;
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
	        		if(inc < (dense *2) && count < (dense * 2)){
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
		    
		    for (int y =1 ; y < split[0]; y+=2){
		    	for(int z=split[y];z>split[y]-5;z--){
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
	        	
	        }
		    return split;
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
}