package totchi.apps.a.imall.ForgetPassModule;


import android.content.Context;

public interface ForgetPassView {
    void showProgress();

    void hideProgress();

    void setEmailError(String s);

    Context getContext();
}
