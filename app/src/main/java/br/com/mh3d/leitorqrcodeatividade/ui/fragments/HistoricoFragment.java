package br.com.mh3d.leitorqrcodeatividade.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historico, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_historico);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final TrabalhoAdapter adapter = new TrabalhoAdapter();
        recyclerView.setAdapter(adapter);


        trabalhoViewModel = ViewModelProviders.of(getActivity()).get(TrabalhoViewModel.class);
        trabalhoViewModel.getHistoricoTrabalhos().observe(getActivity(), new Observer<List<TrabalhoComAtividadeRelatorio>>() {
            @Override
            public void onChanged(List<TrabalhoComAtividadeRelatorio> historico) {
                adapter.setHistorico(historico);
            }
        });


        return root;
    }
}