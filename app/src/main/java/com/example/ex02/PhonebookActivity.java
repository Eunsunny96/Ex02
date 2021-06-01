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
import android.provider.FontRequest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhonebookActivity extends AppCompatActivity {
    TextView txtResult;
    List<PhonebookDTO> items;
    Uri number;
    RecyclerView rv;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo1);
        txtResult = findViewById(R.id.txtResult);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));
        items = new ArrayList<PhonebookDTO>();
        items.add(new PhonebookDTO("김청수", "010-111-222"));
        items.add(new PhonebookDTO("박청수", "010-151-222"));
        items.add(new PhonebookDTO("김청수", "010-441-222"));
        items.add(new PhonebookDTO("김영희", "010-144-5555"));
        myAdapter = new MyAdapter();
        rv.setAdapter(myAdapter);


    }

    class MyAdapter extends RecyclerView.Adapter<PhonebookActivity.MyAdapter.ViewHolder> {


        @Override
        public PhonebookActivity.MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonebook, parent, false);

            return new MyAdapter.ViewHolder(rowItem);
        }

        @Override
        public void onBindViewHolder(PhonebookActivity.MyAdapter.ViewHolder holder, int position) {
            holder.txtName.setText(items.get(position).getName());
            holder.txtTel.setText(items.get(position).getTel());
        }

        @Override
        public int getItemCount() {
            Log.i("test", "자료개수 : " + items.size() + "");

            return items.size();
        }

        //child view의 화면구성 및 이벤트 처리
        public class ViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {
            private TextView txtName, txtTel;

            public ViewHolder(View view) {
                super(view);
                view.setOnClickListener(this);
                //id사 text1인 테그 연결
                this.txtName = view.findViewById(R.id.txtName);
                this.txtTel = view.findViewById(R.id.txtTel);
            }

            @Override
            public void onClick(View v) {//이벤트 처리
                number = Uri.parse("tel:" + items.get(getLayoutPosition()).getTel());
                check();
            }
        }
    }

    void check() {
        int check = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);
        if (check != PackageManager.PERMISSION_GRANTED) {//허용 X
            //권한 허용 다이얼로그
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {//허용 상태
            //전화걸기 화면으로 이동
            Intent intent = new Intent(Intent.ACTION_CALL, number);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    check();
                }
                    break;
                }
        }
    }





