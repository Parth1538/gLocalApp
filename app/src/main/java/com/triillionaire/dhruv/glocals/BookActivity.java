package com.triillionaire.dhruv.glocals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    TextView tname,tfrom,tto,tatime,tdtime,ttype;
    EditText ticket;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        session=new SessionManager(getApplicationContext());
        session.checkLogin();
        tname=(TextView)findViewById(R.id.tvname);
        tfrom=(TextView)findViewById(R.id.tvtfrom);
        tto=(TextView)findViewById(R.id.tvtto);
        tatime=(TextView)findViewById(R.id.tvtatime);
        tdtime=(TextView)findViewById(R.id.tvtdtime);
        ttype=(TextView)findViewById(R.id.tvttype);

        tname.setText(getIntent().getStringExtra("tname"));
        tfrom.setText(getIntent().getStringExtra("tfrom"));
        tto.setText(getIntent().getStringExtra("tto"));
        tatime.setText(getIntent().getStringExtra("tatime"));
        tdtime.setText(getIntent().getStringExtra("tdtime"));
        ttype.setText(getIntent().getStringExtra("ttype"));


        ticket=(EditText)findViewById(R.id.etticket);

        Button btn_booking=(Button)findViewById(R.id.btn_book);
        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String train_name = tname.getText().toString();
                String source = tfrom.getText().toString();
                String destination = tto.getText().toString();
                String t_type = ttype.getText().toString();
                String no_of_ticket = ticket.getText().toString();



                BackgroundWorker bgworker = new BackgroundWorker();
                bgworker.execute("bookticket",train_name, source, destination, t_type, no_of_ticket);

            }
        });




    }
}
