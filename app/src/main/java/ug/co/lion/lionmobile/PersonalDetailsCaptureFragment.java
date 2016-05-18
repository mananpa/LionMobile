package ug.co.lion.lionmobile;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import fr.ganfra.materialspinner.MaterialSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalDetailsCaptureFragment extends Fragment {

        TextInputLayout tilSurname,tilFirstName, tilEmail, tilPhoneNumber, tilPostalAddress, tilPhysicalAddress;
        MaterialSpinner spinnerGender;
      //  ArrayAdapter genderAdapter;
        View rootView;

    public PersonalDetailsCaptureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_personal_details_capture, container, false);

        tilSurname = (TextInputLayout) rootView.findViewById(R.id.til_surname);
        tilFirstName = (TextInputLayout) rootView.findViewById(R.id.til_firstname);
        tilEmail = (TextInputLayout) rootView.findViewById(R.id.til_email);
        tilPhoneNumber = (TextInputLayout) rootView.findViewById(R.id.til_phonenumber);
        tilPostalAddress = (TextInputLayout) rootView.findViewById(R.id.til_postaladdress);
        tilPhysicalAddress = (TextInputLayout) rootView.findViewById(R.id.til_physicaladdress);
        spinnerGender = (MaterialSpinner) rootView.findViewById(R.id.spinner_gender);
      //  tilDateOfBirth = (TextInputLayout) rootView.findViewById(R.id.til_);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ApplicationClass globalData = (ApplicationClass) getActivity().getApplicationContext();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.gender_options, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerGender.setAdapter(adapter);

        tilSurname.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //do something
                } else {
                    if (!tilSurname.getEditText().getText().toString().equals("")) {

                        globalData.setLastName(tilSurname.getEditText().getText().toString());

                    }

                }
            }
        });

        tilFirstName.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //do something
                } else {
                    if (!tilFirstName.getEditText().getText().toString().equals("")) {

                        globalData.setFirstName(tilFirstName.getEditText().getText().toString());

                    }

                }
            }
        });

        tilEmail.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //do something
                } else {
                    if (!tilEmail.getEditText().getText().toString().equals("")) {

                        globalData.setEmail(tilEmail.getEditText().getText().toString());

                    }

                }
            }
        });

        tilPhoneNumber.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //do something
                } else {
                    if (!tilPhoneNumber.getEditText().getText().toString().equals("")) {

                        globalData.setPhoneNumber(tilPhoneNumber.getEditText().getText().toString());

                    }

                }
            }
        });

        tilPhysicalAddress.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //do something
                } else {
                    if (!tilPhysicalAddress.getEditText().getText().toString().equals("")) {

                        globalData.setPhysicalAddress(tilPhysicalAddress.getEditText().getText().toString());

                    }

                }
            }
        });

        tilPostalAddress.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //do something
                } else {
                    if (!tilPostalAddress.getEditText().getText().toString().equals("")) {

                        globalData.setPostalAddress(tilPostalAddress.getEditText().getText().toString());

                    }

                }
            }
        });



        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv_clicked = (TextView) view;

                globalData.setGender(tv_clicked.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                spinnerGender.setError("Are you male or female?");
            }
        });


    }
}
