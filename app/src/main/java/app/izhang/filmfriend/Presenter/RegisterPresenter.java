package app.izhang.filmfriend.Presenter;

import app.izhang.filmfriend.Util.FirebaseService;
import app.izhang.filmfriend.View.LoginView;
import app.izhang.filmfriend.View.RegisterView;

/**
 * Created by ivanzhang on 3/10/18.
 */

public class RegisterPresenter {
    private RegisterView mView;

    public RegisterPresenter(RegisterView view){
        this.mView = view;
    }

    public void createAccount(String email, String password){
        FirebaseService fbService = FirebaseService.getInstance();
        fbService.createAccount(email, password, this);
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
