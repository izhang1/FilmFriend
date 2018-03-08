package app.izhang.filmfriend.Presenter;

import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import app.izhang.filmfriend.Util.FirebaseService;
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
