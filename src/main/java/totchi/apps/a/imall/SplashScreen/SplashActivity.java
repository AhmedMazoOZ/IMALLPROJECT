package totchi.apps.a.imall.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import totchi.apps.a.imall.ConnectorModel.Requests;
import totchi.apps.a.imall.LoginModule.LoginActivity;
import totchi.apps.a.imall.R;

public class SplashActivity extends AppCompatActivity {
Requests request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String email = preferences.getString("email", "");
                String password = preferences.getString("password", "");
                String token = preferences.getString("TOKEN", "");
                if (token.length() == 0) {
//                    User u = new User();
//                    u.setEmail(email);
//                   u.setPassword(password);
//                   request.Login(u);
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();

                } else {
                   startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, 3000);
    }

}
