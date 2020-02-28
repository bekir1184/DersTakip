package com.example.dersyoklama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.dersyoklama.Program.Prog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private Fragment Anasayfa;
    private Fragment Program;
    private Fragment Yoklama;
    private View decorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout =(FrameLayout)findViewById(R.id.frameLay);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation_bar);


        Anasayfa = new AnaSayfa();
        Program = new Prog();
        Yoklama = new Yoklama();

        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLay,Anasayfa);
        fragmentTransaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.anaSayfa:
                        setFragment(Anasayfa);
                        return true;

                    case R.id.ders_prog:
                        Prog prog= new Prog();
                        prog.onStart();
                        setFragment(prog);
                        return true;

                    case R.id.yoklama:
                        setFragment(Yoklama);
                        return true;

                    default:return false;
                }


            }



            private void setFragment(Fragment fragment) {
                FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLay,fragment);
                fragmentTransaction.commit();
            }
        });




        decorView =getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                if(i==0){
                    decorView.setSystemUiVisibility(yokEt());

                }
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(yokEt());


        }
    }
    private int  yokEt(){
       return View.SYSTEM_UI_FLAG_LAYOUT_STABLE  |
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}
