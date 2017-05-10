package com.zhbit.hhh;
import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.media.SoundPool;
import android.media.AudioManager;
public class MainActivity extends Activity implements OnClickListener{
    private Button beginGame,aboutButton,btnexit,continueGame;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//显示主界面

        beginGame=(Button)findViewById(R.id.beginButton);//四个按钮
        aboutButton=(Button)findViewById(R.id.aboutButton);
        continueGame=(Button)findViewById(R.id.continueButton);
        btnexit=(Button) findViewById(R.id.exitButton);

        beginGame.setOnClickListener((OnClickListener) this);//按钮绑定事件
        aboutButton.setOnClickListener((OnClickListener) this);
        continueGame.setOnClickListener((OnClickListener) this);
        btnexit.setOnClickListener(this);

    }
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch(arg0.getId()){
            case R.id.beginButton:
                openNewGame();
                break;
            case R.id.aboutButton:
                new AlertDialog.Builder(this).setTitle("About Game")
                        .setMessage("\t数独盘面是个九宫，每一宫又分为九个小格。" +
                                    "在这八十一格中给出一定的已知数字和解题条" +
                                    "件，利用逻辑和推理，在其他的空格上填入1-9" +
                                    "的数字。" +
                                    "\n\t玩家需要根据9×9盘面上的已知数字，推理出所" +
                                    "有剩余空格的数字，并满足每一行、每一列、每一" +
                                    "个粗线宫内的数字均含1-9，不重复。" )
                        .setPositiveButton("确定"  ,  null ).show();
                break;
            case R.id.continueButton:
                startOldGame(-1);
                break;
            case R.id.exitButton:
                exitAlert("真的要退出吗？");
                break;
        }
    }
    private void startOldGame(int diff){
        Intent intent=new Intent(MainActivity.this,Game.class);
        intent.putExtra("difficulty", diff);
        startActivity(intent);
    }
    private void openNewGame(){
        AlertDialog.Builder new_game=new AlertDialog.Builder(this);
        final String ss[]={"容易","中等","困难"};
        new_game.setTitle("选择难度");
        new_game.setItems(ss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, ss[which], Toast.LENGTH_SHORT).show();
                startOldGame(which);
            }
        });
        AlertDialog alert=new_game.create();
        alert.show();
    }
    // 显示对话框
    private void exitAlert(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                return;
            }
        }).show();
//        AlertDialog alert = builder.create();
//        alert.show();
    }
    /*从xml定义的菜单资源中生成一个菜单*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }


}