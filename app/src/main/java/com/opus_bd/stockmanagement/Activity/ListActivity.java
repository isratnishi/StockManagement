package com.opus_bd.stockmanagement.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.opus_bd.stockmanagement.Activity.RackOut.RackOutListActivity;
import com.opus_bd.stockmanagement.Activity.StockIn.List2Activity;
import com.opus_bd.stockmanagement.Activity.StockOut.StockOutListActivity;
import com.opus_bd.stockmanagement.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {
 @BindView(R.id.ic_profile)
    ImageView ic_profile;

    @BindView(R.id.ivStockIn)
    ImageView ivStockIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
      imageload();
    }

    public void imageload() {
        Glide.with(this).load(R.drawable.ic_dashboard).into(ic_profile);
        Glide.with(this).load(R.drawable.ic_stockin).into(ivStockIn);

    }

    @OnClick(R.id.cvProfile)
    public void cvProfileOnclick() {
        Intent intent = new Intent(ListActivity.this, DashBoardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    } @OnClick(R.id.cvStockIn)
    public void cvStockInOnclick() {
        Intent intent = new Intent(ListActivity.this, WareHouseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

}
