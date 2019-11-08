package br.com.mh3d.leitorqrcodeatividade.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.models.Atividades;

@Dao
public interface AtividadeDAO {

    @Insert
    void insert(Atividades atividade);

    @Update
    void update(Atividades atividade);

    @Delete
    void delete(Atividades atividade);

    @Query("SELECT * FROM atividade_table ORDER BY id_atividade ASC")
    LiveData<List<Atividades>> getAllAtividades();


}
