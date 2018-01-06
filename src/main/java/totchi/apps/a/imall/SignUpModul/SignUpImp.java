package totchi.apps.a.imall.SignUpModul;


import android.os.Handler;
import android.widget.Toast;

import totchi.apps.a.imall.ConnectorModel.Requests;
import totchi.apps.a.imall.ConnectorModel.User;
import totchi.apps.a.imall.R;


public class SignUpImp implements SignUpPresenter {
    SignUpView signUpView;

    SignUpImp(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void validateCredentials(String name, String phone, String email, String password, String confirmation) {
        if (signUpView != null) {
            signUpView.show_Progress_bar();
        }
        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmation.isEmpty()) {
            if (email.contains("@") & email.contains(".")) {
                if (password.equals(confirmation)) {
                    signUpView.show_Progress_bar();
                    User u = new User();
                    u.setPassword(password);
                    u.setEmail(email);
                    u.setName(name);
                    u.setPhone(phone);
                    Requests model = new Requests(signUpView.getContext(), signUpView);
                    model.Register(u);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            signUpView.hide_progress_bar();
                        }
                    }, 5000);
                } else {
                    signUpView.hide_progress_bar();
                    Toast.makeText(signUpView.getContext(), signUpView.getContext().getString(R.string.not_same), Toast.LENGTH_LONG).show();
                }
            } else {
                signUpView.hide_progress_bar();
                Toast.makeText(signUpView.getContext(), signUpView.getContext().getString(R.string.real_email), Toast.LENGTH_LONG).show();
            }
        } else {


            Toast.makeText(signUpView.getContext(), signUpView.getContext().getString(R.string.fillAll), Toast.LENGTH_LONG).show();
            if (name.equals("")) {
                signUpView.check_name();
                signUpView.hide_progress_bar();
            } else if (phone.equals("")) {
                signUpView.check_phone();
                signUpView.hide_progress_bar();
            } else if (email.equals("")) {
                signUpView.check_Mail();
                signUpView.hide_progress_bar();
            } else if (password.equals("")) {
                signUpView.check_Password();
                signUpView.hide_progress_bar();
            } else if (confirmation.equals("")) {
                signUpView.check_conf_password();
                signUpView.hide_progress_bar();
            }
        }
    }

    @Override
    public void onDestroy() {
        signUpView = null;
    }

}
