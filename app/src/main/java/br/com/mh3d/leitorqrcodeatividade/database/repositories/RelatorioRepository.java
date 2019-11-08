package br.com.mh3d.leitorqrcodeatividade.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.database.AppDataBase;
import br.com.mh3d.leitorqrcodeatividade.database.dao.RelatorioDAO;
import br.com.mh3d.leitorqrcodeatividade.models.Relatorio;

public class RelatorioRepository {
    private RelatorioDAO relatorioDAO;
    private LiveData<List<Relatorio>> allRelatorios;

        public RelatorioRepository(Application application){
            AppDataBase dataBase = AppDataBase.getInstance(application);
            relatorioDAO = dataBase.relatorioDAO();
            allRelatorios = relatorioDAO.getAllRelatorio();
        }

    public void insert(Relatorio relatorio){
        new InsertRelatorioAsyncTask(relatorioDAO).execute(relatorio);
    }
    public void update(Relatorio relatorio){
        new UpdateRelatorioAsyncTask(relatorioDAO).execute(relatorio);
    }

    public void delete(Relatorio relatorio){
        new DeleteRelatorioAsyncTask(relatorioDAO).execute(relatorio);
    }

    public LiveData<List<Relatorio>> getAllRelatorio() {
        return allRelatorios;
    }

    private static class InsertRelatorioAsyncTask extends AsyncTask<Relatorio, Void, Void> {
        private RelatorioDAO relatorioDAO;

        private InsertRelatorioAsyncTask(RelatorioDAO relatorioDAO){
            this.relatorioDAO = relatorioDAO;
        }

        @Override
        protected Void doInBackground(Relatorio... relatorio) {
            relatorioDAO.insert(relatorio[0]);
            return null;
        }
    }

    private static class UpdateRelatorioAsyncTask extends AsyncTask<Relatorio, Void, Void>{
        private RelatorioDAO relatorioDAO;

        private UpdateRelatorioAsyncTask(RelatorioDAO relatorioDAO){
            this.relatorioDAO = relatorioDAO;
        }

        @Override
        protected Void doInBackground(Relatorio... relatorio) {
            relatorioDAO.update(relatorio[0]);
            return null;
        }

    }

    private static class DeleteRelatorioAsyncTask extends AsyncTask<Relatorio, Void, Void>{
        private RelatorioDAO relatorioDAO;

        private DeleteRelatorioAsyncTask(RelatorioDAO relatorioDAO){
            this.relatorioDAO = relatorioDAO;
        }

        @Override
        protected Void doInBackground(Relatorio... relatorio) {
            relatorioDAO.delete(relatorio[0]);
            return null;
        }
    }


}
