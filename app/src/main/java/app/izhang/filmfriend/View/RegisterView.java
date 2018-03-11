package app.izhang.filmfriend.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import app.izhang.filmfriend.Presenter.RegisterPresenter;
import app.izhang.filmfriend.R;
import app.izhang.filmfriend.View.Base.BaseLoginView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterView extends AppCompatActivity implements BaseLoginView {

    // UI references
    @BindView(R.id.tv_login_textbtn) TextView mLoginTextBtn;
    @BindView(R.id.btn_register) Button mRegisterBtn;
    @BindView(R.id.login_progress) ProgressBar mProgressBar;
    @BindView(R.id.email) AutoCompleteTextView mEmailTV;
    @BindView(R.id.username) EditText mUsernameET;
    @BindView(R.id.password) EditText mPasswordET;

    // Presenter Reference
    RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        ButterKnife.bind(this);

        mRegisterPresenter = new RegisterPresenter(this);

        mLoginTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                startActivity(intent);
                finish();
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(mUsernameET.getText().toString(),
                        mEmailTV.getText().toString(),
                        mPasswordET.getText().toString());
            }
        });
    }

    @Override
    public void showLoadingState(boolean visible) {
        if(visible) mProgressBar.setVisibility(View.VISIBLE);
        else mProgressBar.setVisibility(View.INVISIBLE);
    }

    // Not needed for this
    @Override
    public void requestLogin(String username, String password) {

    }

    @Override
    public void registerUser(String username, String email, String password) {
        mRegisterPresenter.createAccount(email, password, username);
    }

    @Override
    public void onSuccess() {
        // TODO: 3/11/18 Change this to finish instead of launching the new intent
        // finish();
        Intent homeViewIntent = new Intent(getApplicationContext(), HomeView.class);
        startActivity(homeViewIntent);
        finish();
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "Could not register your account, please try again", Toast.LENGTH_LONG).show();
    }
}
