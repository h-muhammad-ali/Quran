package com.example.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder>{
    private Context context;
    private List<String> verses;

    public Adapter(Context context, List<String> verses) {
        this.context = context;
        this.verses = verses;
    }

    @NonNull
    @Override
    public Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Viewholder holder, int position) {
        holder.verse.setText(verses.get(position));
    }

    @Override
    public int getItemCount() {
        return verses.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        //private ImageView courseIV;
        private TextView verse;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            verse = itemView.findViewById(R.id.verse);
        }
    }

}
