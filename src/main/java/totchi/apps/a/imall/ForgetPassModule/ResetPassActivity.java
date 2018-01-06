package totchi.apps.a.imall.ForgetPassModule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import totchi.apps.a.imall.LoginModule.LoginActivity;
import totchi.apps.a.imall.R;

public class ResetPassActivity extends AppCompatActivity implements ForgetPassView{
    ForgetPassPresenterIm forgetPresenterimp;
    ProgressDialog progressDialog;
    EditText codeEditText,PassEditText,confEditText;
    TextView textView,textView2;
    Button submit;
    String email = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        email = getIntent().getStringExtra("email");
        forgetPresenterimp = new ForgetPassPresenterIm(this);
        codeEditText=findViewById(R.id.reset_code);
        PassEditText=findViewById(R.id.reset_newpass);
        confEditText=findViewById(R.id.con_reset_newpass);
        submit=findViewById(R.id.submit_forgetPass_email);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String pass = PassEditText.getText().toString();
//                String conf = confEditText.getText().toString();
//                PassEditText.setError(getString(R.string.empty_password));
//                confEditText.setError(getString(R.string.empty_cfpass));
//                codeEditText.setError(getString(R.string.empty_code));
//                if (pass.equals(conf) && !pass.isEmpty()) {
//                    forgetPresenterimp.send_code(email, codeEditText.getText().toString(), PassEditText.getText().toString());
//                } else {
//                    Snackbar.make(findViewById(R.id.contentR),getString(R.string.not_same),Snackbar.LENGTH_LONG).show();
//
//                    //     Toast.makeText(v.getContext(), getString(R.string.not_same), Toast.LENGTH_LONG).show();
//                }
                  startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void setEmailError(String s) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
