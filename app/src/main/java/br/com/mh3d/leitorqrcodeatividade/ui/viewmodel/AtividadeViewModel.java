package br.com.mh3d.leitorqrcodeatividade.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.database.repositories.AtividadeRepository;
import br.com.mh3d.leitorqrcodeatividade.models.Atividades;

public class AtividadeViewModel extends AndroidViewModel {
    private AtividadeRepository atividadeRepository;
    private LiveData<List<Atividades>> getAllAtividades;
    public AtividadeViewModel(@NonNull Application application) {
        super(application);
        atividadeRepository = new AtividadeRepository(application);
        getAllAtividades = atividadeRepository.getAllAtividades();
    }

    public void insert(Atividades atividades){
        atividadeRepository.insert(atividades);
    }

    public void update(Atividades trabalho){
        atividadeRepository.update(trabalho);
    }

    public LiveData<List<Atividades>> getAllAtividades() {
        return getAllAtividades;
    }




}
