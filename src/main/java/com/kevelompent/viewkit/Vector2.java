package com.kevelompent.viewkit;

/**
 * Created by Kevin on 1/9/18.
 */

public class Vector2
{
    public float x;
    public float y;

    public static Vector2 zero = new Vector2(0, 0);

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2(){
        new Vector2(0,0);
    }

    public Vector2(Vector2 src){
        new Vector2(src.x, src.y);
    }

    public Vector2 normalize(){
        if(x == 0 && y == 0) return new Vector2(0, 0);
        float mag = (float) Math.sqrt((x*x) + (y*y));
        this.x /= mag;
        this.y /= mag;
        return this;
    }

    public Vector2 add(Vector2 a){x += a.x; y += a.y; return this;}
    public Vector2 minus(Vector2 a){x -= a.x; y -= a.y; return this;}
    public Vector2 multiply(double mult){x *= mult; y *= mult; return this;}
    public Vector2 divide(double div){x /= div; y /= div; return this;}
    public float dist(Vector2 a){
        return Vector2.dist(this, a);
    }

    public static Vector2 add(Vector2 a, Vector2 b){
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 minus(Vector2 a, Vector2 b){
        return new Vector2(a.x - b.x, a.y - b.y);
    }

    public static Vector2 multiply(Vector2 a, float mult){
        return new Vector2(a.x * mult, a.y * mult);
    }

    public static Vector2 divide(Vector2 a, float div){
        return new Vector2(a.x / div, a.y / div);
    }

    public static float dist(Vector2 a, Vector2 b){
        return (float) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}
