package com.designpattern.mvvm.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.designpattern.mvvm.model.Result;
import com.designpattern.mvvm.network.AsyncHttp;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

public class LoginRepository {

    private static LoginRepository repository;
    private MutableLiveData<Result> data;

    public synchronized static LoginRepository getInstance(MutableLiveData<Result> data) {
        if (repository == null) {
            repository = new LoginRepository(data);
        }
        return repository;
    }

    public LoginRepository(MutableLiveData<Result> data) {
        this.data = data;
    }

    public void getLoginResult(final String email, final String password) {

        AsyncHttp.get(AsyncHttp.API_USER_LOGIN, AsyncHttp.paramsUserLogin(email, password), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("@@@","responseString "+responseString);
                Result result = new Result("Error " + statusCode);
                data.setValue(result);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("@@@","responseString "+responseString);
                Result result = new Gson().fromJson(responseString, Result.class);
                if (result != null) {
                    data.setValue(result);
                }
            }
        });
    }

}
