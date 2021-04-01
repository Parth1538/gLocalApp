package com.triillionaire.dhruv.glocals;

/**
 * Created by Dhruv on 10/3/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Train_Adapter extends RecyclerView.Adapter<Train_Adapter.MyViewHolder> {

    private ArrayList<Train> trainList  = new ArrayList<>();
    Context ctx;

    public Train_Adapter(ArrayList<Train> trainList, Context ctx)
    {
        this.trainList = trainList;
        this.ctx=ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.train_details_row, parent, false);
      MyViewHolder myViewHolder = new MyViewHolder(itemView,ctx,trainList);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Train train = trainList.get(position);

        holder.tno.setText(train.getTno());
        holder.tname.setText(train.getTname());
        holder.tfrom.setText(train.getTfrom());
        holder.tto.setText(train.getTto());
        holder.tatime.setText(train.getTatime());
        holder.tdtime.setText(train.getTdtime());
        holder.ttype.setText(train.getTtype());



    }

    @Override
    public int getItemCount()
    {
        return trainList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tno,tname,tfrom,tto,tatime,tdtime,ttype;

        private ArrayList<Train> trainList  = new ArrayList<Train>();
        Context ctx;
        public MyViewHolder(View view,Context ctx,ArrayList<Train> trainList) {
            super(view);
           view.setOnClickListener(this);
            this.trainList=trainList;
            this.ctx=ctx;
            tno = (TextView) view.findViewById(R.id.TNo);
            tname = (TextView) view.findViewById(R.id.TName);
            tfrom = (TextView) view.findViewById(R.id.TFrom);
            tto = (TextView) view.findViewById(R.id.TTo);
            tatime = (TextView) view.findViewById(R.id.TATime);
            tdtime = (TextView) view.findViewById(R.id.TDTime);
            ttype = (TextView) view.findViewById(R.id.TType);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Train train = this.trainList.get(position);
            Intent intent = new Intent(this.ctx,BookActivity.class);
            intent.putExtra("tno",train.getTno());
            intent.putExtra("tname",train.getTname());
            intent.putExtra("tfrom",train.getTfrom());
            intent.putExtra("tto",train.getTto());
            intent.putExtra("tatime",train.getTatime());
            intent.putExtra("tdtime",train.getTdtime());
            intent.putExtra("ttype",train.getTtype());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.ctx.startActivity(intent);





        }
    }


}
