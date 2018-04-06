package app.izhang.filmfriend.Presenter;

import android.content.SharedPreferences;

import app.izhang.filmfriend.Model.SharedPreferenceManager;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.Model.Services.FirebaseService;
import app.izhang.filmfriend.View.RegisterView;

/**
 * Created by ivanzhang on 3/10/18.
 */

public class RegisterPresenter {
    private RegisterView mView;

    public RegisterPresenter(RegisterView view){
        this.mView = view;
    }

    public void createAccount(String email, String password, String username){
        FirebaseService fbService = FirebaseService.getInstance();
        fbService.createAccount(email, password, this);
        mView.showLoadingState(true);
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(mView);
        sharedPreferenceManager.saveUsernameToPref(username);
    }

    public void onResult(boolean success){
        if(success){
            mView.onSuccess();
        }else{
            mView.onFailure();
        }
        mView.showLoadingState(false);
    }
}
