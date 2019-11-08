package br.com.mh3d.leitorqrcodeatividade.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.database.AppDataBase;
import br.com.mh3d.leitorqrcodeatividade.database.dao.TrabalhoDAO;
import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;
import br.com.mh3d.leitorqrcodeatividade.models.TrabalhoComAtividadeRelatorio;

public class TrabalhoRepository {
    private TrabalhoDAO trabalhoDAO;
    private LiveData<List<TrabalhoComAtividadeRelatorio>> getHistoricoTrabalhos;

    public TrabalhoRepository(Application application) {
        AppDataBase trabalhoDatabase = AppDataBase.getInstance(application);
        trabalhoDAO = trabalhoDatabase.trabalhoDAO();
        getHistoricoTrabalhos = trabalhoDAO.getHistoricoTrabalhos();
    }


    public void insert(Trabalho trabalho) {
        new InsertTrabalhoAsyncTask(trabalhoDAO).execute(trabalho);

    }

    public void update(Trabalho trabalho) {
        new UpdateTrabalhoAsyncTask(trabalhoDAO).execute(trabalho);

    }

    public void delete(Trabalho trabalho) {
        new DeleteTrabalhoAsyncTask(trabalhoDAO).execute();
    }


    public LiveData<List<TrabalhoComAtividadeRelatorio>> getHistoricoTrabalhos() {
        return getHistoricoTrabalhos;
    }


    public LiveData<TrabalhoComAtividadeRelatorio> getGetUserById(int id_usuario){
        return trabalhoDAO.getUserById(id_usuario);
    }







    private static class InsertTrabalhoAsyncTask extends AsyncTask<Trabalho, Void, Void> {
        private TrabalhoDAO trabalhoDAO;

        private InsertTrabalhoAsyncTask(TrabalhoDAO trabalhoDAO) {
            this.trabalhoDAO = trabalhoDAO;
        }

        @Override
        protected Void doInBackground(Trabalho... trabalho) {
            trabalhoDAO.insert(trabalho[0]);
            return null;
        }
    }

    private static class UpdateTrabalhoAsyncTask extends AsyncTask<Trabalho, Void, Void> {
        private TrabalhoDAO trabalhoDAO;

        private UpdateTrabalhoAsyncTask(TrabalhoDAO trabalhoDAO) {
            this.trabalhoDAO = trabalhoDAO;
        }

        @Override
        protected Void doInBackground(Trabalho... trabalho) {
            trabalhoDAO.update(trabalho[0]);
            return null;
        }

    }

    private static class DeleteTrabalhoAsyncTask extends AsyncTask<Trabalho, Void, Void> {
        private TrabalhoDAO trabalhoDAO;

        private DeleteTrabalhoAsyncTask(TrabalhoDAO trabalhoDAO) {
            this.trabalhoDAO = trabalhoDAO;
        }

        @Override
        protected Void doInBackground(Trabalho... trabalho) {
            trabalhoDAO.delete(trabalho[0]);
            return null;
        }
    }


}
