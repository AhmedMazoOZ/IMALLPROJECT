package totchi.apps.a.imall.ForgetPassModule;


import totchi.apps.a.imall.ConnectorModel.Requests;
import totchi.apps.a.imall.ConnectorModel.User;
import totchi.apps.a.imall.R;


public class ForgetPassPresenterIm implements ForgetPassPresenter {
    private ForgetPassView forgetView;
    Requests request;

    public ForgetPassPresenterIm(ForgetPassView forgetView) {
        this.forgetView = forgetView;
    }

    @Override
    public void validateCredentials(final String mail) {

        if (forgetView != null) {
            forgetView.showProgress();
        }

        if (mail.length() == 0) {
            forgetView.setEmailError(forgetView.getContext().getString(R.string.empty_mail));
            forgetView.hideProgress();
        }  else {
            request = new Requests(forgetView.getContext(), forgetView);
            User u1 = new User();
            u1.setEmail(mail);
            request.ForgetPass(u1);
        }
    }

    @Override
    public void send_code(String m, String c, String p) {
        String Mail = m;
        String Code = c;
        String NewPass = p;
        request = new Requests(forgetView.getContext(), forgetView);
        request.SEND_CODE(Mail, Code, NewPass);
    }

    @Override
    public void onDestroy() {
        forgetView = null;
    }

}
