package com.kevelompent.viewkit;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by Kevin on 1/9/18.
 */

public abstract class Sprite {
    public boolean active;
    protected float height;
    protected float width;
    public Vector2 position;
    protected Vector2 direction;
    public float speed;
    protected Paint paint;
    protected float rotation;

    public Sprite(){
        this.active = true;
        this.position = Vector2.zero;
        this.direction = Vector2.zero;
        this.speed = 0;
    }

    public Sprite(Sprite src){
        this.active = src.active;
        this.height = src.height;
        this.width = src.width;
        this.position = new Vector2(src.position);
        this.direction = new Vector2(src.direction);
        this.speed = speed;
        this.paint = new Paint(src.paint);
    }
    public void move(){
        if(active) position.add(Vector2.multiply(direction, speed));
    }
    public abstract void draw(Canvas canvas);

    public float getHeight(){return height;}
    public float getWidth(){return width;}

    public abstract void setDimen(int width, int height);

    public Vector2 getDirection(){return this.direction;}
    public void setDirection(Vector2 dir){dir.normalize(); this.direction = dir;}
    public void setDirection(float x, float y){this.direction = new Vector2(x, y).normalize();}

    public float getLeft(){return this.position.x;}
    public float getTop(){return this.position.y;}
    public float getRight(){return this.position.x + this.width;}
    public float getBottom(){return this.position.y + this.height;}

    public Vector2 getCenter(){return new Vector2(position.x + (width / 2), position.y + (height / 2));}
    public void setCenter(Vector2 center){position = new Vector2(center.x - (width / 2), center.y - (height / 2));}
    public void setCenter(float x, float y){setCenter(new Vector2(x, y));}

    public RectF getRectF(){
        return new RectF(position.x, position.y, width, height);
    }

    public void setRectF(RectF rect){
        this.position.x = rect.left;
        this.position.y = rect.top;
        this.width = rect.width();
        this.height = rect.height();
    }

    public Paint getPaint(){return paint;}
    public void setPaint(Paint paint){this.paint = paint;}

    public float getRotation(){return this.rotation;}
    public void setRotation(float rotation){this.rotation = rotation;}

    public boolean contains(Vector2 point) {
        return point.x > position.x && point.x < position.x + width &&
                point.y > position.y && point.y < position.y + height;
    }

    public static boolean intersects(Sprite a, Sprite b){
        return a.getLeft() <= b.getRight() && a.getRight() >= b.getLeft() &&
                a.getTop() <= b.getBottom() && a.getBottom() >= b.getTop();
    }

    public boolean intersects(Sprite sprite){
        return Sprite.intersects(this, sprite);
    }

    public float getRadius(){
        return (float) Math.sqrt(((width / 2) * (width / 2)) + ((height / 2) * (height / 2)));
    }

    public static boolean circleIntersects(Vector2 aCenter, float aRad, Vector2 bCenter, float bRad){
        return aCenter.dist(bCenter) <= aRad + bRad;
    }

    public static boolean circleIntersects(Sprite cA, Sprite cB){
        return Sprite.circleIntersects(cA.getCenter(), cA.width, cB.getCenter(), cB.width);
    }


    public void destroy(){
        position = null;
        direction = null;
        active = false;
    }
}
