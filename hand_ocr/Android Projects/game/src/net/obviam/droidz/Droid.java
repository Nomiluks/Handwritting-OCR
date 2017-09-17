package net.obviam.droidz;

//package net.obviam.droidz.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Droid {

 private Bitmap bitmap; // the actual bitmap
 private Bitmap bitmap1;
 private int x,x1;   // the X coordinate
 private int y,y1;   // the Y coordinate
 private boolean touched; // if droid is touched/picked up

 public Droid(Bitmap bitmap, int x, int y) {
  this.bitmap = bitmap;
  this.x = x;
  this.y = y;
 }

 public Bitmap getBitmap() {
  return bitmap;
 }
 public void setBitmap(Bitmap bitmap) {
  this.bitmap = bitmap;
 }
 
 public void setBitmap(Bitmap bitmap, Bitmap bitmap1) {
	  this.bitmap = bitmap;
	  this.bitmap1 = bitmap1;
	 }
 public void setBitmap(Bitmap bitmap, int x, int y) {
	  this.bitmap = bitmap;
	  this.x = x;
	  this.y = y;
	 }
 
 public void setBitmap1(Bitmap bitmap, int x1, int y1) {
	  this.bitmap1 = bitmap;
	  this.x1 = x1;
	  this.y1 = y1;
	 }
 
 public int getX() {
  return x;
 }
 public void setX(int x) {
  this.x = x;
 }
 public int getY() {
  return y;
 }
 public void setY(int y) {
  this.y = y;
 }

 public boolean isTouched() {
  return touched;
 }

 public void setTouched(boolean touched) {
  this.touched = touched;
 }

 public void draw(Canvas canvas) {
	 
	 canvas.drawBitmap(bitmap1, x1 , y1 , null);
	 canvas.drawBitmap(bitmap, x - (bitmap.getWidth() ), y - (bitmap.getHeight() ), null);
	 	  
 }
 
 public void clean()
 {
	 this.bitmap1.recycle();
	 this.bitmap.recycle();
	 
	 
 }

 public void handleActionDown(int eventX, int eventY) {
  if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
   if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
    // droid touched
    setTouched(true);
   } else {
    setTouched(false);
   }
  } else {
   setTouched(false);
  }

 }
}
