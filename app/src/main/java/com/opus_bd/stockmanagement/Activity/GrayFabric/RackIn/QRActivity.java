package com.opus_bd.stockmanagement.Activity.GrayFabric.RackIn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.opus_bd.stockmanagement.Activity.GrayFabric.GaryFebricActivity;
import com.opus_bd.stockmanagement.Activity.LoginActivity;
import com.opus_bd.stockmanagement.Activity.StockIn.List2Activity;
import com.opus_bd.stockmanagement.Adapter.GrayFebric.RollListAdapter;
import com.opus_bd.stockmanagement.Model.GrayFabric.MultipleRollRackInModel;
import com.opus_bd.stockmanagement.Model.GrayFabric.Roll;
import com.opus_bd.stockmanagement.Model.GrayFabric.Scan.GrayRollDetailInfo;
import com.opus_bd.stockmanagement.R;
import com.opus_bd.stockmanagement.RetrofitService.RetrofitClientInstance;
import com.opus_bd.stockmanagement.RetrofitService.RetrofitService;
import com.opus_bd.stockmanagement.Utilts.SharedPrefManager;
import com.opus_bd.stockmanagement.Utilts.Utilities;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class QRActivity extends AppCompatActivity {
    public int counterRoll = 0, btncode;
    @BindView(R.id.buttonScanRoll)
    Button buttonScanRoll;
    @BindView(R.id.buttonScanQRCode)
    Button buttonScanQRCode;
    int c = 0, rackid, detailsid;
    String qr;
    ArrayList<String> rollList = new ArrayList<String>();
    ArrayList<Integer> rollItemList = new ArrayList<>();
    ArrayList<Roll> rollArrayList = new ArrayList<>();
    RollListAdapter rollListAdapter;


    @BindView(R.id.rvRoll)
    RecyclerView rvRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r);
        ButterKnife.bind(this);

        buttonScanRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btncode = 1;
                try {
                    (new IntentIntegrator(QRActivity.this)).initiateScan();
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" No Error e   =  " + e.toString());
                }

            }
        });
        buttonScanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btncode = 2;
                try {
                    (new IntentIntegrator(QRActivity.this)).initiateScan();
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" No Error e   =  " + e.toString());
                }

            }
        });


        rollListAdapter = new RollListAdapter(rollList, this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        rvRoll.setLayoutManager(layoutManager1);
        rvRoll.setAdapter(rollListAdapter);

    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Utilities.showLogcatMessage(" No Error");

        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", LENGTH_LONG).show();
            } else {
                if (btncode == 1) {
                    Addroll(result.getContents());
                    GetGrayRollDetailInfo(result.getContents());
                    // Toast.makeText((Context)this, (CharSequence)("Scanned: " + result.getContents()), LENGTH_LONG).show();
                } else {
                    submitToServer(result.getContents());
                }


            }
        }

    }

    public void GetGrayRollDetailInfo(String id) {
        Utilities.showLogcatMessage(" Gray response 0");
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        //  Call<LoginResponce> registrationRequest = retrofitService.GetFinishFabricReceivedList(userModel);
        String token = SharedPrefManager.getInstance(this).getToken();

        if (token != null) {
            Utilities.showLogcatMessage(" Gray response token");
            Call<GrayRollDetailInfo> registrationRequest = retrofitService.GetGrayRollDetailInfo(SharedPrefManager.BEARER + token, id);

            try {
                Utilities.showLogcatMessage(" Gray response 1");
                registrationRequest.enqueue(new Callback<GrayRollDetailInfo>() {
                    @Override
                    public void onResponse(Call<GrayRollDetailInfo> call, @NonNull Response<GrayRollDetailInfo> response) {
                        Utilities.showLogcatMessage(" Gray response 2" + response.body());
                        if (response.body() != null) {
                            Utilities.showLogcatMessage(" Gray response 3" + response.body());
                            rollItemList.add(rollItemList.size(), response.body().getGrayFebricsDetailId());
                          /*  for (int i = 0; i < rollItemList.size(); i++) {
                                rollItemList.add(rollItemList.size(),response.body().getGrayFebricsDetailId());
                            }*/

                           /* tvRollNo.setText(response.body().getGrayFebricsDetail().getRollNo());
                            tvLocation.setText(response.body().getGrayFebricsStorageDetail().getCellNo());

                            tvQuantity.setText(String.valueOf(response.body().getGrayFebricsDetail().getGQTY()));

                            if(response.body().getStatusId()==3){
                                tvStatus.setText("Already Stored");
                            }
                            else {
                                tvStatus.setText("Not Yet Stored");
                            }*/

                            qr = response.body().getGrayFebricsStorageDetail().getQrCode();
                            rackid = response.body().getId();
                            detailsid = response.body().getGrayFebricsDetailId();
                        } else {
                            Toasty.error(QRActivity.this, "SESSION_EXPIRED", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(QRActivity.this, List2Activity.class);
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

    private void submitToServer(String rack) {
        Roll roll = new Roll();
        for (int i = 0; i < rollItemList.size(); i++) {
            roll.setDetailsId(rollItemList.get(i));
            rollArrayList.add(roll);
        }

        String token = SharedPrefManager.getInstance(this).getToken();
        MultipleRollRackInModel multipleRollRackInModel = new MultipleRollRackInModel();
        multipleRollRackInModel.setQrCode(rack);
        multipleRollRackInModel.setRolls(rollArrayList);
        for (int i = 0; i < rollArrayList.size(); i++)
            Utilities.showLogcatMessage("rollArrayList " + rollArrayList.get(i).getDetailsId());

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.UpdateGrayFabricMultipleRollRackIn(SharedPrefManager.BEARER + token, multipleRollRackInModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(QRActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                        //   finish();
                        startActivity(new Intent(QRActivity.this, GaryFebricActivity.class));

                    } else {
                        Toast.makeText(QRActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(QRActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(QRActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

   /* private void submitToServerforChange(int di, String qr) {

        String token = SharedPrefManager.getInstance(this).getToken();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.UpdateGrayFabricReceiveStatusAndLocationAPI(SharedPrefManager.BEARER + token, di, qr);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(QRActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                        //   finish();
                        startActivity(new Intent(QRActivity.this, GaryFebricActivity.class));

                    } else {
                        Toast.makeText(QRActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(QRActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(QRActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }*/

   /* public void showAlert(int detail, String Qrc) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("QR code not matched . Are you sure to submit here?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        submitToServerforChange(detail, Qrc);
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }*/

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, GrayFebricDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void Addroll(String rollNO) {
        counterRoll++;


        try {
            rollList.add(counterRoll + ". " + rollNO);
            //addressListAdapter.notifyItemInserted(addressListAdapter.getItemCount()+1);

            rollListAdapter.notifyDataSetChanged();

            for (int i = 0; i < rollList.size(); i++) {
                Utilities.showLogcatMessage("notifyDataSetChanged " + rollList.get(i));
            }
        } catch (Exception e) {
            Utilities.showLogcatMessage("notifyDataSetChanged " + e.toString());
        }


    }


}
