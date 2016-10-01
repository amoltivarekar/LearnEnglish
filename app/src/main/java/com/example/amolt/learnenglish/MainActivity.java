package com.example.amolt.learnenglish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button logoutBtn;
    private Session session;
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] menues = {"GRAMMER","READING","WRITTING","VIDEO","ONVERSATION","VACUBALRY","TIPS","TEST","QUEZ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(this);
        if(!(session.loggedin()))
        {
            logout();
        }
        logoutBtn = (Button)findViewById(R.id.btnLogout);

        logoutBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        listView = (ListView)findViewById(R.id.listViewMenu);
        adapter = new ArrayAdapter<String>(this, R.layout.single_row,R.id.textview,menues);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ItemList());

    }
    class ItemList implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ViewGroup vg = (ViewGroup)view;
            TextView tv = (TextView)vg.findViewById(R.id.textview);
            switch (tv.getText().toString())
            {
                case "GRAMMER":
                    grammer();
                    break;
                case "READING":
                    reading();
                    break;
                case "VIDEO":
                    video();
                    break;
                    default:
            }
        }
    }

    private void logout()
    {
        session.setLoggedin(false);
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    private void grammer()
    {
        startActivity(new Intent(MainActivity.this,GrammerActivity.class));
    }
    private void reading()
    {
        startActivity(new Intent(MainActivity.this,ReadingActivity.class));
    }
    private void video()
    {
        startActivity(new Intent(MainActivity.this,VideoActivity.class));
    }
}