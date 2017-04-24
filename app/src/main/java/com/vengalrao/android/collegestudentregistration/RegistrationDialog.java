package com.vengalrao.android.collegestudentregistration;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.vengalrao.android.collegestudentregistration.Data.DataContract;

import butterknife.ButterKnife;

/**
 * Created by vengalrao on 24-04-2017.
 */

public class RegistrationDialog extends DialogFragment {

    EditText name;
    EditText age;
    EditText dept;
    EditText phone;
    EditText mail;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= LayoutInflater.from(getActivity());
        View custom = inflater.inflate(R.layout.registration_dialog,null);
        ButterKnife.bind(this,custom);
        name=(EditText)custom.findViewById(R.id.reg_name) ;
        age=(EditText)custom.findViewById(R.id.reg_age) ;
        dept=(EditText)custom.findViewById(R.id.reg_dept) ;
        phone=(EditText)custom.findViewById(R.id.reg_phone) ;
        mail=(EditText)custom.findViewById(R.id.reg_mail) ;
        builder.setView(custom);
        builder.setMessage(getString(R.string.dialog_title));
        builder.setPositiveButton(getString(R.string.dialog_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        insertIntoDB();
                    }
                });
        builder.setNegativeButton(getString(R.string.dialog_cancel), null);
        Dialog dialog = builder.create();

        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }

        return dialog;
    }
    private void insertIntoDB(){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataContract.Student.NAME,name.getText().toString());
        contentValues.put(DataContract.Student.AGE,age.getText().toString());
        contentValues.put(DataContract.Student.DEPARTMENT,dept.getText().toString());
        contentValues.put(DataContract.Student.PHONE,phone.getText().toString());
        contentValues.put(DataContract.Student.MAIL,mail.getText().toString());
        contentValues.put(DataContract.Student.GENDER,"Male");
        Uri uri=getActivity().getContentResolver().insert(DataContract.Student.CONTENT_STUDENT_URI,contentValues);
        dismissAllowingStateLoss();
    }
}