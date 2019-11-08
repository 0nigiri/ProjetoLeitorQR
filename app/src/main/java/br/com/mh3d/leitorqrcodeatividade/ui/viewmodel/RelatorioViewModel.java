package br.com.mh3d.leitorqrcodeatividade.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.database.repositories.RelatorioRepository;
import br.com.mh3d.leitorqrcodeatividade.models.Relatorio;

public class RelatorioViewModel extends AndroidViewModel {
    private RelatorioRepository relatorioRepository;
    private LiveData<List<Relatorio>> getAllRelatorio;

    public RelatorioViewModel(@NonNull Application application) {
        super(application);
        relatorioRepository = new RelatorioRepository(application);
        getAllRelatorio = relatorioRepository.getAllRelatorio();
    }

    public void insert(Relatorio relatorio){
        relatorioRepository.insert(relatorio);
    }

    public void update(Relatorio relatorio){
        relatorioRepository.update(relatorio);
    }

    public LiveData<List<Relatorio>> getAllRelatorio() {
        return getAllRelatorio;
    }
}
