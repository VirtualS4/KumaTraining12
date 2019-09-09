package com.example.rona.kumatraining12;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
public class BearAdapter extends RecyclerView.Adapter<BearAdapter.BearViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<Bear> listPersonInfo;
    public BearAdapter(Context context, List<Bear> listPersonInfo,OnUserClickListener listener) {
        this.context=context;
        this.listPersonInfo=listPersonInfo;
        this.listener=listener;
    }

    public interface OnUserClickListener{
        void onUserClick(Bear currentPerson, String action);
    }
    @NonNull
    @Override
    public BearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bear_row_item,parent,false);
        BearViewHolder bearviewholder=new BearViewHolder(view);
        return bearviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull BearViewHolder holder, final int position) {
        final Bear currentBear = listPersonInfo.get(position);
        holder.ctxtid.setText(currentBear.getId()+"");
        holder.ctxtName.setText(currentBear.getName());
        holder.ctxtAge.setText(currentBear.getAge()+"");
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentBear,"Edit");
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentBear,"Delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public class BearViewHolder extends RecyclerView.ViewHolder {
        TextView ctxtAge,ctxtName,ctxtid;
        ImageView imgDelete,imgEdit;

        public BearViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtid=itemView.findViewById(R.id.ctxtId);
            ctxtAge=itemView.findViewById(R.id.ctxtAge);
            ctxtName=itemView.findViewById(R.id.ctxtName);
            imgDelete=itemView.findViewById(R.id.imgdelete);
            imgEdit=itemView.findViewById(R.id.imgedit);
        }
    }
}