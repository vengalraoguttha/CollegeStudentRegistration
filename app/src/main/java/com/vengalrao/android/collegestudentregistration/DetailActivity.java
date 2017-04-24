package com.vengalrao.android.collegestudentregistration;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vengalrao.android.collegestudentregistration.Data.DataContract;

public class DetailActivity extends AppCompatActivity {
    TextView name;
    TextView age;
    TextView dept;
    TextView phone;
    TextView mail;
    ImageView imageView;
    Student s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Intent intent=getIntent();
        s=intent.getParcelableExtra("data");
        name=(TextView)findViewById(R.id.name_det);
        age=(TextView)findViewById(R.id.age_det);
        dept=(TextView)findViewById(R.id.dept_det);
        phone=(TextView)findViewById(R.id.phone_det);
        mail=(TextView)findViewById(R.id.mail_det);
        imageView=(ImageView)findViewById(R.id.delete);

        name.setText(s.getStudentName());
        age.setText(s.getAge());
        dept.setText(s.getDepartment());
        phone.setText(s.getPhoneNo());
        mail.setText(s.getMailId());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= DataContract.Student.CONTENT_STUDENT_URI.buildUpon().appendPath(s.getStudentName()).build();
                int x=getContentResolver().delete(uri,null,null);
                Intent intent1=new Intent(DetailActivity.this,MainActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        });
    }
}
