package ug.co.lion.lionmobile;


import android.content.Intent;
import android.os.Bundle;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteDetailsFragment extends Fragment {

    MaterialEditText valueEt;
    Button getQuoteButton;
    View rootView;


    public QuoteDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_quote_details, container, false);

        valueEt = (MaterialEditText) rootView.findViewById(R.id.met_vehicle__value_insured);
        getQuoteButton = (Button) rootView.findViewById(R.id.b_get_quote);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(valueEt.getText().toString().isEmpty()){

                    valueEt.setError("Please enter total value to be insured");


                }
                else{

                    //Double value = Double.valueOf(valueEt.getText().toString());
                    String valueString = valueEt.getText().toString();


                    Intent i = new Intent(getActivity(), QuoteView.class);
                    i.putExtra("value", valueString);
                    startActivity(i);




                }
            }
        });
    }
}
