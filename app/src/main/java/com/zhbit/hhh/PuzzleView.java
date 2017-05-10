package com.zhbit.hhh;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.ImageView;

public class PuzzleView extends View {
    private final Rect selRect=new Rect();
    private Game new_game;
    private int i,j,m=0;
    private float width,height;
    private int selX,selY;
    public String [][]  puzzle = new String[9][9];//棋盘
    private String easy[][] = new String[][]{
            {"1","2","3","4","5","6","7","8","9"},
            {"4","5","6","7","8","9","1","2","3"},
            {"7","8","9","1","2","3","4","5","6"},

            {"2","3","4","5","6","7","8","9","1"},
            {"5","6","7","8","9","1","2","3","4"},
            {"8","9","1","2","3","4","5","6","7"},

            {"3","4","5","6","7","8","9","1","2"},
            {"6","7","8","9","1","2","3","4","5"},
            {"9","1","2","3","4","5","6","7"," "},
    };
    private String medium[][] = new String[][]{
            {"6","5"," "," "," "," "," ","7"," "},
            {" "," "," ","5"," ","6"," "," "," "},
            {" ","1","4"," "," "," "," "," ","5"},
            {" ","7"," ","4","6"," "," "," ","3"},
            {" "," ","2","3","1","4","7"," "," "},
            {" "," "," ","7"," "," ","8"," "," "},
            {"5"," "," "," "," "," ","6","3"," "},
            {" ","9"," ","3"," ","1"," ","8"," "},
            {" "," "," "," "," "," ","6"," "," "},
    };
    private String hard[][] = new String[][]{
            {"5","2","4"," "," ","6"," ","8","9"},
            {" "," "," "," "," "," "," "," "," "},
            {"1"," "," ","5"," "," ","3"," "," "},
            {" ","4"," "," "," "," "," ","2"," "},
            {" "," ","7"," ","1"," "," "," "," "},
            {" "," ","1"," "," "," "," "," ","8"},
            {" ","9"," "," "," ","8"," "," "," "},
            {" "," "," ","6"," "," "," "," "," "},
            {" "," "," "," "," "," "," ","1"," "},
    };
    public PuzzleView(Context context,int diff) {
        // TODO Auto-generated constructor stub
        super(context);
        //取得绘图的上下文环境
        new_game=(Game)context;
        setFocusable(true);//允许键盘事件为true，才会响应该事件
        setFocusableInTouchMode(true);//允许触屏事件为true才会响应相应事件
        for(i=0;i<9;i++)
            for(j=0;j<9;j++)
            {
                switch(diff)
                {
                    case 0:
                        puzzle[i][j] = easy[i][j];
                        break;
                    case 1:
                        puzzle[i][j] = medium[i][j];
                        break;
                    case 2:
                        puzzle[i][j] =hard[i][j];
                        break;
                    case -1:
                        puzzle[i][j] = new_game.continueString.substring(m, m+1);
                        System.out.print(puzzle[i][j]);
                        m++;
                        break;
                }
            }
    }
    /*
     * onSizeChanged在屏幕发生改变的时候调用，在初始化一个屏幕时，在onCreate方法之前调用
     * 通过重写该方法，在其内部获取屏幕的宽度，从而获取游戏中矩形框的宽度和高度
     * */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        width=w/9f;
        height=h/9f;
        getRect(selX,selY,selRect);
    }

    //绘制9*9的格子，利用线条颜色的不同吧81个 格子形成九宫格
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
		/*设置背景图片*/
        //canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundpaint);
        Resources resources = getContext().getResources();
         Drawable btnDrawable = resources.getDrawable(R.drawable.game_bg3);
        this.setBackgroundDrawable(btnDrawable);

        //定义颜色
        Paint dark = new Paint();
        dark.setColor(getResources().getColor(R.color.puzzle_dark));
        dark.setStrokeWidth(10.0f);
        Paint light = new Paint();
        light.setColor(getResources().getColor(R.color.puzzle_light));
        light.setStrokeWidth(4.0f);
        Paint hilight = new Paint();



        //绘制线条
        for(int i=0;i<9;i++){
            canvas.drawLine(0, i*height,getWidth(),i*height,light);

            canvas.drawLine(i*width, 0,i*width,getHeight(),light);

            if(i%3==0){
                canvas.drawLine(0, i*height,getWidth(),i*height,dark);

                canvas.drawLine(i*width, 0,i*width,getHeight(),dark);

            }
        }
        //绘制九宫格内数字
        Paint frontPaint=new Paint();
        frontPaint.setColor(Color.WHITE);
        frontPaint.setTextSize(60);
        for(i=0;i<9;i++){
            for(j=1;j<=9;j++){
                canvas.drawText(puzzle[i][j-1], i*width+(width*0.3f), j*height-(height*0.3f),frontPaint);
            }
        }
        //设置初始的矩形
        Paint selPaint=new Paint();
        selPaint.setColor(getResources().getColor(R.color.puzzle_selected));
        canvas.drawRect(selRect,selPaint);
        //调用方法
    }

    //根据传入的selX和selY重新设置矩形的，长、宽和初始为主
    private void getRect(float selX,float selY,Rect r){
        r.set((int)(selX*width+1), (int)(selY*height+1), (int)(selX*width+width-1), (int)(selY*height+height-1));
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        //检测事件类型
        if(event.getAction()!=MotionEvent.ACTION_DOWN){
            return super.onTouchEvent(event);
        }
        //计算触屏的位置在哪个矩形上
        int x=(int)(event.getX()/width);
        int y=(int)(event.getY()/height);
        selectXY(x,y);
        new_game.showKeyPad();
        return false;
    }
    public void selectXY(int x,int y){
        invalidate(selRect);
        if(y==-1) {selY=8;}
        else if(y==9) {selY=0;}
        else selY=y;

        if(x==-1) {selX=8;
        }else if(x==9){selX=0;
        }else selX=x;
        //更新矩形的长、宽、左上角坐标等属性
        getRect(selX,selY,selRect);
        //重新绘制矩形
        invalidate(selRect);
    }

    public void setSelectTile(String d)
    {
        int row,col;
        int finishflag = 0;
        String info = "";
        //1. 检查同一列是否有相同的数字
        for(row = 0;row<9;row++)
        {
            if(puzzle[selX][row].equals(d))
            {
                info = "同一列有相同的数字";
                break;
            }
        }
        //2.检查同一行是否有相同数字
        for(col = 0;col<9;col++)
        {
            if(puzzle[col][selY].equals(d))
            {
                info = "同一行有相同的数字";
                break;
            }
        }
        //3.检查所在的九宫格是否有相同数字
        int x=selX/3;
        int Y=selY/3;
        for(col = x*3;col<x*3+3;col++)
            for(row = Y*3;row<Y*3+3;row++)
            {
                if(puzzle[col][row].equals(d))
                {
                    info = "所在的九宫格有相同数字";
                    break;
                }
            }
        //4.判断输入的数字是否为0，如果为0则将其重置为空，如果info不为空则显示info内容
        //为空则将该矩形框的puzzle数组的内容重置为d。并重新绘制图形
        if(info.equals(""))
        {
            if(d.equals("0"))
                d ="";
            puzzle[selX][selY] = d;
            invalidate(selRect);
        }
        else
        {
            Toast.makeText(this.new_game.getApplicationContext(), info, Toast.LENGTH_SHORT).show();
        }
        //5.利用双重循环验证puzzle数组内是否存在空值” ”，如果存在将finishflag赋值为1
        for(col=0;col<9;col++)
            for(row=0;row<9;row++)
                if(puzzle[col][row].equals(""))
                {
                    finishflag=1;
                    info="九宫格内有未填的数字";break;
                }
        //6.根据finishflag的值判断游戏是否结束，如果为0则通过，否则继续游戏
        if(finishflag==0)
        {
            info="恭喜您通过游戏!";
        }
        else info="请继续游戏!";
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch(keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                selectXY(selX,selY-1);
                break;
            default:
                return super.onKeyDown(keyCode, event);
        }
        return true;
    }


}
