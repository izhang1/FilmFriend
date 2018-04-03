package app.izhang.filmfriend.Presenter;

import app.izhang.filmfriend.Model.Services.FirebaseService;
import app.izhang.filmfriend.View.LoginView;

/**
 * Created by ivanzhang on 3/7/18.
 */

public class LoginPresenter {

    private LoginView mView;

    public LoginPresenter(LoginView view){
        this.mView = view;
    }

    public void loginUser(String email, String password){
       FirebaseService fbService = FirebaseService.getInstance();
       fbService.login(email, password, mView.getApplicationContext(), this);
       mView.showLoadingState(true);
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
