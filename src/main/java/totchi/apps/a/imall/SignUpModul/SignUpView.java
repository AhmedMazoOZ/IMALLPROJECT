package totchi.apps.a.imall.SignUpModul;


import android.content.Context;

public interface SignUpView {
    void show_Progress_bar();

    void hide_progress_bar();

    void check_Mail();

    void check_Password();

    void check_name();

    void check_phone();

    void check_conf_password();

    Context getContext();
}
