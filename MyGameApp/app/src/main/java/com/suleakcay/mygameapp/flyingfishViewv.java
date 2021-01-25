package com.suleakcay.mygameapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class flyingfishViewv extends View

{
    private Bitmap fish[]=new Bitmap[2];
    private int fishX=10;
    private int fishY;
    private  int fishSpeed;
    private int canvasWidth,canvasheight;
    private  int yellowX,yellowY,yellowSpeed=16;
    private Paint yellowPaint=new Paint();
    private int greenX,greenY,greenSpeed=20;
    private  int redX,redY,redSpeed=22;
    private  Paint greenPaint=new Paint();
    private Paint redPaint=new Paint();

    private int  score;

    private Bitmap backgroundImage;
    private Paint scorePaint=new Paint();
    private Bitmap life[]=new Bitmap[2];

    private  boolean touch=false;

    public flyingfishViewv(Context context) {
        super(context);
        fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish);
        fish[0]=Bitmap.createScaledBitmap(fish[0],80,80,true);
        fish[1]=BitmapFactory.decodeResource(getResources(),R.drawable.fish1);

        backgroundImage=BitmapFactory.decodeResource(getResources(),R.drawable.splash2);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);



        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.heart);
       life[0]=Bitmap.createScaledBitmap(life[0],40, 40,true);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.grey_heart);
        life[1]=Bitmap.createScaledBitmap(life[1],40,40,true);

        fishY=550;

        score=0;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth=canvas.getWidth();
        canvasheight=canvas.getHeight();




        canvas.drawBitmap(backgroundImage,0,0,null);
        int minFishY=fish[0].getHeight();
        int maxFishY=canvasheight-fish[0].getHeight()*2;
        fishY=fishY+fishSpeed;
        if(fishY<minFishY){
            fishY=minFishY;

        }
        if(fishY>maxFishY)
        {
            fishY=maxFishY;

        }
        fishSpeed =fishSpeed+2;
        if(touch){
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch =false;

        }else {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }

        yellowX=yellowX-yellowSpeed;
        if(hitBallChecker(yellowX,yellowY)){
            score=score+5;
            yellowX=-100;
        }
        if(yellowX<0){
            yellowX=canvasWidth+21;
            yellowY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;

        }
        canvas.drawCircle(yellowX,yellowY,20,yellowPaint);

        greenX=greenX-greenSpeed;
        if(hitBallChecker(greenX,greenY)){
            score=score+20;
            greenX=-100;
        }
        if(greenX<0){
            greenX=canvasWidth+21;
            greenY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;

        }
        canvas.drawCircle(greenX,greenY,20,greenPaint);

        redX=redX-redSpeed;
        if(hitBallChecker(redX,redY)){
            score=score+10;
            redX=-100;
        }
        if(redX<0){
            redX=canvasWidth+21;
            redY=(int)Math.floor(Math.random()*(maxFishY-minFishY))+minFishY;

        }
        canvas.drawCircle(redX,redY,20,redPaint);



        canvas.drawText("Skor :"+score,20, 60,scorePaint);

        canvas.drawBitmap(life[0],500,10,null);
        canvas.drawBitmap(life[0],600,10,null);
        canvas.drawBitmap(life[0],700,10,null);
    }

    public boolean hitBallChecker(int x,int y){
        if(fishX<x&& x<(fishX+fish[0].getWidth())&& fishY<y && y<fishY+fish[0].getHeight()){
            return  true;
        }
            return false;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
      if(event.getAction()==MotionEvent.ACTION_DOWN){
          touch=true;
          fishSpeed =-33;

      }
      return true;
    }
}
