package app.izhang.filmfriend.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import app.izhang.filmfriend.R;

public class MovieDetailView extends AppCompatActivity {

    Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_view);

        // TODO: 3/4/18 Change this to the title of the Movie
        mActionBarToolbar = findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Black panther");
        setSupportActionBar(mActionBarToolbar);

    }
}
