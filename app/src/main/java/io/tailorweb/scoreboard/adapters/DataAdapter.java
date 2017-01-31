package io.tailorweb.scoreboard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import io.tailorweb.scoreboard.R;
import io.tailorweb.scoreboard.model.Data;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private static final String TAG = DataAdapter.class.getSimpleName() ;
    private Context mContext;
    private Data[] mDatas;

    public DataAdapter(Context context, Data[] datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mDatas[position]);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mDatas.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitleTextView;
        public TextView mScoreTextView;
        public ElegantNumberButton mActionButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            mScoreTextView = (TextView) itemView.findViewById(R.id.scoreTextView);
            mActionButton = (ElegantNumberButton) itemView.findViewById(R.id.actionButton);
            mActionButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String num = mActionButton.getNumber();;
                    mDatas[position].setCount(Integer.parseInt(num));
                    mScoreTextView.setText(num);
                }
            });
        }

        public void bindData(Data data){
            mTitleTextView.setText(data.getTitle());
            mScoreTextView.setText(data.getCount()+"");
            mActionButton.setNumber(data.getCount()+"");
        }

        @Override
        public void onClick(View view) {
        }
    }

}

