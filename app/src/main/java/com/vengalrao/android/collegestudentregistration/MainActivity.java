package com.vengalrao.android.collegestudentregistration;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vengalrao.android.collegestudentregistration.Data.DataContract;

public class MainActivity extends AppCompatActivity implements StudentAdapter.ListItemClickListener{

    RecyclerView mRecyclerView;
    TextView mTextView;
    StudentAdapter adapter;
    Student[] students;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolBar_main);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        mRecyclerView=(RecyclerView)findViewById(R.id.students_view);
        mTextView=(TextView)findViewById(R.id.error_view);
        adapter=new StudentAdapter(this,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        getData();
        Log.v("xxxx",students.length+"");
        if(students!=null&&students.length!=0){
            adapter.setData(students);
            mTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        else {
            mRecyclerView.setVisibility(View.GONE);
            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText("No Data Available!!");
        }
    }

    public void getData(){
        Cursor cursor=getContentResolver().query(DataContract.Student.CONTENT_STUDENT_URI,
                null,
                null,
                null,
                null);
        Student[] students=null;
        if(cursor!=null) {
            students = new Student[cursor.getCount()];
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                students[i] = new Student();
                students[i].setStudentName(cursor.getString(cursor.getColumnIndex(DataContract.Student.NAME)));
                students[i].setAge(cursor.getString(cursor.getColumnIndex(DataContract.Student.AGE)));
                students[i].setDepartment(cursor.getString(cursor.getColumnIndex(DataContract.Student.DEPARTMENT)));
                students[i].setPhoneNo(cursor.getString(cursor.getColumnIndex(DataContract.Student.PHONE)));
                students[i].setMailId(cursor.getString(cursor.getColumnIndex(DataContract.Student.MAIL)));
                cursor.moveToNext();
            }
        }
        this.students=students;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void button(View view){
        RegistrationDialog dialog=new RegistrationDialog();
        dialog.show(getFragmentManager(),"Registration Dialog");

    }

    @Override
    public void onListItemClickListener(int clickedPosition, View view) {
        getData();
            Intent intent=new Intent(MainActivity.this,DetailActivity.class);
            intent.putExtra("data",students[clickedPosition]);
            startActivity(intent);
    }
}
