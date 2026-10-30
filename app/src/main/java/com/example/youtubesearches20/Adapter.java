package com.example.youtubesearches20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubesearches20.SearchPreferences;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<String> consultas;
    private onItemClickListener listener;
    private SearchPreferences searchPreferences;

    public interface onItemClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public Adapter(ArrayList<String> usuarios, SearchPreferences searchPreferences) {
        this.consultas = usuarios;
        this.searchPreferences = searchPreferences;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtCategoria.setText(consultas.get(position));
        holder.txtConsulta.setText(searchPreferences.getSearch(consultas.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.consultas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtConsulta;
        private TextView txtCategoria;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtConsulta = itemView.findViewById(R.id.txtConsulta);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemLongClick(position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

}
