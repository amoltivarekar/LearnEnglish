package com.example.amolt.learnenglish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button registerBtn;
    private TextView tvLogin;
    private EditText etEmail,etPass;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        db = new DbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = (Button)findViewById(R.id.btnRegister);
        registerBtn.setOnClickListener(this);

        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(this);

        etEmail=(EditText)findViewById(R.id.emailId);
        etPass=(EditText)findViewById(R.id.password);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnRegister:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:
        }
    }

    public void register()
    {
        String email=etEmail.getText().toString();
        String pass=etPass.getText().toString();

        if(email.isEmpty() && pass.isEmpty())
        {
            displayToast("email/password field empty");
        }
        else
        {
            db.addUser(email,pass);
            displayToast("User Registered");
            //finish();
        }
    }

    private void displayToast( String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG ).show();
    }
}
