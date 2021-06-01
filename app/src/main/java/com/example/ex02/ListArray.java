package com.example.ex02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListArray extends AppCompatActivity {
    EditText edit1;
    Button button1;
    List<String> items;
    RecyclerView rv;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_array);
        rv = (RecyclerView)findViewById(R.id.rv);
        //레이아웃 설정
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        //행마다 선
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(),DividerItemDecoration.VERTICAL));
        edit1= (EditText) findViewById(R.id.edit1);
        button1=(Button) findViewById(R.id.button1);
        //ArrayList 생성
        items = new ArrayList<String>();
        items.add("사과");
        items.add("포도");
        items.add("파인애플");
        items.add("체리");
        items.add("자두");
        //아답터 생성
        myAdapter = new ListArray.MyAdapter(items);
        //아답터 연결
        rv.setAdapter(myAdapter);
        //버튼 클릭 이벤트
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //입력한 스트링
                String str = edit1.getText().toString();
                //ArrayList에 추가
                items.add(str);
                //변경사항 반영
                myAdapter.notifyDataSetChanged();
            }
        });
    }
    //아답터 클래스
    class MyAdapter extends RecyclerView.Adapter<ListArray.MyAdapter.ViewHolder>{
        private List<String> data;

        public  MyAdapter(List<String> data){
            this.data =data;
        }
        //child view 생성
        @Override
        public ListArray.MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list1_row,parent,false);

            return new ListArray.MyAdapter.ViewHolder(rowItem);
        }

        @Override
        //각각 세부 아이템을 view 에 연결시키는 코드
        public void onBindViewHolder(ListArray.MyAdapter.ViewHolder holder, int position) {
            holder.textView.setText(this.data.get(position));
        }


        @Override
        public int getItemCount() {
            return this.data.size();
        }
        //child view의 화면구성 및 이벤트 처리
        public  class ViewHolder extends  RecyclerView.ViewHolder
                implements View.OnClickListener, View.OnLongClickListener{
            private TextView textView;
            public  ViewHolder(View view){
                super(view);
                view.setOnClickListener(this);
                view.setOnLongClickListener(this);

                //id사 text1인 테그 연결
                this.textView=view.findViewById(R.id.text1);
            }

            @Override
            public boolean onLongClick(View v) {
                //getLayoutposition() 아이템의 인덱스 값
                Toast.makeText(v.getContext(),"position : " +
                        getLayoutPosition() + "text : " +
                        this.textView.getText(),Toast.LENGTH_SHORT).show();
                items.remove(getLayoutPosition());//제거
                myAdapter.notifyDataSetChanged();//변경사항 반영 요청
                return true;//false를 하면 화면에 안뜸
            }

            @Override
            public void onClick(View v) {//이벤트 처리




            }

        }


    }
}