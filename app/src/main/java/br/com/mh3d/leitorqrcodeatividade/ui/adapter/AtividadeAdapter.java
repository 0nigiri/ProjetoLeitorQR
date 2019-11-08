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
import br.com.mh3d.leitorqrcodeatividade.models.Atividades;

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.AtividadeHolder> {
    private List<Atividades> atividade = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public AtividadeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_atividade_relatorio, parent, false);
        return new AtividadeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadeHolder holder, int position) {
        Atividades currentAtividade = atividade.get(position);
        holder.textViewAtividade.setText(currentAtividade.getAtividade());
        holder.textViewIdAtividade.setText(String.valueOf(currentAtividade.getId_atividade()));


    }

    @Override
    public int getItemCount() {
        return atividade.size();
    }

    public void setAtividade(List<Atividades> atividade) {
        this.atividade = atividade;
        notifyDataSetChanged();
    }

    public Atividades getAtividadeAt(int position) {
        return atividade.get(position);
    }

    class AtividadeHolder extends RecyclerView.ViewHolder {
        private TextView textViewIdAtividade;
        private TextView textViewAtividade;

        public AtividadeHolder(View itemView) {
            super(itemView);
            textViewIdAtividade = itemView.findViewById(R.id.text_view_id_atividade_relatorio);
            textViewAtividade = itemView.findViewById(R.id.text_view_atividade_relatorio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                        listener.onItemClick(atividade.get(position));

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Atividades atividades);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
