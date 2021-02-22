package ug.co.lion.lionmobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GetQuote extends AppCompatActivity {

    NonSwipeableViewPager quoteStepsPager;
    MyViewPagerAdapter myViewPagerAdapter;
    Button b_right, b_left;
    TextView tv_label;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        b_right = (Button) findViewById(R.id.b_right);
        b_left = (Button) findViewById(R.id.b_left);
        tv_label = (TextView) findViewById(R.id.tv_label);
        quoteStepsPager = (NonSwipeableViewPager) findViewById(R.id.vp_quotesteps);

        //    quoteStepsPager = new NonSwipeableViewPager(this);


        fm = getSupportFragmentManager();

        myViewPagerAdapter = new MyViewPagerAdapter(fm);

        quoteStepsPager.setAdapter(myViewPagerAdapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b_right.getText().toString().equals("Next")) {

                    setCurrentItem(1, true);
                    b_right.setText("Get Quote");
                    tv_label.setText("Step 2 of 2");
                    b_left.setVisibility(View.VISIBLE);
                    b_right.setVisibility(View.GONE);

                } else if (b_right.getText().toString().equals("Get Quote")) {

                    ApplicationClass globalData = ((ApplicationClass) getApplicationContext());

                    Toast.makeText(GetQuote.this, "LastName: " + globalData.getLastName() + "\n First Name:" + globalData.getFirstName() + "\nGender:"
                            + globalData.getGender() + "\n PhysicalAdd: " + globalData.getPhysicalAddress() + "\n PostalAdd: " + globalData.getPostalAddress()
                            + "\n Email: " + globalData.getEmail(), Toast.LENGTH_LONG).show();

                    Log.d("fromGlobe",  "LastName: " + globalData.getLastName() + "\n First Name:" + globalData.getFirstName() + "\nGender:"
                            + globalData.getGender() + "\n PhysicalAdd: " + globalData.getPhysicalAddress() + "\n PostalAdd: " + globalData.getPostalAddress()
                            + "\n Email: " + globalData.getEmail());
                } ;


            }
        });

        b_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentItem(0, true);
                b_right.setText("Next");
                tv_label.setText("Step 1 of 2");
                b_left.setVisibility(View.GONE);
                b_right.setVisibility(View.VISIBLE);

            }
        });


    }


    public void setCurrentItem(int item, boolean smoothScroll) {
        quoteStepsPager.setCurrentItem(item, smoothScroll);


    }
}
