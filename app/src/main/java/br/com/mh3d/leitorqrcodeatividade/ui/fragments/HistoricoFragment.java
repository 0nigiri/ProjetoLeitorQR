package br.com.mh3d.leitorqrcodeatividade.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.R;
import br.com.mh3d.leitorqrcodeatividade.models.TrabalhoComAtividadeRelatorio;
import br.com.mh3d.leitorqrcodeatividade.ui.adapter.TrabalhoAdapter;
import br.com.mh3d.leitorqrcodeatividade.ui.viewmodel.TrabalhoViewModel;

public class HistoricoFragment extends Fragment {

    private TrabalhoViewModel trabalhoViewModel;
    private List<TrabalhoComAtividadeRelatorio> mHistorico;
    private Button historicoTodos;
    private Button historicoEmAndamento;
    private Button historicoConcluido;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historico, container, false);

      final  RecyclerView recyclerView = root.findViewById(R.id.recycler_historico);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final TrabalhoAdapter adapter = new TrabalhoAdapter();
        recyclerView.setAdapter(adapter);




        historicoTodos = root.findViewById(R.id.button_historico_todos);
        historicoEmAndamento = root.findViewById(R.id.button_historico_andamento);
        historicoConcluido = root.findViewById(R.id.button_historico_concluidos);
        trabalhoViewModel = ViewModelProviders.of(getActivity()).get(TrabalhoViewModel.class);
        historicoEmAndamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trabalhoViewModel.gethistoricoEmAndamento().observe(getActivity(), new Observer<List<TrabalhoComAtividadeRelatorio>>() {
                    @Override
                    public void onChanged(List<TrabalhoComAtividadeRelatorio> historico) {
                        recyclerView.setAdapter(adapter);
                        adapter.setHistorico(historico);
                    }
                });

            }
        });

        historicoTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trabalhoViewModel.getHistoricoTrabalhos().observe(getActivity(), new Observer<List<TrabalhoComAtividadeRelatorio>>() {
                    @Override
                    public void onChanged(List<TrabalhoComAtividadeRelatorio> historico) {
                        recyclerView.setAdapter(adapter);
                        adapter.setHistorico(historico);
                    }
                });

            }
        });


        historicoConcluido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trabalhoViewModel.gethistoricoConcluido().observe(getActivity(), new Observer<List<TrabalhoComAtividadeRelatorio>>() {
                    @Override
                    public void onChanged(List<TrabalhoComAtividadeRelatorio> historico) {
                        recyclerView.setAdapter(adapter);
                        adapter.setHistorico(historico);
                    }
                });

            }
        });






        return root;
    }
}