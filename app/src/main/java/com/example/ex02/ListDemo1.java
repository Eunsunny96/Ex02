package com.example.ex02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ListDemo1 extends AppCompatActivity {
    TextView txtResult;
    String[] items ={"사과","포도","레몬","수박","바나나","체리"};
    RecyclerView rv;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo1);

        txtResult =findViewById(R.id.txtResult);
        rv=findViewById(R.id.rv);
        //리사이클러뷰의 화면 배치방법
        rv.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false));
        //구분자 설정 항목 사이에 선
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(),
                DividerItemDecoration.VERTICAL));
        //아답터 생성 및 성정 (내부 클래스)
        myAdapter = new MyAdapter(items);
        rv.setAdapter(myAdapter);
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private String[] data;

        public  MyAdapter(String[] data){
            this.data =data;
        }
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list1_row,parent,false);

            return new ViewHolder(rowItem);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            holder.textView.setText(this.data[position]);
        }

        @Override
        public int getItemCount() {
            return this.data.length;
        }
        //child view의 화면구성 및 이벤트 처리
        public  class ViewHolder extends  RecyclerView.ViewHolder
                implements View.OnClickListener{
            private TextView textView;
            public  ViewHolder(View view){
                super(view);
                view.setOnClickListener(this);
                //id사 text1인 테그 연결
                this.textView=view.findViewById(R.id.text1);
            }
            @Override
            public void onClick(View view) {//이벤트 처리

                Toast.makeText(view.getContext(),"position : " +
                        getLayoutPosition() + "text : " +
                        this.textView.getText(),Toast.LENGTH_SHORT).show();
                txtResult.setText(items[getLayoutPosition()]);

            }
        }
    }
}