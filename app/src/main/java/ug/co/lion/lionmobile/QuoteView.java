package ug.co.lion.lionmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import net.i2p.android.ext.floatingactionbutton.FloatingActionButton;
import net.i2p.android.ext.floatingactionbutton.FloatingActionsMenu;


public class QuoteView extends AppCompatActivity {

    Intent i;
    MotorCompInsuranceQuoteItem mCI;
    TextView tv_title, tv_sum_insured, tv_basic_premium, tv_training_levy, tv_sticker_fees, tv_VAT, tv_stamp_duty, tv_total_premium;
    FloatingActionsMenu fab;
    FloatingActionButton fabCallLion, fabSendEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_title = (TextView) findViewById(R.id.tv_quote_view_pdt_title);
        tv_sum_insured = (TextView) findViewById(R.id.tv_quote_view_amt_insured);
        tv_basic_premium = (TextView) findViewById(R.id.tv_quote_view_basic_premium);
        tv_training_levy = (TextView) findViewById(R.id.tv_quote_view_training_levy);
        tv_sticker_fees = (TextView) findViewById(R.id.tv_quote_view_sticker_fees);
        tv_VAT = (TextView) findViewById(R.id.tv_quote_view_VAT);
        tv_stamp_duty = (TextView) findViewById(R.id.tv_quote_view_stamp_duty);
        tv_total_premium = (TextView) findViewById(R.id.tv_quote_view_total_premium);
        fab = (FloatingActionsMenu) findViewById(R.id.fam_fab);
        fabCallLion = (FloatingActionButton) findViewById(R.id.fab_call_lion);
        fabSendEmail = (FloatingActionButton) findViewById(R.id.fab_send_email);

        i = getIntent();
        String valueString = i.getStringExtra("value");

        mCI = new MotorCompInsuranceQuoteItem();
        mCI.setUpAll(valueString, this);

        tv_sum_insured.setText("" + mCI.getSumInsured());
        tv_basic_premium.setText("" + mCI.getBasicPremium());
        tv_training_levy.setText("" + mCI.getTrainingLevy());
        tv_sticker_fees.setText("" + mCI.getStickerFees());
        tv_VAT.setText("" + mCI.getVAT());
        tv_stamp_duty.setText("" + mCI.getStampDuty());
        tv_total_premium.setText("" + mCI.getTotalPremium());

        String x = "SumInsured: UGX " + mCI.getSumInsured()
                + "\n" + "BasicPremium: UGX " + mCI.getBasicPremium()

                + "\n" + "TrainingLevy: UGX " + mCI.getTrainingLevy()
                + "\n" + "StickerFees: UGX " + mCI.getStickerFees()
                + "\n" + "VAT: UGX " + mCI.getVAT()
                + "\n" + "StampDuty: UGX " + mCI.getStampDuty()
                + "\n" + "TotalPremium: UGX " + mCI.getTotalPremium();


        Log.d("Results", x);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fabCallLion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0702157270"));
                startActivity(intent);
            }
        });


    }

}
