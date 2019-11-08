package br.com.mh3d.leitorqrcodeatividade;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import br.com.mh3d.leitorqrcodeatividade.database.AppDataBase;
import br.com.mh3d.leitorqrcodeatividade.database.dao.AtividadeDAO;
import br.com.mh3d.leitorqrcodeatividade.database.dao.RelatorioDAO;
import br.com.mh3d.leitorqrcodeatividade.database.dao.TrabalhoDAO;
import br.com.mh3d.leitorqrcodeatividade.models.Atividades;
import br.com.mh3d.leitorqrcodeatividade.models.Relatorio;
import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;
import br.com.mh3d.leitorqrcodeatividade.ui.viewmodel.TrabalhoViewModel;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public class SimpleEntityReadWriteTest {
        private TrabalhoDAO trabalhoDAO;
        private RelatorioDAO relatorioDAO;
        private AtividadeDAO atividadeDAO;
        private AppDataBase db;
        private TrabalhoViewModel trabalhoViewModel;

        @Before
        public void createDb() {
            Context context = ApplicationProvider.getApplicationContext();
            db = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).build();
            trabalhoDAO = db.trabalhoDAO();
            relatorioDAO = db.relatorioDAO();
            atividadeDAO = db.atividadeDAO();
        }

        @After
        public void closeDb() throws IOException {
            db.close();
        }

        @Test
        public void writeUserAndReadInList() throws Exception {

            relatorioDAO.insert(new Relatorio(0, "Nada a relatar"));
            relatorioDAO.insert(new Relatorio(1, "Falta de material"));
            relatorioDAO.insert(new Relatorio(2,"Falta de energia eletrica"));
            relatorioDAO.insert(new Relatorio(3, "Tempestade"));

            atividadeDAO.insert(new Atividades(1,"Instalacao de gas"));
            atividadeDAO.insert(new Atividades(2, "Revestimento ceramico do piso"));
            atividadeDAO.insert(new Atividades(3,"Azuleijo"));
            trabalhoDAO.insert(new Trabalho(1,"Thiago onishi",2, "1573086958","1573104958", 3,"4 andar" ));
            trabalhoDAO.insert(new Trabalho(2,"Jun onishi",1, "1573086958","1573104958", 2,"4 andar" ));
            trabalhoDAO.insert(new Trabalho(1,"Paulo onishi",3, "1573086958","1573104958", 1,"4 andar" ));



            Trabalho byId = trabalhoDAO.getUserById(2);

            Log.d("Nome do usuario", byId.getNome_usuario());

        }


}
}
