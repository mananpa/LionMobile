package ug.co.lion.lionmobile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Eriq on 11/3/2015.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment personalDetailsCaptureFragment = new PersonalDetailsCaptureFragment();

        Fragment productsFragment = new ProductsFragment();

        Fragment quoteDetailsFragment = new QuoteDetailsFragment();

        if(position == 0){

            return personalDetailsCaptureFragment;}

        else if(position == 1 ){

            return quoteDetailsFragment;
        }

        else{
            return null;}
    }

    @Override
    public int getCount() {
        return 2;
    }
}
