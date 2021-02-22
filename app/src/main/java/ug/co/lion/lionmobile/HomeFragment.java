package ug.co.lion.lionmobile;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView iv;
    TextView tv;
    View rootView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        iv = (ImageView) rootView.findViewById(R.id.iv_glide);
        tv = (TextView) rootView.findViewById(R.id.tap_me);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Glide.with(getActivity()).load("http://ea-agribusiness.co.ug/wp-content/uploads/2013/09/Agrinsurance-27-660x330.jpg").into(iv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getActivity()).load("http://www.newvision.co.ug/newvision_cms/newsimages/file/lion-assurance-2-10.jpg").into(iv);
            }
        });


    }
}
