package com.example.amolt.learnenglish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button loginBtn,registerBtn;
    private EditText emailTxt,passwordTxt;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button)findViewById(R.id.btnLogin);
        registerBtn = (Button)findViewById(R.id.btnRegister);

        emailTxt = (EditText)findViewById(R.id.emailId);
        passwordTxt = (EditText)findViewById(R.id.password);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        db=new DbHelper(this);
        session=new Session(this);

        if(session.loggedin())

        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
                break;
            default:
        }
    }

    private void login()
    {
        String email=emailTxt.getText().toString();
        String pass=passwordTxt.getText().toString();

        if(db.getUser(email,pass))
        {
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Wrong Email/password.",Toast.LENGTH_LONG).show();
        }
    }
}

