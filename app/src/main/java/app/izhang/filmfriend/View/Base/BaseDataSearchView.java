package app.izhang.filmfriend.View.Base;

import java.util.ArrayList;

/**
 * Created by ivanzhang on 3/17/18.
 */

public interface BaseDataSearchView {

    void showLoadingState(boolean visible);

    void searchData();

    void searchDataSuccess(ArrayList searchDataList);

    void searchDataFailure(String failure);
}
