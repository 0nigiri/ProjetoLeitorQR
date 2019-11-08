package br.com.mh3d.leitorqrcodeatividade.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.models.Relatorio;

@Dao
public interface RelatorioDAO {

    @Insert
    void insert(Relatorio atividade);

    @Update
    void update(Relatorio atividade);

    @Delete
    void delete(Relatorio atividade);

    @Query("SELECT * FROM relatorio_table ORDER BY id_relatorio ASC")
    LiveData<List<Relatorio>> getAllRelatorio();

}
