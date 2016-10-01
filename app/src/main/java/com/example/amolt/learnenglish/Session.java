package com.example.amolt.learnenglish;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by amolt on 6/28/2016.
 */
public class Session
{
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx)
    {
        this.ctx = ctx;
        prefs=ctx.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor=prefs.edit();
    }

    public void setLoggedin(boolean loggedin)
    {
        editor.putBoolean("LoggedInMode",loggedin);
        editor.commit();
    }

    public boolean loggedin()
    {
        return prefs.getBoolean("LoggedInMode",false);
    }
}
