package com.kkv.killer.tvremotefb;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Remote extends AppCompatActivity {
    private ActionBar toolbar;
    FirebaseAuth auth;
    DatabaseReference userfb;
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,sub,del;
    TextView chno;
    FirebaseUser user;
    int jjj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        toolbar = getSupportActionBar();
        auth = FirebaseAuth.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        userfb= FirebaseDatabase.getInstance().getReference();
        toolbar.setTitle("Remote");
        b0=findViewById(R.id.num0);
        b1=findViewById(R.id.num1);
        b2=findViewById(R.id.num2);
        b3=findViewById(R.id.num3);
        b4=findViewById(R.id.num4);
        b5=findViewById(R.id.num5);
        b6=findViewById(R.id.num6);
        b7=findViewById(R.id.num7);
        b8=findViewById(R.id.num8);
        b9=findViewById(R.id.num9);
        del=findViewById(R.id.del);
        chno=findViewById(R.id.curnum);
        sub=findViewById(R.id.submit);

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        auth.signOut();
                        Intent i=new Intent(Remote.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.num0:
                chno.append(0 + "");
                break;
            case R.id.num1:
                chno.append(1 + "");
                break;
            case R.id.num2:
                chno.append(2 + "");
                break;
            case R.id.num3:
                chno.append(3 + "");
                break;
            case R.id.num4:
                chno.append(4 + "");
                break;
            case R.id.num5:
                chno.append(5 + "");
                break;
            case R.id.num6:
                chno.append(6 + "");
                break;
            case R.id.num7:
                chno.append(7 + "");
                break;
            case R.id.num8:
                chno.append(8 + "");
                break;
            case R.id.num9:
                chno.append(9 + "");
                break;
            case R.id.submit:
                if(!(chno.getText().toString().equals("")||chno.getText().toString().equals("0"))) {
                    Toast.makeText(this, chno.getText(), Toast.LENGTH_SHORT).show();
                    jjj = Integer.parseInt(chno.getText().toString());
                    userfb.child("Users").child(user.getUid()).setValue("" + jjj);
                    chno.setText("");
                }
                break;
            case R.id.del:
                if (chno.getText().equals(null))
                    return;
                jjj = Integer.parseInt(chno.getText().toString());
                jjj=jjj/10;
                chno.setText(""+jjj);
                break;
        }
    }
}
