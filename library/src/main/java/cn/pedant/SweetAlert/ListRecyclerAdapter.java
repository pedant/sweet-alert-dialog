package cn.pedant.SweetAlert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<String> mData;
    private OnRecyclerListener mListener;
    private int mTextSize;

    public ListRecyclerAdapter(Context context, ArrayList<String> data, OnRecyclerListener listener, int textSize) {
        mInflater = LayoutInflater.from(context);
        mData = data;
        mListener = listener;
        mTextSize = textSize;
    }

    public interface OnRecyclerListener {
        void onItemClick(View v, int position);
    }

    @Override
    public ListRecyclerAdapter.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.list_item_text);
            textView.setTextSize(mTextSize);
        }
    }

}
