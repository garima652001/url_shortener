package users.com.cuttly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        YoYo.with(Techniques.Bounce)
                .duration(1500)
                .delay(YoYo.INFINITE)
                .playOn(findViewById(R.id.imageView));
        /*YoYo.with(Techniques.FadeOut)
                .duration(3000)
                .playOn(findViewById(R.id.textView));*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity2.class);
                startActivity(i);
                finish();
            }
        }, 2500);
    }
    }