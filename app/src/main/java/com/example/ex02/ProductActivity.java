package com.example.ex02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    TextView txtResult;
    ListView list;
    List<ProductDTO> items;
    RecyclerView rv;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo1);
        txtResult = (TextView)findViewById(R.id.txtResult);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));
        items = new ArrayList<>();
        ProductDTO[] dtos = new ProductDTO[10];
        for (int i = 0; i < dtos.length; i++) {
            dtos[i] = new ProductDTO("상품" + i, i * 10000);
            items.add(dtos[i]);
        }
        myAdapter = new MyAdapter();
        rv.setAdapter(myAdapter);


    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);

            return new MyAdapter.ViewHolder(rowItem);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            holder.txtPrice.setText(items.get(position).getPrice() + "원");
        }

        @Override
        public int getItemCount() {
            Log.i("test", "자료개수 : " + items.size() + "");

            return items.size();
        }

        //child view의 화면구성 및 이벤트 처리
        public class ViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {
            private TextView txtProductName, txtPrice;

            public ViewHolder(View view) {
                super(view);
                view.setOnClickListener(this);
                //id사 text1인 테그 연결
                this.txtProductName = view.findViewById(R.id.txtProductName);
                this.txtPrice = view.findViewById(R.id.txtPrice);
            }

            @Override
            public void onClick(View view) {


            }

        }
    }
}


