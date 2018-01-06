package totchi.apps.a.imall.LoginModule;


public interface LoginPresenter {
    void validate(String username, String password);
    void onDestroy();
}
