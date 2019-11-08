package br.com.mh3d.leitorqrcodeatividade.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.database.AppDataBase;
import br.com.mh3d.leitorqrcodeatividade.database.dao.AtividadeDAO;
import br.com.mh3d.leitorqrcodeatividade.models.Atividades;

public class AtividadeRepository {
    private AtividadeDAO atividadeDAO;
    private LiveData<List<Atividades>> allAtividades;

    public AtividadeRepository(Application application){
        AppDataBase atividadeDatabase = AppDataBase.getInstance(application);
        atividadeDAO = atividadeDatabase.atividadeDAO();
        allAtividades = atividadeDAO.getAllAtividades();
    }

    public void insert(Atividades atividade){
        new InsertAtividadeAsyncTask(atividadeDAO).execute(atividade);
    }
    public void update(Atividades atividade){
        new UpdateAtividadeAsyncTask(atividadeDAO).execute(atividade);
    }

    public void delete(Atividades atividade){
        new DeleteAtividadeAsyncTask(atividadeDAO).execute(atividade);
    }

    public LiveData<List<Atividades>> getAllAtividades() {
        return allAtividades;
    }

    private static class InsertAtividadeAsyncTask extends AsyncTask<Atividades, Void, Void> {
        private AtividadeDAO atividadeDAO;

        private InsertAtividadeAsyncTask(AtividadeDAO atividadeDAO){
            this.atividadeDAO = atividadeDAO;
        }

        @Override
        protected Void doInBackground(Atividades... atividade) {
            atividadeDAO.insert(atividade[0]);
            return null;
        }
    }

    private static class UpdateAtividadeAsyncTask extends AsyncTask<Atividades, Void, Void>{
        private AtividadeDAO atividadeDAO;

        private UpdateAtividadeAsyncTask(AtividadeDAO atividadeDAO){
            this.atividadeDAO = atividadeDAO;
        }

        @Override
        protected Void doInBackground(Atividades... atividade) {
            atividadeDAO.update(atividade[0]);
            return null;
        }

    }

    private static class DeleteAtividadeAsyncTask extends AsyncTask<Atividades, Void, Void>{
        private AtividadeDAO atividadeDAO;

        private DeleteAtividadeAsyncTask(AtividadeDAO atividadeDAO){
            this.atividadeDAO = atividadeDAO;
        }

        @Override
        protected Void doInBackground(Atividades... atividade) {
            atividadeDAO.delete(atividade[0]);
            return null;
        }
    }
}
