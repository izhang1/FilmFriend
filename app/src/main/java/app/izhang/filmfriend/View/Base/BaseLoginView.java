package app.izhang.filmfriend.View.Base;


/**
 * Created by ivanzhang on 3/7/18.
 */

public interface BaseLoginView {
    void showLoadingState(boolean visible);

    void requestLogin(String email, String password);

    void registerUser(String username, String email, String password);

    void onSuccess();

    void onFailure();
}
