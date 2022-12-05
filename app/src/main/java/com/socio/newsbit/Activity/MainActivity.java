package com.socio.newsbit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.socio.newsbit.Adapter.NewsAdapter;
import com.socio.newsbit.Model.News;
import com.socio.newsbit.Model.ResponseModel;
import com.socio.newsbit.R;
import com.socio.newsbit.Service.ApiClient;
import com.socio.newsbit.Service.ApiInterface;
import com.socio.newsbit.utils.OnRecyclerViewItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener {

    private static final String API_KEY="cdc29a6a60204730a65fe6210e126cae";
    TextView appbartextView;
     NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //appbartextView=findViewById(R.id.appbartextView);
        //appbartextView.setText(R.string.app_name);
      final RecyclerView recyclerView=(findViewById(R.id.activity_recyclerview));
            LinearLayoutManager manager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setHasFixedSize(true);
        // assigning ID of the toolbar to a variable
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);
       // toolbar.setTitle(getString(R.string.app_name));



        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> call = apiService.getLatestNews("techcrunch",API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                if(response.body().getStatus().equals("ok")) {
                    List<News> articleList = response.body().getArticles();
                    newsAdapter = new NewsAdapter(articleList);
                    if(articleList.size()>0) {
                         newsAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                       recyclerView.setAdapter(newsAdapter);

                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("out", t.toString());
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.newsAdapterll:
                News article = (News) view.getTag();
                if(!TextUtils.isEmpty(article.getUrl())) {
                    Log.e("clicked url", article.getUrl());
                    Intent webActivity = new Intent(this,WebActivity.class);
                    webActivity.putExtra("url",article.getUrl());
                    startActivity(webActivity);
                }
                break;
        }
    }
}