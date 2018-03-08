package app.izhang.filmfriend.View;


/**
 * Created by ivanzhang on 3/7/18.
 */

public interface BaseLoginView {
    void showLoadingState(boolean visible);

    void requestLogin(String username, String password);

    void onSuccess();

    void onFailure();
}
