package br.com.mh3d.leitorqrcodeatividade.ui.adapter;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.R;
import br.com.mh3d.leitorqrcodeatividade.models.TrabalhoComAtividadeRelatorio;
import br.com.mh3d.leitorqrcodeatividade.utils.DateFormatUtils;

public class TrabalhoAdapter extends RecyclerView.Adapter<TrabalhoAdapter.TrabalhoHolder> {
    private DateFormatUtils dateFormatUtils = new DateFormatUtils();

    private List<TrabalhoComAtividadeRelatorio> historico = new ArrayList<>();

    @NonNull
    @Override
    public TrabalhoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_historico, parent, false);
        return new TrabalhoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrabalhoHolder holder, int position) {

        TrabalhoComAtividadeRelatorio currentTrabalho = historico.get(position);
        holder.txtViewIdTrabalho.setText(String.valueOf(currentTrabalho.getId_trabalho()));
        holder.txtViewLocal.setText((currentTrabalho.getLocal()));
        holder.txtViewNomeFuncionario.setText(currentTrabalho.getNome_usuario());
        holder.txtViewAtividade.setText(currentTrabalho.getAtividade());
        holder.txtViewTempoInicio.setText("Iniciado em " + dateFormatUtils.convertUnixStringToDateString(currentTrabalho.getTempo_inicio()));
        if(currentTrabalho.getTempo_fim() != null && !currentTrabalho.getTempo_fim().equals("")) {

            holder.txtViewTempoFim.setVisibility(View.VISIBLE);
            holder.txtViewTempoFim.setText("Finalizado em " + dateFormatUtils.convertUnixStringToDateString(currentTrabalho.getTempo_fim()));
            holder.txtViewRelatorio.setVisibility(View.VISIBLE);
            holder.txtViewRelatorio.setText(currentTrabalho.getRelatorio());

        } else {
            holder.txtViewTempoFim.setVisibility(View.GONE);
            holder.txtViewRelatorio.setVisibility(View.GONE);
        }
    }

    public void setHistorico(List<TrabalhoComAtividadeRelatorio> historico){
        this.historico = historico;
    }

    @Override
    public int getItemCount() {
        return historico.size();
    }

    class TrabalhoHolder extends RecyclerView.ViewHolder{
        private TextView txtViewIdTrabalho;
        private TextView txtViewLocal;
        private TextView txtViewNomeFuncionario;
        private TextView txtViewAtividade;
        private TextView txtViewTempoInicio;
        private TextView txtViewTempoFim;
        private TextView txtViewRelatorio;


        public TrabalhoHolder(@NonNull View itemView) {
            super(itemView);
            txtViewIdTrabalho = itemView.findViewById(R.id.txt_id_trabalho);
            txtViewLocal = itemView.findViewById(R.id.txt_local);
            txtViewNomeFuncionario = itemView.findViewById(R.id.txt_nome_usuario);
            txtViewAtividade = itemView.findViewById(R.id.txt_atividade);
            txtViewTempoInicio = itemView.findViewById(R.id.txt_tempo_inicio);
            txtViewTempoFim = itemView.findViewById(R.id.txt_tempo_fim);
            txtViewRelatorio = itemView.findViewById(R.id.txt_relatorio);
        }
    }


}
