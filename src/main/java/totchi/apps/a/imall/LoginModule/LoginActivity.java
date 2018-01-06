package totchi.apps.a.imall.LoginModule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import totchi.apps.a.imall.ForgetPassModule.ForgetPassActivity;
import totchi.apps.a.imall.HomePageModule.MainActivity;
import totchi.apps.a.imall.R;
import totchi.apps.a.imall.SignUpModul.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
    Typeface tf1;

    EditText email, pass;

    Button Login, Signup;
    TextView forgetPass;
    TextView Or;
    ProgressDialog progressDialog;
    LoginPresenterImp imp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Login = (Button) findViewById(R.id.log_in_button);
        Signup = (Button) findViewById(R.id.sign_up_button);
        email = (EditText) findViewById(R.id.email_log_in);
        pass = (EditText) findViewById(R.id.password_log_in);
        forgetPass=findViewById(R.id.forgetPassword);
        Or = (TextView) findViewById(R.id.OR);
        imp = new LoginPresenterImp(this);
        tf1 = Typeface.createFromAsset(getAssets(), "NeoSansArabic.ttf");
        Login.setTypeface(tf1);
        Signup.setTypeface(tf1);
        email.setTypeface(tf1);
        pass.setTypeface(tf1);
        Or.setTypeface(tf1);
        forgetPass.setTypeface(tf1);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SignUpActivity.class));
                finish();
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgetPassActivity.class));
            }
        });
    }

    @Override
    public void show_Progress_bar() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    public void hide_progress_bar() {
        progressDialog.dismiss();
    }

    @Override
    public void is_Mail_Empty() {
        email.setError(getString(R.string.empty_mail));
    }

    @Override
    public void is_Password_Empty() {
        pass.setError(getString(R.string.empty_password));
    }

    @Override
    public Context getContext() {
        return this;
    }
/*
    @OnClick(R.id.log_in_button)
    void Login() {
        imp.validate(email.getText().toString().trim(), pass.getText().toString().trim());
    }


    public void openSignUpActivity() {
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }

    @OnClick(R.id.forgetPassword)
    void openForgetPassActivity() {
        startActivity(new Intent(this, ForgetPassActivity.class));
        finish();
    }
  */
}
