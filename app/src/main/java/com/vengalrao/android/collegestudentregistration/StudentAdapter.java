package com.vengalrao.android.collegestudentregistration;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vengalrao on 24-04-2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentAdapterViewHolder> {

    private Context mContext;
    final private ListItemClickListener mListItemClickListener;
    Student[] students;

    public StudentAdapter(Context context,ListItemClickListener listItemClickListener){
        mContext=context;
        mListItemClickListener=listItemClickListener;
    }


    @Override
    public StudentAdapter.StudentAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutId=R.layout.student_item_layout;
        LayoutInflater inflater= LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately=false;
        View view=inflater.inflate(layoutId,parent,shouldAttachToParentImmediately);
        return new StudentAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentAdapter.StudentAdapterViewHolder holder, int position) {
        Student currentStudent=students[position];
        if(currentStudent!=null){
            if(currentStudent.getStudentName()!=null)
                holder.name.setText(currentStudent.getStudentName());
            holder.dept.setText(currentStudent.getDepartment());
        }
    }

    @Override
    public int getItemCount() {
        if(students==null)
        return 0;
        else
            return students.length;
    }

    public interface ListItemClickListener{
        void onListItemClickListener(int clickedPosition,View view);
    }

    class StudentAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView dept;
        public StudentAdapterViewHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.student_name);
            dept=(TextView)view.findViewById(R.id.student_dept);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Log.v("xxxxxxx",v.getTag().toString());
            int clickedPosition=getAdapterPosition();
            mListItemClickListener.onListItemClickListener(clickedPosition,v);
        }
    }

    public void setData(Student[] data){
        students=data;
        notifyDataSetChanged();
    }
}
