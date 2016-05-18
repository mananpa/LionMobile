package ug.co.lion.lionmobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import nl.changer.polypicker.Config;
import nl.changer.polypicker.ImagePickerActivity;
import nl.changer.polypicker.utils.ImageInternalFetcher;

public class StartClaim extends AppCompatActivity {

    private static final int INTENT_REQUEST_GET_IMAGES = 100;
    private ViewGroup mSelectedImagesContainer;
    HashSet<Uri> mMedia = new HashSet<Uri>();
    Button b_getImages, b_submitClaim;
    TextInputLayout til_whatHappened, til_dateAndTime, til_whyAndHow, til_whoWasInvolved, til_totalValueOfLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_claim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSelectedImagesContainer = (ViewGroup) findViewById(R.id.selected_photos_container);
        b_getImages = (Button) findViewById(R.id.get_images);
        b_submitClaim = (Button) findViewById(R.id.b_submit_claim);
        til_dateAndTime = (TextInputLayout) findViewById(R.id.til_date_and_time_it_happened);
        til_totalValueOfLoss = (TextInputLayout) findViewById(R.id.til_total_value_of_loss);
        til_whatHappened = (TextInputLayout) findViewById(R.id.til_what_happened);
        til_whoWasInvolved = (TextInputLayout) findViewById(R.id.til_who_was_involved);
        til_whyAndHow = (TextInputLayout) findViewById(R.id.til_why_and_how_it_happened);

        b_getImages.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getImages();
            }
        });

        b_submitClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean formOk = isFormFine();
                if (formOk) {

                    submit();
                }
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void submit() {

        MyAsyncTask myAsyncTask = new MyAsyncTask();

        myAsyncTask.execute("eric", "senyonga");
    }

    private Boolean isFormFine() {

        if (til_totalValueOfLoss.getEditText().getText().toString().isEmpty()) {

            til_totalValueOfLoss.setError("Please enter estimated value of loss");
            return false;

        }

        if (til_whatHappened.getEditText().getText().toString().isEmpty()) {

            til_whatHappened.setError("Please enter what happened");
            return false;
        }

        return true;


    }

    private void getImages() {
        Intent intent = new Intent(StartClaim.this, ImagePickerActivity.class);
        Config config = new Config.Builder()
                .setTabBackgroundColor(R.color.white)    // set tab background color. Default white.
                .setTabSelectionIndicatorColor(R.color.blue)
                .setCameraButtonColor(R.color.green)
                .setSelectionLimit(8)    // set photo selection limit. Default unlimited selection.
                .build();
        ImagePickerActivity.setConfig(config);
        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == INTENT_REQUEST_GET_IMAGES) {


                Parcelable[] parcelableUris = intent.getParcelableArrayExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);

                if (parcelableUris == null) {
                    return;
                }

                // Java doesn't allow array casting, this is a little hack
                Uri[] uris = new Uri[parcelableUris.length];
                System.arraycopy(parcelableUris, 0, uris, 0, parcelableUris.length);

                if (uris != null) {
                    for (Uri uri : uris) {
                        Log.i("imgUri", " uri: " + uri);
                        mMedia.add(uri);
                    }

                    showMedia();
                }
            }
        }
    }

    private void showMedia() {
        // Remove all views before
        // adding the new ones.
        mSelectedImagesContainer.removeAllViews();

        Iterator<Uri> iterator = mMedia.iterator();
        final ImageInternalFetcher imageFetcher = new ImageInternalFetcher(this, 500);
        while (iterator.hasNext()) {
            Uri uri = iterator.next();

            // showImage(uri);
            Log.i("showImage", " uri: " + uri);
            if (mMedia.size() >= 1) {
                mSelectedImagesContainer.setVisibility(View.VISIBLE);
            }

            View imageHolder = LayoutInflater.from(this).inflate(R.layout.media_layout, null);

            // View removeBtn = imageHolder.findViewById(R.id.remove_media);
            // initRemoveBtn(removeBtn, imageHolder, uri);
            final ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);

            if (!uri.toString().contains("content://")) {
                // probably a relative uri
                uri = Uri.fromFile(new File(uri.toString()));
            }

            imageFetcher.loadImage(uri, thumbnail);

            thumbnail.setTag(uri.toString());

            mSelectedImagesContainer.addView(imageHolder);

            // set the dimension to correctly
            // show the image thumbnail.
            int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(wdpx, htpx));

            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //
                    ImageView iv = new ImageView(StartClaim.this);
                    TextView tv = new TextView(StartClaim.this);
                    String uriString = (String) thumbnail.getTag();
                    tv.setText(uriString);
                    imageFetcher.loadImage(Uri.parse(uriString), iv);



                    AlertDialog.Builder builder = new AlertDialog.Builder(StartClaim.this);
                    builder.setTitle("edit image");
                    builder.setView(iv);

                    Dialog d = builder.create();
                    d.show();


                }
            });
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {


        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(StartClaim.this);
            progressDialog.setMessage("Submiting your Claim...");
            progressDialog.setProgressStyle(android.R.style.Widget_Material_Light_ProgressBar);

            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return "done";


        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);


            progressDialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(StartClaim.this);
            builder.setMessage("Submitted Succesfully");
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(StartClaim.this, MainActivity.class);
                    startActivity(i);
                }
            });
            Dialog d = builder.create();
            d.show();


        }

    }


}
