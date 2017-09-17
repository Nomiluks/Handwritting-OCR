package com.and;

import android.app.Activity; 
import android.os.Bundle; 
import android.widget.ImageView; 
import org.opencv.android.Utils; 
import org.opencv.core.CvType;
import org.opencv.core.Mat; 
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc; 
import android.graphics.Bitmap; 
import android.graphics.BitmapFactory; 
public class EdgeDetection extends Activity 
{ 
/** Called when the activity is first created. */ 
 
@Override 
public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); 
	    setContentView(R.layout.main); 
	    ImageView img1 =(ImageView) findViewById(R.id.imageView1);    
	    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.i);
	    img1.setImageBitmap(cannyDetection(bmp));

	    ImageView img2=(ImageView) findViewById(R.id.imageView2);
	}
public Bitmap cannyDetection(Bitmap bmp)
{
	  Mat imgToProcess2 = Utils.bitmapToMat(bmp);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY); 
	    Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1,1,Imgproc.BORDER_DEFAULT);
	    Imgproc.Canny(imgToProcess2, imgToProcess2, 90, 100);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
	    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
	    Utils.matToBitmap(imgToProcess2, bmpOut2);
	    return bmpOut2;
}
public Bitmap laplaceDetection(Bitmap bmp)
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
public Bitmap sobelDetection(Bitmap bmp)
{
	Mat imgToProcess2 = Utils.bitmapToMat(bmp);
    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY); 
    Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1,1,Imgproc.BORDER_DEFAULT);
    Imgproc.Sobel(imgToProcess2, imgToProcess2,CvType.CV_8U ,80, 90);
    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
    Utils.matToBitmap(imgToProcess2, bmpOut2);
    return bmpOut2;
}
} 

