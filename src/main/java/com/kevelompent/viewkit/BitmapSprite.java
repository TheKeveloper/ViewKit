package com.kevelompent.viewkit;

/**
 * Created by Kevin on 1/9/18.
 */

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

public class BitmapSprite extends Sprite{
    private Bitmap bitmap;

    public BitmapSprite(BitmapSprite src){
        super(src);
        this.bitmap = src.bitmap;
    }

    public BitmapSprite(Bitmap bitmap, float width, float height, Vector2 position, int color){
        setSizedBitmap(bitmap, width, height);
        this.position = position;
        this.paint = new Paint(color);
        this.paint.setColorFilter(new LightingColorFilter(color, 1));
        active = true;
    }
    public BitmapSprite(Resources res, int bitmapSource, float width, float height, Vector2 position,
                        int color){
        new BitmapSprite(BitmapFactory.decodeResource(res, bitmapSource), width, height, position, color);
    }

    public BitmapSprite(Bitmap bitmap, Vector2 position){
        this.bitmap = bitmap;
        this.position = position;
        this.paint = new Paint();
        active = true;
    }

    public BitmapSprite(Resources Res, int BitmapSource, Vector2 position){
        new BitmapSprite(BitmapFactory.decodeResource(Res, BitmapSource), position);
    }

    public BitmapSprite(Bitmap bitmap, float width, float height, Vector2 position){
        setSizedBitmap(bitmap, width, height);
        this.position = position;
        this.paint = new Paint();
        active = true;
    }
    public BitmapSprite(Resources Res, int BitmapSource, float width, float height, Vector2 position){
        new BitmapSprite(BitmapFactory.decodeResource(Res, BitmapSource), width, height, position);
    }

    public void draw(Canvas canvas){
        if(active){
            if(rotation != 0){
                canvas.save();
                canvas.rotate(rotation);
            }
            canvas.drawBitmap(bitmap, position.x, position.y, paint);
            if(rotation != 0){
                canvas.restore();
            }
        }
    }

    //Get/set modifiers
    private void setSizedBitmap(Bitmap bitmap, float width, float height){
        this.bitmap = Bitmap.createScaledBitmap(bitmap,(int)width, (int) height, false);
        this.height = (int)height;
        this.width = (int)width;
    }
    public void setDimen(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.bitmap = Bitmap.createScaledBitmap(this.bitmap, width, height, false);
    }

    public boolean circleIntersecs(BitmapSprite cB){
        return BitmapSprite.circleIntersects(this, cB);
    }

    public boolean circleRectIntersects(BitmapSprite rect){
        return BitmapSprite.circleRectIntersects(this, rect);
    }

    public boolean circleRectIntersects(BitmapSprite rect, double rectRad) {
        return BitmapSprite.circleRectIntersects(this, rect, rectRad);
    }

    public static boolean circleRectIntersects(BitmapSprite circle, BitmapSprite rect){
        //Checks if circle intersects rect rectangally
        if(circle.intersects(rect)){
            //Gets diagonal length of the rectangle
            //Math.sqrt is computationally expensive, try to find better way
            double rectRad = Math.sqrt((rect.width * rect.width) + (rect.height * rect.height));

            return BitmapSprite.circleIntersects(circle.getCenter(), circle.width,
                    rect.getCenter(), (float) rectRad);
        }
        return false;
    }

    public static boolean circleRectIntersects(BitmapSprite circle, BitmapSprite rect, double rectRad){
        return circle.intersects(rect) && BitmapSprite.circleIntersects(circle.getCenter(), circle.width / 2,
                rect.getCenter(), (float) rectRad);
    }

    @Override
    public void destroy(){
        super.destroy();
        bitmap = null;
    }
}
