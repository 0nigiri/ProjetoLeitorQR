package br.com.mh3d.leitorqrcodeatividade.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.mh3d.leitorqrcodeatividade.database.dao.AtividadeDAO;
import br.com.mh3d.leitorqrcodeatividade.database.dao.RelatorioDAO;
import br.com.mh3d.leitorqrcodeatividade.database.dao.TrabalhoDAO;
import br.com.mh3d.leitorqrcodeatividade.models.Atividades;
import br.com.mh3d.leitorqrcodeatividade.models.Relatorio;
import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;

@Database(entities = {Trabalho.class, Atividades.class, Relatorio.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase INSTANCE;
    public abstract TrabalhoDAO trabalhoDAO();
    public abstract AtividadeDAO atividadeDAO();
    public abstract RelatorioDAO relatorioDAO();


    public static synchronized AppDataBase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "trabalho_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();


        }
    };



    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private AtividadeDAO atividadeDAO;
        private RelatorioDAO relatorioDAO;
        private TrabalhoDAO trabalhoDAO;

        private PopulateDbAsyncTask(AppDataBase db){
           atividadeDAO = db.atividadeDAO();
           relatorioDAO = db.relatorioDAO();
           trabalhoDAO = db.trabalhoDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            relatorioDAO.insert(new Relatorio(0, "Nada a relatar"));
            relatorioDAO.insert(new Relatorio(1, "Falta de material"));
            relatorioDAO.insert(new Relatorio(2,"Falta de energia eletrica"));
            relatorioDAO.insert(new Relatorio(3, "Tempestade"));

            atividadeDAO.insert(new Atividades(1,"Instalacao de gas"));
            atividadeDAO.insert(new Atividades(2, "Revestimento ceramico do piso"));
            atividadeDAO.insert(new Atividades(3,"Azuleijo"));
            trabalhoDAO.insert(new Trabalho(1,"Thiago onishi",2, "1573086958","1573104958", 3,"4 andar" ));

            return null;
        }
    }


}
