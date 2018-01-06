package totchi.apps.a.imall.ForgetPassModule;


public interface ForgetPassPresenter {
    void validateCredentials(String mail);

    void send_code(String m, String s, String x);

    void onDestroy();
}
