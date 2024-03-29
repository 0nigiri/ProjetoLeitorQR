package br.com.mh3d.leitorqrcodeatividade.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.database.repositories.TrabalhoRepository;
import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;
import br.com.mh3d.leitorqrcodeatividade.models.TrabalhoComAtividadeRelatorio;

public class TrabalhoViewModel extends AndroidViewModel {

    private TrabalhoRepository trabalhoRepository;
    private LiveData<List<TrabalhoComAtividadeRelatorio>> historicoAtividades;
    private LiveData<List<TrabalhoComAtividadeRelatorio>> historicoConcluido;
    private LiveData<List<TrabalhoComAtividadeRelatorio>> historicoEmAndamento;

    public TrabalhoViewModel(@NonNull Application application) {
        super(application);
        trabalhoRepository = new TrabalhoRepository(application);
        historicoEmAndamento = trabalhoRepository.getHistoricoEmAndamento();
        historicoAtividades = trabalhoRepository.getHistoricoTrabalhos();
        historicoConcluido = trabalhoRepository.getHistoricoConcluido();
    }

    public void insert(Trabalho trabalho){
        trabalhoRepository.insert(trabalho);
    }

    public void update(Trabalho trabalho){
        trabalhoRepository.update(trabalho);
    }

    public LiveData<List<TrabalhoComAtividadeRelatorio>> getHistoricoTrabalhos() {
        return historicoAtividades;
    }
    public LiveData<List<TrabalhoComAtividadeRelatorio>> gethistoricoConcluido() {
        return historicoConcluido;
    }
    public LiveData<List<TrabalhoComAtividadeRelatorio>> gethistoricoEmAndamento() {
        return historicoEmAndamento;
    }


    public LiveData<TrabalhoComAtividadeRelatorio> getUserById(int id_usuario){
        return trabalhoRepository.getGetUserById(id_usuario);
    }

}
