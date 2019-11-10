package br.com.mh3d.leitorqrcodeatividade.models;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "trabalho_table",
        foreignKeys = { @ForeignKey(    entity = Atividades.class,
                                        parentColumns = "id_atividade",
                                        childColumns = "trabalho_id_atividade",
                                        onDelete = ForeignKey.CASCADE,
                                        onUpdate = ForeignKey.CASCADE
                        ),
                        @ForeignKey(    entity = Relatorio.class,
                                        parentColumns = "id_relatorio",
                                        childColumns = "trabalho_id_relatorio",
                                        onDelete = ForeignKey.CASCADE,
                                        onUpdate = ForeignKey.CASCADE
                        )} ,
        indices     = { @Index(value = "trabalho_id_atividade"),
                        @Index(value = "trabalho_id_relatorio")
        })
public class Trabalho implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_trabalho")
    private int id_trabalho;
    @ColumnInfo(name = "id_usuario")
    private int id_usuario;
    @ColumnInfo(name = "nome_usuario")
    private String nome_usuario;
    @ColumnInfo(name = "trabalho_id_atividade")
    private int id_atividade;
    @ColumnInfo(name = "tempo_inicio")
    private String tempo_inicio;
    @ColumnInfo(name = "tempo_fim")
    private String tempo_fim;
    @ColumnInfo(name = "trabalho_id_relatorio")
    private int id_relatorio;
    @ColumnInfo(name = "local")
    private String local;


    // Construtores
    public Trabalho(){

    }
    @Ignore
    public Trabalho(int id_usuario, String nome_usuario){
        this.id_usuario= id_usuario;
        this.nome_usuario = nome_usuario;
    }
    @Ignore
    public Trabalho(int id_usuario, String nome_usuario, int id_atividade, String tempo_inicio){
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.id_atividade = id_atividade;
        this.tempo_inicio = tempo_inicio;
    }
    @Ignore
    public Trabalho(int id_usuario, String nome_usuario, int id_atividade, String tempo_inicio, String tempo_fim){
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.id_atividade = id_atividade;
        this.tempo_inicio = tempo_inicio;
        this.tempo_fim = tempo_fim;
    }
    @Ignore
    public Trabalho(int id_usuario, String nome_usuario, int id_atividade, String tempo_inicio, String tempo_fim, int id_relatorio){
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.id_atividade = id_atividade;
        this.tempo_inicio = tempo_inicio;
        this.tempo_fim = tempo_fim;
        this.id_relatorio = id_relatorio;
    }

    @Ignore
    public Trabalho( int id_usuario, String nome_usuario, int id_atividade, String tempo_inicio, String tempo_fim, int id_relatorio, String local) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.id_atividade = id_atividade;
        this.tempo_inicio = tempo_inicio;
        this.tempo_fim = tempo_fim;
        this.id_relatorio = id_relatorio;
        this.local = local;
    }

// Getter and Setters



    public int getId_trabalho() {
        return id_trabalho;
    }

    public void setId_trabalho(int id) {
        this.id_trabalho = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public int getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(int id_atividade) {
        this.id_atividade = id_atividade;
    }

    public int getId_relatorio() {
        return id_relatorio;
    }

    public void setId_relatorio(int id_relatorio) {
        this.id_relatorio= id_relatorio;
    }

    public String getTempo_inicio() {
        return tempo_inicio;
    }

    public void setTempo_inicio(String tempo_inicio) {
        this.tempo_inicio = tempo_inicio;
    }

    public String getTempo_fim() {
        return tempo_fim;
    }

    public void setTempo_fim(String tempo_fim) {
        this.tempo_fim = tempo_fim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    // Parcelable
    @Ignore
    protected Trabalho(Parcel in) {
        id_trabalho = in.readInt();
        id_usuario = in.readInt();
        nome_usuario = in.readString();
        id_atividade = in.readInt();
        id_relatorio = in.readInt();
        tempo_inicio = in.readString();
        tempo_fim = in.readString();
        local = in.readString();
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_trabalho);
        dest.writeInt(id_usuario);
        dest.writeString(nome_usuario);
        dest.writeInt(id_atividade);
        dest.writeInt(id_relatorio);
        dest.writeString(tempo_inicio);
        dest.writeString(tempo_fim);
        dest.writeString(local);
    }

    @Ignore
    public static final Creator<Trabalho> CREATOR = new Creator<Trabalho>() {
        @Override
        public Trabalho createFromParcel(Parcel in) {
            return new Trabalho(in);
        }

        @Override
        public Trabalho[] newArray(int size) {
            return new Trabalho[size];
        }
    };

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }
}
