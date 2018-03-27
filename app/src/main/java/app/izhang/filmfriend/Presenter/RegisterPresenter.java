package app.izhang.filmfriend.Presenter;

import android.content.SharedPreferences;

import app.izhang.filmfriend.R;
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

    public void createAccount(String email, String password, String username){
        FirebaseService fbService = FirebaseService.getInstance();
        fbService.createAccount(email, password, this);
        mView.showLoadingState(true);
        saveUsernameLocally(username);
    }

    public void onResult(boolean success){
        if(success){
            mView.onSuccess();
        }else{
            mView.onFailure();
        }
        mView.showLoadingState(false);
    }

    public void saveUsernameLocally(String username){
        SharedPreferences sharedPref = mView.getSharedPreferences(mView.getApplicationContext().getString(R.string.pref_file_key), mView.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(mView.getString(R.string.username_pref_key), username);
        editor.commit();
    }
}
