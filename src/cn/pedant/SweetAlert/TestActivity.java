package cn.pedant.SweetAlert;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import cn.pedant.SweetAlert.ui.SweetAlertDialog;

public class TestActivity extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        findViewById(R.id.basic_test).setOnClickListener(this);
        findViewById(R.id.under_text_test).setOnClickListener(this);
        findViewById(R.id.error_text_test).setOnClickListener(this);
        findViewById(R.id.success_text_test).setOnClickListener(this);
        findViewById(R.id.warning_confirm_test).setOnClickListener(this);
        findViewById(R.id.warning_cancel_test).setOnClickListener(this);
        findViewById(R.id.custom_img_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SweetAlertDialog sweetAlertDialog = null;
        switch (v.getId()) {
            case R.id.basic_test:
                sweetAlertDialog = new SweetAlertDialog(this);
                break;
            case R.id.under_text_test:
                sweetAlertDialog = new SweetAlertDialog(this);
                sweetAlertDialog.setContentText("It's pretty, isn't it?");
                break;
            case R.id.error_text_test:
                sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
                sweetAlertDialog.setTitleText("Oops...");
                sweetAlertDialog.setContentText("Something went wrong!");
                break;
            case R.id.success_text_test:
                sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("Good job!");
                sweetAlertDialog.setContentText("You clicked the button!");
                break;
            case R.id.warning_confirm_test:
                sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                sweetAlertDialog.setTitleText("Are you sure?");
                sweetAlertDialog.setContentText("Won't be able to recover this file!");
                sweetAlertDialog.setConfirmText("Yes,delete it!");
                sweetAlertDialog.setOnConfirmListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("Deleted!");
                        sDialog.setContentText("Your imaginary file has been deleted!");
                        sDialog.setAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                });
                break;
            case R.id.warning_cancel_test:
                sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                sweetAlertDialog.setTitleText("Are you sure?");
                sweetAlertDialog.setContentText("Won't be able to recover this file!");
                sweetAlertDialog.setCancelText("No,cancel plx!");
                sweetAlertDialog.setConfirmText("Yes,delete it!");
                sweetAlertDialog.showCancelButton(true);
                sweetAlertDialog.setOnCancelListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("Cancelled!");
                        sDialog.setContentText("Your imaginary file is safe :)");
                        sDialog.setAlertType(SweetAlertDialog.ERROR_TYPE);
                    }
                });
                sweetAlertDialog.setOnConfirmListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("Deleted!");
                        sDialog.setContentText("Your imaginary file has been deleted!");
                        sDialog.setAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                });
                break;
            case R.id.custom_img_test:
                sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                sweetAlertDialog.setTitleText("Sweet!");
                sweetAlertDialog.setContentText("Here's a custom image.");
                sweetAlertDialog.setCustomImage(R.drawable.custom_img);
                break;
        }

        if (sweetAlertDialog != null) {
            sweetAlertDialog.show();
        }
    }
}
