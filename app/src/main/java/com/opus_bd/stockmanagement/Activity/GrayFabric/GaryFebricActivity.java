package com.opus_bd.stockmanagement.Activity.GrayFabric;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.opus_bd.stockmanagement.Activity.DashBoardActivity;
import com.opus_bd.stockmanagement.Activity.GrayFabric.RackIn.GrayFebricRackInActivity;
import com.opus_bd.stockmanagement.Activity.GrayFabric.RackIn.GrayFebricRackOutActivity;
import com.opus_bd.stockmanagement.Activity.ListActivity;
import com.opus_bd.stockmanagement.Activity.WareHouseActivity;
import com.opus_bd.stockmanagement.Fragment.GrayFebric.HistoryFragment;
import com.opus_bd.stockmanagement.Fragment.GrayFebric.RankInFragment;
import com.opus_bd.stockmanagement.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GaryFebricActivity extends AppCompatActivity {
    @BindView(R.id.ivFinishedFebric)
    ImageView ivFinishedFebric;

    @BindView(R.id.ivGrayFebric)
    ImageView ivGrayFebric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gary_febric);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ButterKnife.bind(this);
        imageload();
    }

    public void imageload() {
        Glide.with(this).load(R.drawable.finised_febric).into(ivFinishedFebric);
        Glide.with(this).load(R.drawable.gray_febric).into(ivGrayFebric);

    }

    @OnClick(R.id.cvFinishedFebric)
    public void cvFinishedFebricOnclick() {
        Intent intent = new Intent(GaryFebricActivity.this, GrayFebricRackInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    } @OnClick(R.id.cvGrayFebric)
    public void cvGrayFebricOnclick() {
        Intent intent = new Intent(GaryFebricActivity.this, GrayFebricRackOutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(this, ListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
