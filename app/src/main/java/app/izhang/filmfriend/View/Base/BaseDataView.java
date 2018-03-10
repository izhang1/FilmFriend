package app.izhang.filmfriend.View.Base;

/**
 * Created by ivanzhang on 3/10/18.
 */

public interface BaseDataView {
    void showLoadingState(boolean visible);

    void getData();

    void getDataSuccess();

    void getDataFailure();
}
