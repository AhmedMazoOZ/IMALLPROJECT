package totchi.apps.a.imall.SignUpModul;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import totchi.apps.a.imall.LoginModule.LoginActivity;
import totchi.apps.a.imall.R;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    @BindView(R.id.password_sign_up)
    EditText pass;
    @BindView(R.id.email_sign_up)
    EditText email;
    @BindView(R.id.name_sign_up)
    EditText name;
    @BindView(R.id.phone_sign_up)
    EditText phone;
    @BindView(R.id.con_password_sign_up)
    EditText cfpass;
    @BindView(R.id.hint_pass)
    TextView hintPass;
    @BindView(R.id.hint_email)
    TextView hintMail;
    RadioGroup radiogroup;
    RadioButton male ,female;
    Button SignUp;
    ProgressDialog progressDialog;
    Typeface tf1;
    SignUpImp signUpImp;
    String Uname, Uphone, Uemail, Upass, Ucfpass, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        signUpImp = new SignUpImp(this);
        tf1 = Typeface.createFromAsset(getAssets(), "NeoSansArabic.ttf");
        radiogroup = findViewById(R.id.radioGroup);
        pass = findViewById(R.id.password_sign_up);
        cfpass = findViewById(R.id.con_password_sign_up);
        hintPass = findViewById(R.id.hint_pass);
        email = findViewById(R.id.email_sign_up);
        hintMail = findViewById(R.id.hint_email);
        name = findViewById(R.id.name_sign_up);
        phone = findViewById(R.id.phone_sign_up);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        pass.setTypeface(tf1);
        cfpass.setTypeface(tf1);
        hintPass.setTypeface(tf1);
        email.setTypeface(tf1);
        hintMail.setTypeface(tf1);
        name.setTypeface(tf1);
        phone.setTypeface(tf1);
        SignUp=findViewById(R.id.sign_up_button);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uname = name.getText().toString();
//                Uphone = phone.getText().toString();
//                Uemail = email.getText().toString();
//                Upass = pass.getText().toString();
//                Ucfpass = cfpass.getText().toString();
//                radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//                        switch(checkedId) {
//                            case R.id.male:
//                                gender="male";
//                                break;
//                            case R.id.female:
//                                gender="female";
//                                break;
//                        }
//                    }
//                });
//                signUpImp.validateCredentials(Uname, Uphone, Uemail, Upass, Ucfpass);
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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
    public void check_Mail() {
        email.setError(getString(R.string.empty_mail));
    }

    @Override
    public void check_Password() {
        pass.setError(getString(R.string.empty_password));
    }

    @Override
    public void check_name() {
        name.setError(getString(R.string.empty_name));
    }

    @Override
    public void check_phone() {
        phone.setError(getString(R.string.empty_phone));
    }

    @Override
    public void check_conf_password() {
        cfpass.setError(getString(R.string.empty_cfpass));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.sign_up_button)
    public void sign_up() {

    }

    @OnTouch(R.id.email_sign_up)
    boolean showMailHint() {
        hintMail.setVisibility(View.VISIBLE);
        hintPass.setVisibility(View.GONE);
        return false;
    }

    @OnTouch(R.id.password_sign_up)
    boolean showPassHint() {
        hintPass.setVisibility(View.VISIBLE);
        hintMail.setVisibility(View.GONE);
        return false;
    }

    @OnTouch(R.id.con_password_sign_up)
    boolean hidehints() {
        hintMail.setVisibility(View.GONE);
        hintPass.setVisibility(View.GONE);
        return false;
    }
}
