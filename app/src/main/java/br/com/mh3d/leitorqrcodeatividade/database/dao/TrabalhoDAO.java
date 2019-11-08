package br.com.mh3d.leitorqrcodeatividade.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;
import br.com.mh3d.leitorqrcodeatividade.models.TrabalhoComAtividadeRelatorio;

@Dao
public interface TrabalhoDAO {


    @Insert
    public void insert(Trabalho trabalho);

    @Update
    public void update(Trabalho trabalho);

    @Delete
    public void delete(Trabalho trabalho);

    @Query("SELECT t.*, a.atividade , r.relatorio " +
            "FROM trabalho_table t " +
            "JOIN atividade_table a ON t.id_atividade = a.id_atividade " +
            "JOIN relatorio_table r ON t.id_relatorio = r.id_relatorio " +
            "ORDER BY t.tempo_inicio DESC")
    LiveData<List<TrabalhoComAtividadeRelatorio>> getHistoricoTrabalhos();

    @Query("SELECT t.*, a.atividade , r.relatorio " +
            "FROM trabalho_table t " +
            "JOIN atividade_table a ON t.id_atividade = a.id_atividade " +
            "JOIN relatorio_table r ON t.id_relatorio = r.id_relatorio " +
            "WHERE id_usuario=:id_usuario " +
            "ORDER BY id_trabalho DESC " +
            "LIMIT 1 ")
    LiveData<TrabalhoComAtividadeRelatorio> getUserById(int id_usuario);



}
