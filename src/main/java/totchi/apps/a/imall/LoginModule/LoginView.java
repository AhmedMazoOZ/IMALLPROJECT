package totchi.apps.a.imall.LoginModule;


import android.content.Context;

public interface LoginView {
    void show_Progress_bar();

    void hide_progress_bar();

    void is_Mail_Empty();

    void is_Password_Empty();

    Context getContext();
}
