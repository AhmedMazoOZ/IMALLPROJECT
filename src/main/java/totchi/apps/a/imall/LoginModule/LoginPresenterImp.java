package totchi.apps.a.imall.LoginModule;


import android.widget.Toast;


import totchi.apps.a.imall.ConnectorModel.Requests;
import totchi.apps.a.imall.ConnectorModel.User;
import totchi.apps.a.imall.R;


public class LoginPresenterImp implements LoginPresenter {

    LoginView loginView;

    LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void validate(final String email, final String password) {
        if (loginView != null) {
            loginView.show_Progress_bar();
        }
        if (!email.isEmpty() && !password.isEmpty()) {
            if (email.contains("@") && email.contains(".")) {
                loginView.show_Progress_bar();
                Requests request = new Requests(loginView.getContext(), loginView);
                User u = new User();
                u.setEmail(email);
                u.setPassword(password);
                request.Login(u);

            } else {
                loginView.hide_progress_bar();
                Toast.makeText(loginView.getContext(), loginView.getContext().getString(R.string.real_email), Toast.LENGTH_LONG).show();
            }
        } else if (email.isEmpty() && password.isEmpty()) {
            loginView.hide_progress_bar();
            Toast.makeText(loginView.getContext(), loginView.getContext().getString(R.string.empty_mail_or_password), Toast.LENGTH_LONG).show();
        } else if (email.isEmpty()) {
            loginView.hide_progress_bar();
            loginView.is_Mail_Empty();
        } else if (password.isEmpty()) {
            loginView.hide_progress_bar();
            loginView.is_Password_Empty();
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }
}
