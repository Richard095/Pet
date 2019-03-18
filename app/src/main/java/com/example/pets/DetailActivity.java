package com.example.pets;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAPTURE_AUDIO_OUTPUT;

public class DetailActivity extends AppCompatActivity {

    public static final String PET_KEY = "pet";
    public static final int CALL_PHONE_REQUEST_CODE =0;

    private String ownerPhoneNumber="";
    TextView tv_description;
    TextView tv_current_owner;
    TextView tv_phone;
    ImageView iv_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.activity_detail_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }


        Bundle extras = getIntent().getExtras();
        final Pet pet = extras.getParcelable(PET_KEY);

        if (pet != null) {



            tv_description = findViewById(R.id.tv_description);
            tv_current_owner = findViewById(R.id.tv_current_owner);
            tv_phone = findViewById(R.id.tv_phone);
            iv_image = findViewById(R.id.iv_image);


            tv_description.setText(pet.getDescription());
            tv_current_owner.setText(pet.getOwnerName());
            ownerPhoneNumber = pet.getPhoneNumber();
            tv_phone.setText(ownerPhoneNumber);
            iv_image.setImageDrawable(ContextCompat.getDrawable(this,pet.getImageId()));

            CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.detail_Collpasing_Toolbar_Layout);
            collapsingToolbarLayout.setTitle(pet.getName());


            FloatingActionButton floating_Button = findViewById(R.id.floating_Button);
            floating_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Call();
                }
            });
        }

    }


    public void Call(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ownerPhoneNumber));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(CALL_PHONE) ==PackageManager.PERMISSION_GRANTED){
                startActivity(intent);
            }else{
                final String[] permissions = new String[]{CALL_PHONE};
                requestPermissions(permissions,CALL_PHONE_REQUEST_CODE);
            }
        }else{
            startActivity(intent);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if (requestCode == CALL_PHONE_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Call();
            }else if(shouldShowRequestPermissionRationale(CALL_PHONE)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Hacer llamadas");
                builder.setMessage("Debes aceptar este permiso para hacer llamadas desde la app mascotas");
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String[] permissions = new String[] {CALL_PHONE};
                        requestPermissions(permissions,CALL_PHONE_REQUEST_CODE);
                    }
                });
                builder.show();
            }
        }
    }
}
