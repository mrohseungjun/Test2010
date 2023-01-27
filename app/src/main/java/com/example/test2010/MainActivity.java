package com.example.test2010;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MemberAdapter memberAdapter;
    LinearLayoutManager manager;
    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addConcat();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        MemberService memberService = Retrofit2Client.getInstance().getPhoneService();
        Call<List<Member>> call = memberService.fineAll();

        call.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                List<Member> memberList = response.body();
                recyclerView = findViewById(R.id.recycleView1);
                manager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
                recyclerView.setLayoutManager(manager);

                memberAdapter = new MemberAdapter(memberList);

                recyclerView.setAdapter(memberAdapter);
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {

            }
        });
    }

    private void addConcat() {
        View dialogView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.member_in, null);
        final EditText etName = dialogView.findViewById(R.id.etname);
        final EditText etAge = dialogView.findViewById(R.id.etage);
        final EditText etTel = dialogView.findViewById(R.id.etTel);
        final EditText etAddr = dialogView.findViewById(R.id.etaddr);
        final EditText etEamil = dialogView.findViewById(R.id.etemail);

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("회원 등록");
        dlg.setView(dialogView);
        dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Member memberDto = new Member();
                memberDto.setName(etName.getText().toString());
                memberDto.setAge(etAge.getText().toString());
                memberDto.setTel(etTel.getText().toString());
                memberDto.setAddr(etAddr.getText().toString());
                memberDto.setEamil(etEamil.getText().toString());

                MemberService memberService = Retrofit2Client.getInstance().getPhoneService();
                Call<Member> call = memberService.save(memberDto);

                call.enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        memberAdapter.addItem(response.body());
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {

                    }
                });


            }
        });
        dlg.setNegativeButton("닫기",null);
        dlg.show();
    }
}
