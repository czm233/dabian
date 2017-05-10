package com.zhbit.hhh;

import android.app.Dialog;
import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Keypad extends Dialog implements OnClickListener {

    private PuzzleView fatherView;
    private Button[] key=new Button[9];


    public Keypad(Context context, PuzzleView father) {
        super(context);
        // TODO Auto-generated constructor stub
        fatherView=father;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTitle("小键盘输入");
        setContentView(R.layout.keypad);
        findButtonId();
        for(int i=0;i<9;i++){
            key[i].setOnClickListener(this);
        }

    }

    private void findButtonId(){
        key[0]=(Button)findViewById(R.id.keypad_1);
        key[1]=(Button)findViewById(R.id.keypad_2);
        key[2]=(Button)findViewById(R.id.keypad_3);
        key[3]=(Button)findViewById(R.id.keypad_4);
        key[4]=(Button)findViewById(R.id.keypad_5);
        key[5]=(Button)findViewById(R.id.keypad_6);
        key[6]=(Button)findViewById(R.id.keypad_7);
        key[7]=(Button)findViewById(R.id.keypad_8);
        key[8]=(Button)findViewById(R.id.keypad_9);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

        switch(arg0.getId()){
            case R.id.keypad_1:
                fatherView.setSelectTile("1");break;
            case R.id.keypad_2:
                fatherView.setSelectTile("2");break;
            case R.id.keypad_3:
                fatherView.setSelectTile("3");break;
            case R.id.keypad_4:
                fatherView.setSelectTile("4");break;
            case R.id.keypad_5:
                fatherView.setSelectTile("5");break;
            case R.id.keypad_6:
                fatherView.setSelectTile("6");break;
            case R.id.keypad_7:
                fatherView.setSelectTile("7");break;
            case R.id.keypad_8:
                fatherView.setSelectTile("8");break;
            case R.id.keypad_9:
                fatherView.setSelectTile("9");break;
            case R.id.keypad_10:
                fatherView.setSelectTile(" ");break;
            default:
                fatherView.setSelectTile(" ");break;
        }
        dismiss();
    }

}
