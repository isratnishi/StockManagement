package com.opus_bd.stockmanagement.Activity.GrayFabric.Scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.opus_bd.stockmanagement.Activity.LoginActivity;
import com.opus_bd.stockmanagement.Activity.StockIn.List2Activity;
import com.opus_bd.stockmanagement.Model.GrayFabric.Scan.GrayRollDetailInfo;
import com.opus_bd.stockmanagement.R;
import com.opus_bd.stockmanagement.RetrofitService.RetrofitClientInstance;
import com.opus_bd.stockmanagement.RetrofitService.RetrofitService;
import com.opus_bd.stockmanagement.Utilts.SharedPrefManager;
import com.opus_bd.stockmanagement.Utilts.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class GrayFebricScanQrActivity extends AppCompatActivity {
    @BindView(R.id.buttonScanRoll)
    Button buttonScanRoll;

    @BindView(R.id.buttonScanQRCode)
    Button buttonScanQRCode;

    @BindView(R.id.text)
    TextView text;

    @BindView(R.id.tvRollNo)
    TextView tvRollNo;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.tvQuantity)
    TextView tvQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gray_febric_scan_qr);
        ButterKnife.bind(this);
        buttonScanQRCode.setVisibility(View.GONE);

        buttonScanRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new IntentIntegrator(GrayFebricScanQrActivity.this)).initiateScan();
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", LENGTH_LONG).show();
            } else {
                text.setText(result.getContents());
                text.setTextColor(getResources().getColor(R.color.errorColor));
                //  GetGrayRollDetailInfo(result.getContents()) ;
                //Toast.makeText((Context)this, (CharSequence)("Scanned: " + result.getContents()), LENGTH_LONG).show();
            }
        }

    }

    public void GetGrayRollDetailInfo(String id) {
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        //  Call<LoginResponce> registrationRequest = retrofitService.GetFinishFabricReceivedList(userModel);
        String token = SharedPrefManager.getInstance(this).getToken();

        if (token != null) {
            Call<GrayRollDetailInfo> registrationRequest = retrofitService.GetGrayRollDetailInfo(SharedPrefManager.BEARER + token, id);

            try {
                Utilities.showLogcatMessage(" Gray response 1");
                registrationRequest.enqueue(new Callback<GrayRollDetailInfo>() {
                    @Override
                    public void onResponse(Call<GrayRollDetailInfo> call, @NonNull Response<GrayRollDetailInfo> response) {
                        if (response.body() != null) {
                            Utilities.showLogcatMessage(" Gray response 2" + response.body());
                            tvRollNo.setText(response.body().getGrayFebricsDetail().getRollNo());
                            tvLocation.setText(response.body().getGrayFebricsStorageDetail().getCellNo());
                            tvQuantity.setText(String.valueOf(response.body().getGrayFebricsDetail().getGQTY()));
                        } else {
                            Toasty.error(GrayFebricScanQrActivity.this, "SESSION_EXPIRED", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(GrayFebricScanQrActivity.this, List2Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<GrayRollDetailInfo> call, Throwable t) {
                        Utilities.showLogcatMessage("error " + t.toString());
                    }
                });

            } catch (Exception e) {
                Utilities.showLogcatMessage("error " + e.toString());
            }
        } else {
            Toasty.error(this, "SESSION_EXPIRED", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
