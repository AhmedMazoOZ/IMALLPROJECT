package totchi.apps.a.imall.ConnectorModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import totchi.apps.a.imall.ForgetPassModule.ForgetPassView;
import totchi.apps.a.imall.ForgetPassModule.ResetPassActivity;
import totchi.apps.a.imall.LoginModule.LoginActivity;
import totchi.apps.a.imall.LoginModule.LoginView;
import totchi.apps.a.imall.HomePageModule.MainActivity;
import totchi.apps.a.imall.R;
import totchi.apps.a.imall.SignUpModul.SignUpActivity;
import totchi.apps.a.imall.SignUpModul.SignUpView;

/**
 * Created by 3zoOz on 29/12/2017.
 */

public class Requests {
    static Context context;
    private String name;
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;
    ProgressBar progress;
    ArrayList<User> arr;
    LoginView loginView;
    SignUpView signUpView;
    ForgetPassView forgetPassView;
    public Requests(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }
    public Requests(Context context, SignUpView signUpView) {
        this.context = context;
        this.signUpView = signUpView;
    }
    public Requests(Context context, ForgetPassView forgetView) {
        this.context = context;
        this.forgetPassView = forgetView;
    }
    public void Login(final User user) {

        final String URL = "http://totchi.net:8080/api/login";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();
        final Parser parser = new Parser();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("Login Service", "Response : " + response);
                String data = parser.ParseRegisterResponse(response);
                if (data != null) {
                    if (parser.isRegisterResponseValid()) {
                        if (loginView != null) {
                            loginView.hide_progress_bar();
                        }
                        prefEditor.clear();
                        prefEditor.putString("TOKEN", data).apply();
                        prefEditor.putString("email", user.getEmail()).apply();
                        prefEditor.putString("password", user.getPassword()).apply();
                        prefEditor.commit();

                        preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        String token = preferences.getString("TOKEN", "");
                        Log.i("token", "     " + token);
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } else {
//                        Toast.makeText(context, data, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("error in response", "Error: " + error.getMessage());
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Login(user);
                    Log.e("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                }
                if (error instanceof TimeoutError) {
                    Log.e("Volley", "TimeoutError");
                    Snackbar.make(((Activity) context).findViewById(R.id.contentL), context.getString(R.string.TimeoutError), Snackbar.LENGTH_LONG).show();
                    Toast.makeText(context, context.getString(R.string.TimeoutError), Toast.LENGTH_LONG).show();
                    Login(user);
                } else if (error instanceof NoConnectionError) {
                    Log.e("Volley", "NoConnectionError");
                    Snackbar.make(((Activity) context).findViewById(R.id.contentL), context.getString(R.string.NoConnectionError), Snackbar.LENGTH_LONG).show();

                    Toast.makeText(context, context.getString(R.string.NoConnectionError), Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Log.e("Volley", "AuthFailureError");
                    String data = parser.ParseRegisterResponse(String.valueOf(error));
//                    if (data != null) {
//                            Toast.makeText(context, data, Toast.LENGTH_LONG).show();
//                    }
                    if (loginView != null) {
                        loginView.hide_progress_bar();
                    }
                    Snackbar.make(((Activity) context).findViewById(R.id.contentL), context.getString(R.string.error), Snackbar.LENGTH_LONG).show();
//                    R.string.error
                    Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_LONG).show();
                    Intent back = new Intent(context, LoginActivity.class);
                    context.startActivity(back);
                } else if (error instanceof ServerError) {
                    Log.e("Volley", "ServerError");
                } else if (error instanceof NetworkError) {
                    Log.e("Volley", "NetworkError");
                    Snackbar.make(((Activity) context).findViewById(R.id.contentL), context.getString(R.string.NetworkError), Snackbar.LENGTH_LONG).show();

                    Toast.makeText(context, context.getString(R.string.NetworkError), Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Log.e("Volley", "ParseError");
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());
                return params;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void Register(final User user) {
        final String URL = "http://totchi.net:8080/api/register";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Parser parser = new Parser();
                String data = parser.ParseRegisterResponse(response);
                if (data.length() != 0) {
                    if (parser.isRegisterResponseValid()) {
                        prefEditor.putString("TOKEN", data).apply();
                        prefEditor.putString("email", user.getEmail()).apply();
                        prefEditor.putString("password", user.getPassword()).apply();
                        prefEditor.putString("name", user.getName()).apply();
                        prefEditor.putString("password", user.getPassword()).apply();
                        signUpView.hide_progress_bar();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    } else {
                        signUpView.hide_progress_bar();
                        Snackbar.make(((Activity) context).findViewById(R.id.contentS), data, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, SignUpActivity.class);
                        context.startActivity(intent);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Snackbar.make(((Activity) context).findViewById(R.id.contentS), context.getString(R.string.status_code), Snackbar.LENGTH_LONG).show();
                    Toast.makeText(context, context.getString(R.string.status_code) + networkResponse.statusCode, Toast.LENGTH_LONG).show();
                }
                if (error instanceof TimeoutError) {
                     Snackbar.make(((Activity) context).findViewById(R.id.contentS), context.getString(R.string.TimeoutError), Snackbar.LENGTH_LONG).show();
                    Toast.makeText(context, context.getString(R.string.TimeoutError), Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                } else if (error instanceof ParseError) {
                } else if (error instanceof NoConnectionError || error instanceof NetworkError) {
                    signUpView.hide_progress_bar();
                    Snackbar.make(((Activity) context).findViewById(R.id.contentS), context.getString(R.string.NoConnectionError), Snackbar.LENGTH_LONG).show();
                    Register(user);
                    Toast.makeText(context, context.getString(R.string.NoConnectionError), Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Snackbar.make(((Activity) context).findViewById(R.id.contentS), context.getString(R.string.AuthFailureError), Snackbar.LENGTH_LONG).show();
                    Toast.makeText(context, context.getString(R.string.AuthFailureError), Toast.LENGTH_LONG).show();
                    signUpView.hide_progress_bar();
                    Register(user);
                } else {
                    signUpView.hide_progress_bar();
                    Register(user);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", user.getName());
                params.put("email", user.getEmail());
                params.put("phone", user.getPhone());
                params.put("password", user.getPassword());
                return params;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void ForgetPass(final User user) {
        final Parser parser = new Parser();
        final String URL = "http://totchi.net:8080/api/forget";
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        prefEditor = preferences.edit();

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("ForgetPass Service", "Response : " + response);

                String data = parser.ParseForgetResponse(response);
                if (data == null) {
                    if (parser.isRegisterResponseValid()) {
                        prefEditor.putString("email", user.getEmail()).apply();
                        forgetPassView.hideProgress();
                        Snackbar.make(((Activity) context).findViewById(R.id.contentF), data, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, ResetPassActivity.class);
                        intent.putExtra("email", user.getEmail());
                        context.startActivity(intent);
                    } else {
                        forgetPassView.hideProgress();
                        //Snackbar.make(((Activity) context).findViewById(R.id.contentF), data, Toast.LENGTH_LONG).show();
                        Toast.makeText(context, data, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(context, SignUpActivity.class);
                        context.startActivity(intent);
                    }
                }
            }
        }, new Response.ErrorListener() {
            int x = 1;

            @Override
            public void onErrorResponse(VolleyError error) {
                forgetPassView.hideProgress();
                Log.e("Registration Service", "Error :Send Token Failed");
                VolleyLog.e("error in response", "Error: " + error.getMessage());
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Log.e("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                    forgetPassView.hideProgress();
                    if (networkResponse.statusCode == 500 && x == 1) {
                        forgetPassView.hideProgress();
                        //Snackbar.make(((Activity) context).findViewById(R.id.contentF), context.getString(R.string.sure), Toast.LENGTH_LONG).show();

                        Toast.makeText(context, context.getString(R.string.sure), Toast.LENGTH_LONG).show();
                        x = 5;
                    } else {
                        forgetPassView.hideProgress();
                        // Snackbar.make(((Activity) context).findViewById(R.id.contentF), context.getString(R.string.NoConnectionError), Toast.LENGTH_LONG).show();
                        Toast.makeText(context, context.getString(R.string.NoConnectionError), Toast.LENGTH_LONG).show();
                    }
                }
                if (error instanceof TimeoutError) {
                    forgetPassView.hideProgress();
                    //Snackbar.make(((Activity) context).findViewById(R.id.contentF), context.getString(R.string.TimeoutError), Toast.LENGTH_LONG).show();

                    //Toast.makeText(context, context.getString(R.string.TimeoutError), Toast.LENGTH_LONG).show();
                    ForgetPass(user);
                } else if (error instanceof NoConnectionError || error instanceof NetworkError) {
                    forgetPassView.hideProgress();
                    //Snackbar.make(((Activity) context).findViewById(R.id.contentF), context.getString(R.string.NoConnectionError), Toast.LENGTH_LONG).show();

                    Toast.makeText(context, context.getString(R.string.NoConnectionError), Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    forgetPassView.hideProgress();
                    //Snackbar.make(((Activity) context).findViewById(R.id.contentF), context.getString(R.string.AuthFailureError), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, context.getString(R.string.AuthFailureError), Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                return params;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void SEND_CODE(final String m, String s, String x) {
        final String URL = "http://totchi.net:8080/api/reset";
        final String mail = m;
        final String code = s;
        final String NewPass = x;

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("ForgetPass Service", "Response : " + response);
                Parser parser = new Parser();
                if (parser.isCodeValid(response)) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    Toast.makeText(context, context.getString(R.string.login_pass), Toast.LENGTH_LONG).show();
                    //Snackbar.make(((Activity) context).findViewById(R.id.contentR), context.getString(R.string.login_pass), Snackbar.LENGTH_LONG).show();
                    context.startActivity(intent);
                } else {
                    //Snackbar.make(((Activity) context).findViewById(R.id.contentR), context.getString(R.string.code), Snackbar.LENGTH_LONG).show();
                    Toast.makeText(context, context.getString(R.string.code), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Registration Service", "Error :Send Token Failed");
                VolleyLog.e("error in response", "Error: " + error.getMessage());
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null) {
                    Log.e("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                }
                if (error instanceof TimeoutError) {
                    Log.e("Volley", "TimeoutError");
                    // Snackbar.make(((Activity) context).findViewById(R.id.contentR), context.getString(R.string.TimeoutError), Snackbar.LENGTH_LONG).show();
                    //  Toast.makeText(context, context.getString(R.string.TimeoutError), Toast.LENGTH_LONG).show();
                    SEND_CODE(mail, code, NewPass);
                } else if (error instanceof NoConnectionError || error instanceof NetworkError) {
                    SEND_CODE(mail, code, NewPass);
                    //Snackbar.make(((Activity) context).findViewById(R.id.contentR), context.getString(R.string.NoConnectionError), Snackbar.LENGTH_LONG).show();

                    Toast.makeText(context, context.getString(R.string.NoConnectionError), Toast.LENGTH_LONG).show();
                } else
                    SEND_CODE(mail, code, NewPass);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", mail);
                params.put("reset", code);
                params.put("password", NewPass);
                return params;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
}
