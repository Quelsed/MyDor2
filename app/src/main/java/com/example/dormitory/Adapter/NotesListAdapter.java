package com.example.dormitory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dormitory.NotesClickListener;
import com.example.dormitory.NotesFB;
import com.example.dormitory.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotsViewHolder>{

    Context context;
    List<NotesFB> list;

    NotesClickListener listener;

    public NotesListAdapter(Context context, List <NotesFB> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotsViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotsViewHolder holder, int position) {

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        holder.textView_roomnumber.setText(list.get(position).getRoomnumber());
        holder.textView_roomnumber.setSelected(true);

        holder.textView_comm.setText(list.get(position).getComm());
        holder.textView_comm.setSelected(true);

        holder.textView_place.setText(list.get(position).getPlace());
        holder.textView_place.setSelected(true);

        holder.textView_fio.setText(list.get(position).getFio());
        holder.textView_fio.setSelected(true);

        int color_code=getColor();
        holder.notes_conteiner.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));

        holder.notes_conteiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });
        holder.notes_conteiner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_conteiner);
                return true;
            }
        });
    }

    private int getColor(){
        List <Integer> colorCode=new ArrayList<>();
        colorCode.add(R.color.blue);

        Random random = new Random();
        int color=random.nextInt(colorCode.size());

        return colorCode.get(color);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class NotsViewHolder extends RecyclerView.ViewHolder {

    CardView notes_conteiner;
    TextView textView_roomnumber;
    TextView textView_date;

    TextView textView_comm;

    TextView textView_place;

    TextView textView_fio;
    public NotsViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_conteiner=itemView.findViewById(R.id.notes_conteiner);
        textView_roomnumber=itemView.findViewById(R.id.textView_roomnumber);
        textView_date=itemView.findViewById(R.id.textView_date);
        textView_comm = itemView.findViewById(R.id.comm);
        textView_place=itemView.findViewById(R.id.place);
        textView_fio=itemView.findViewById(R.id.fio);


    }
}