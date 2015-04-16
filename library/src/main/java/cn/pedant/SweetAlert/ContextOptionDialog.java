package cn.pedant.SweetAlert;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



/**
 * Created by pengwei on 15/4/15.
 */
public class ContextOptionDialog extends Dialog{

    private TextView titleTextView;
    private ListView optionListView;
    private Context context;

    private String[]  options;
    private ArrayAdapter<String> optionAdapter;
    private String title;

    private AdapterView.OnItemClickListener listener;

    public ContextOptionDialog(Context context) {
        super(context, R.style.alert_dialog);

        this.context = context;
        setCancelable(true);
        setCanceledOnTouchOutside(false);

    }

    public void setTitle(String title){
        if (title == null){
            return;
        }

        this.title = title;
        if (titleTextView != null){
            titleTextView.setText(title);
        }
    }

    public void setOptions(String[] options){
        if (options == null || options.length < 1){
            return;
        }

        this.options = options;
        if (optionListView != null){
            optionAdapter = new ArrayAdapter<String>(context, R.layout.option_item, options);
            optionListView.setAdapter(optionAdapter);
            optionAdapter.notifyDataSetChanged();
        }
    }

    public void setOnOptionItemClickListner(AdapterView.OnItemClickListener listener){
        this.listener = listener;
    }


    protected ContextOptionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * Similar to {@link Activity#onCreate}, you should initialize your dialog
     * in this method, including calling {@link #setContentView}.
     *
     * @param savedInstanceState If this dialog is being reinitalized after a
     *                           the hosting activity was previously shut down, holds the result from
     *                           the most recent call to {@link #onSaveInstanceState}, or null if this
     *                           is the first time.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_option_dialog);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        optionListView = (ListView) findViewById(R.id.optionListView);

        if (title != null){
            titleTextView.setText(title);
        }

        if (options != null && options.length > 0){
            optionAdapter = new ArrayAdapter<String>(context, R.layout.option_item, options);
            optionListView.setAdapter(optionAdapter);
            optionListView.setOnItemClickListener(new OnOptionClickListener());
            optionAdapter.notifyDataSetChanged();
        }

    }

    private class OnOptionClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (listener != null){
                listener.onItemClick(parent, view, position, id);
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ContextOptionDialog.this.dismiss();
                }
            }, 300);
        }
    }

}
