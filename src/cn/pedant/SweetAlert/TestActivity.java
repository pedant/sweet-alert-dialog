package cn.pedant.SweetAlert;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import cn.pedant.SweetAlert.ui.SweetAlertDialog;

public class TestActivity extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.basic_test).setOnClickListener(this);
        findViewById(R.id.under_text_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
        switch (v.getId()) {
            case R.id.under_text_test:
                sweetAlertDialog.setContentText("It's pretty, isn't it?");
                break;
        }
        sweetAlertDialog.show();
    }
}
