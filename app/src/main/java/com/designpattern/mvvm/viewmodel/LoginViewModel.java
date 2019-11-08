package com.designpattern.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.designpattern.mvvm.model.Result;
import com.designpattern.mvvm.repository.LoginRepository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Result> data;
    private LoginRepository repository;

    public LoginViewModel() {
        data = new MutableLiveData<>();
        repository = LoginRepository.getInstance(data);
    }

    public MutableLiveData<Result> getData() {
        return data;
    }

    public void getLoginResult(String email, String password){
        repository.getLoginResult(email,password);
    }
}
