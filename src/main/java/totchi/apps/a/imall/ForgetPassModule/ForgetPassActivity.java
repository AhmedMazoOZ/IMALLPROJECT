package totchi.apps.a.imall.ForgetPassModule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import totchi.apps.a.imall.R;

public class ForgetPassActivity extends AppCompatActivity implements ForgetPassView{
    ProgressDialog progressDialog;
    EditText editText;
    Button send;
    Context context;
    Typeface tf1;
    private ForgetPassPresenterIm forgetPassPresenterIm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        tf1 = Typeface.createFromAsset(getAssets(), "NeoSansArabic.ttf");
        editText=findViewById(R.id.forget_pass_email);
        send=findViewById(R.id.submit_forgetPass_email);
        editText.setTypeface(tf1);
        send.setTypeface(tf1);
        this.context = this;
        forgetPassPresenterIm = new ForgetPassPresenterIm(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String mail = editText.getText().toString();
//                showProgress();
//                if (mail.length() == 0) {
//                    editText.setError(getString(R.string.empty_mail));
//                    hideProgress();
//                } else if (!mail.contains("@") && !mail.contains(".")) {
//                    //  Toast.makeText(this, getString(R.string.real_email), Toast.LENGTH_LONG).show();
//                    Snackbar.make(findViewById(R.id.contentF),context.getString(R.string.real_email),Snackbar.LENGTH_LONG).show();
//                    hideProgress();
//                } else {
//                    forgetPassPresenterIm.validateCredentials(editText.getText().toString());
//                    //  showProgress();
//                }
                startActivity(new Intent(getApplicationContext(), ResetPassActivity.class));
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
        editText.setError(getString(R.string.empty_mail));
    }

    @Override
    public Context getContext() {
        return this;
    }

}


