package br.com.mh3d.leitorqrcodeatividade.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;
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

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT t.*, a.atividade , r.relatorio " +
            "FROM trabalho_table t " +
            "JOIN atividade_table a ON t.trabalho_id_atividade = a.id_atividade " +
            "JOIN relatorio_table r ON t.trabalho_id_relatorio = r.id_relatorio " +
            "ORDER BY t.id_trabalho DESC")
    LiveData<List<TrabalhoComAtividadeRelatorio>> getHistoricoTrabalhos();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT t.*, a.atividade , r.relatorio " +
            "FROM trabalho_table t " +
            "JOIN atividade_table a ON t.trabalho_id_atividade = a.id_atividade " +
            "JOIN relatorio_table r ON t.trabalho_id_relatorio = r.id_relatorio " +
            "WHERE t.tempo_fim IS NOT NULL  " +
            "ORDER BY t.tempo_fim DESC")
    LiveData<List<TrabalhoComAtividadeRelatorio>> getHistoricoConcluidos();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT t.*, a.atividade , r.relatorio " +
            "FROM trabalho_table t " +
            "JOIN atividade_table a ON t.trabalho_id_atividade = a.id_atividade " +
            "JOIN relatorio_table r ON t.trabalho_id_relatorio = r.id_relatorio " +
            "WHERE t.tempo_fim IS NULL " +
            "ORDER BY t.tempo_inicio DESC")
    LiveData<List<TrabalhoComAtividadeRelatorio>> getHistoricoEmAndamento();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT t.*, a.atividade , r.relatorio " +
            "FROM trabalho_table t " +
            "JOIN atividade_table a ON t.trabalho_id_atividade = a.id_atividade " +
            "JOIN relatorio_table r ON t.trabalho_id_relatorio = r.id_relatorio " +
            "WHERE id_usuario=:id_usuario " +
            "ORDER BY id_trabalho DESC " +
            "LIMIT 1 ")
    LiveData<TrabalhoComAtividadeRelatorio> getUserById(int id_usuario);

}
