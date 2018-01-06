package totchi.apps.a.imall.SignUpModul;


public interface SignUpPresenter {
    void validateCredentials(String name, String email, String phone, String password, String confirmation);

    void onDestroy();

}
