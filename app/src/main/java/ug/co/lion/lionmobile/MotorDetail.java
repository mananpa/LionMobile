package ug.co.lion.lionmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MotorDetail extends AppCompatActivity {

    TextView details;
    Button b_get_quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        details = (TextView) findViewById(R.id.details);
        b_get_quote = (Button) findViewById(R.id.b_get_quote);

        details.setMovementMethod(LinkMovementMethod.getInstance());
        details.setText(Html.fromHtml(getString(R.string.motor_comprehensive_cover_details)));

        b_get_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MotorDetail.this, GetQuote.class);
                startActivity(i);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
