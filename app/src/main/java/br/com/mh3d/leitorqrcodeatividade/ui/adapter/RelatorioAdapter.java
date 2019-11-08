package br.com.mh3d.leitorqrcodeatividade.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.R;
import br.com.mh3d.leitorqrcodeatividade.models.Relatorio;

public class RelatorioAdapter extends RecyclerView.Adapter<RelatorioAdapter.RelatorioHolder>{
    private List<Relatorio> relatorio = new ArrayList<>();
    private RelatorioAdapter.OnItemClickListener listener;


    @NonNull
    @Override
    public RelatorioAdapter.RelatorioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_atividade_relatorio, parent, false);
        return new RelatorioAdapter.RelatorioHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatorioAdapter.RelatorioHolder holder, int position) {
        Relatorio currentRelatorio = relatorio.get(position);
        holder.textViewRelatorio.setText(currentRelatorio.getRelatorio());
        holder.textViewIdRelatorio.setText(String.valueOf(currentRelatorio.getId_relatorio()));


    }

    @Override
    public int getItemCount() {
        return relatorio.size();
    }

    public void setRelatorio(List<Relatorio> relatorio) {
        this.relatorio = relatorio;
        notifyDataSetChanged();
    }

    public Relatorio getRelatorioAt(int position) {
        return relatorio.get(position);
    }

    class RelatorioHolder extends RecyclerView.ViewHolder {
        private TextView textViewIdRelatorio;
        private TextView textViewRelatorio;

        public RelatorioHolder(View itemView) {
            super(itemView);
            textViewIdRelatorio = itemView.findViewById(R.id.text_view_id_atividade_relatorio);
            textViewRelatorio = itemView.findViewById(R.id.text_view_atividade_relatorio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(relatorio.get(position));

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Relatorio relatorio);
    }

    public void setOnItemClickListener(RelatorioAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}

    
    

