package com.designpattern.mvvm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.designpattern.mvvm.R;
import com.designpattern.mvvm.databinding.ActivityLoginBinding;
import com.designpattern.mvvm.model.Result;
import com.designpattern.mvvm.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    public LoginViewModel viewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        initViews();
    }

    /*
     * Methods
     **/

    private void initViews() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding.bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                viewModel.getLoginResult(email, password);
            }
        });

        viewModel.getData().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if (result.getStatus().equals("0")) {
                    callMainActivity();
                } else {
                    binding.tvError.setText(result.getMessage());
                }
            }
        });
    }

    public void callMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
