package com.opus_bd.stockmanagement.Activity.GrayFabric.Rackout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.opus_bd.stockmanagement.Activity.GrayFabric.GaryFebricActivity;
import com.opus_bd.stockmanagement.R;
import com.opus_bd.stockmanagement.RetrofitService.RetrofitClientInstance;
import com.opus_bd.stockmanagement.RetrofitService.RetrofitService;
import com.opus_bd.stockmanagement.Utilts.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class GrayfebricRackOutQrActivity extends AppCompatActivity {
    Button button_scan_qr_code;
    TextView text;
    int detailsid;
    String RollNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grayfebric_rack_out_qr);
        button_scan_qr_code = findViewById(R.id.button_scan_qr_code);
        text = findViewById(R.id.text);
        button_scan_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new IntentIntegrator(GrayfebricRackOutQrActivity.this)).initiateScan();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailsid = bundle.getInt("Item getId");

            RollNo = bundle.getString("Item getRollNo");

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", LENGTH_LONG).show();
            } else if (result.getContents().equals(RollNo)) {
                text.setText("Matched Successfully ");
                text.setTextColor(getResources().getColor(R.color.successColor));

                submitToServer(detailsid);
               /* c++;
                Utilities.showLogcatMessage(" C++ "+c);
                if(c==2){
                    submitToServer(3);
                    text.setText("Matched Successfully ");
                    text.setTextColor(getResources().getColor(R.color.successColor));
                    c=0;
                }*/

                //Toast.makeText((Context)this, (CharSequence)("Scanned: " + result.getContents()), LENGTH_LONG).show();
            } else /*if(!result.getContents().equals(itemname))*/ {
                text.setText(" Not Matched!!");
                text.setTextColor(getResources().getColor(R.color.errorColor));
                //submitToServer(4);
                //Toast.makeText((Context)this, (CharSequence)("Scanned: " + result.getContents()), LENGTH_LONG).show();
            }
        }

    }

    private void submitToServer(int status) {

        String token = SharedPrefManager.getInstance(this).getToken();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.GrayFabricRackOutEditAPI(SharedPrefManager.BEARER + token, detailsid);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(GrayfebricRackOutQrActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                        //   finish();
                        startActivity(new Intent(GrayfebricRackOutQrActivity.this, GaryFebricActivity.class));

                    } else {
                        Toast.makeText(GrayfebricRackOutQrActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(GrayfebricRackOutQrActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(GrayfebricRackOutQrActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, GrayFebricRackoutbyidActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
