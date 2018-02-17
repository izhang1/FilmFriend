package app.izhang.filmfriend.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import app.izhang.filmfriend.R;

public class RegisterView extends AppCompatActivity {

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
}
