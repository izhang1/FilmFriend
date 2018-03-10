package app.izhang.filmfriend.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import app.izhang.filmfriend.R;
import app.izhang.filmfriend.View.Base.BaseLoginView;

public class RegisterView extends AppCompatActivity implements BaseLoginView {

    // UI references
    private TextView mLoginTextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        mLoginTextBtn = findViewById(R.id.tv_login_textbtn);

        mLoginTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showLoadingState(boolean visible) {

    }

    // Not needed for this
    @Override
    public void requestLogin(String username, String password) {

    }

    @Override
    public void registerUser(String username, String email, String password) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {

    }
}
