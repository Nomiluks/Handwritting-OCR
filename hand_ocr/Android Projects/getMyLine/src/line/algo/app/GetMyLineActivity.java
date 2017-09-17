package line.algo.app;

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
import android.widget.Toast;

public class GetMyLineActivity extends Activity {
    static int save_x = 0,save_y = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView img = (ImageView) findViewById(R.id.imageView1);
        ImageView img2 = (ImageView) findViewById(R.id.ImageView01);
        EditText textbx = (EditText)findViewById(R.id.editText1);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.line);
        bmp = bmp.copy(Config.ARGB_8888, true);
    	bmp = getAdaptiveLocalBinarization(17, 1, bmp);
        Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Config.ARGB_8888);
        bitmap = imageLines(bmp).copy(Config.ARGB_8888, true);
        img2.setImageBitmap(bmp);
       	
       	int picw = bitmap.getWidth();
        int pich = bitmap.getHeight();
        int[] pix = new int[picw * pich];
        int R =0,G=0,B =0, index = 0;
        bitmap.getPixels(pix, 0, picw, 0, 0, picw, pich);
        boolean set = false;
        for (int y = save_y; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		 index = y * picw + x;
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R== 255 && B==0&& G == 0)
        		 {
        			 bitmap.setPixel(x, y, Color.BLUE);
        			 /*for(int o = x; o < x+10; o++)
        			 {
        				 index = y * picw + o;
                	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
                		 G = (pix[index] >> 8) & 0xff;
                		 B =  pix[index] & 0xff;
                		 if(R== 0 && B==0&& G == 0)
                		 {
                			 bitmap.setPixel(o, y, Color.BLUE);
                			 set = true;
                			 break;
                		 }
        			 }
        			 textbx.append(" "+R+" "+G+" "+B);*/
        		 }
        		 if(set == true)
        		 {
        			 break;
        		 }
            }
        }
       	img.setImageBitmap(bitmap);
    }
    Bitmap imageLines(Bitmap bmp)
    {
        int picw = bmp.getWidth();
        int pich = bmp.getHeight();
        int points[] = regionOfIntrest(bmp, picw, pich);
        bmp = getCropedRegion(bmp, points[0], points[1], points[2], points[3]);
        bmp = bmp.copy(Config.ARGB_8888, true);
        picw = bmp.getWidth();
        pich = bmp.getHeight();
        int[] pix = new int[picw * pich];
        bmp.getPixels(pix, 0, picw, 0, 0, picw, pich);
        int R =0,G=0,B =0, index = 0;
        boolean set = false;
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
       	     		bmp.setPixel(x, y, Color.RED);
       	     		save_x = x;
       	     		save_y = y;
       	     		set = true;
       	     		break;
       	     	}
        	}
        	
        	if(set == true){
        		break;}
        	}
        
        int lower_line_pix  = 0;
        int check = 0;
        for (int y = save_y; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		index = y * picw + x;
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(y<(pich-1) )
        		 {
	        		 lower_line_pix = ((y+1) * picw + x);
	        		 check =  (pix[lower_line_pix] >> 16) & 0xff;
	        		 if(R == 0 && G == 0 && B == 0)
	        		 {
		        		 if((R == 0 && G == 0 && B == 0) && check == 255)
		        		 {
		        			 bmp.setPixel(x, (y+1), Color.RED);
		        		 }
		        		 break;
	        		 }
        		 }
            }
        }
    	return bmp;
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
    public int[] regionOfIntrest(Bitmap bmp ,int picw,int pich)
	{
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
}