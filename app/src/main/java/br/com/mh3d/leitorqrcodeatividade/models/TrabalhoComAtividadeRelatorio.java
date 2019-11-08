package br.com.mh3d.leitorqrcodeatividade.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class TrabalhoComAtividadeRelatorio implements Parcelable {

    @ColumnInfo(name = "id_trabalho")
    private int id_trabalho;
    @ColumnInfo(name = "id_usuario")
    private int id_usuario;
    @ColumnInfo(name = "nome_usuario")
    private String nome_usuario;
    @ColumnInfo(name = "id_atividade")
    private int id_atividade;
    @ColumnInfo(name = "atividade")
    private String atividade;
    @ColumnInfo(name = "id_relatorio")
    private int id_relatorio;
    @ColumnInfo(name = "relatorio")
    private String relatorio;
    @ColumnInfo(name = "tempo_inicio")
    private String tempo_inicio;
    @ColumnInfo(name = "tempo_fim")
    private String tempo_fim;
    @ColumnInfo(name = "local")
    private String local;

    public TrabalhoComAtividadeRelatorio() {
    }

    @Ignore
    protected TrabalhoComAtividadeRelatorio(Parcel in) {
        id_trabalho = in.readInt();
        id_usuario = in.readInt();
        nome_usuario = in.readString();
        id_atividade = in.readInt();
        atividade = in.readString();
        id_relatorio = in.readInt();
        relatorio = in.readString();
        tempo_inicio = in.readString();
        tempo_fim = in.readString();
        local = in.readString();
    }

    @Ignore
    public static final Creator<TrabalhoComAtividadeRelatorio> CREATOR = new Creator<TrabalhoComAtividadeRelatorio>() {

        @Override
        public TrabalhoComAtividadeRelatorio createFromParcel(Parcel in) {
            return new TrabalhoComAtividadeRelatorio(in);
        }

        @Override
        public TrabalhoComAtividadeRelatorio[] newArray(int size) {
            return new TrabalhoComAtividadeRelatorio[size];
        }
    };

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_trabalho);
        dest.writeInt(id_usuario);
        dest.writeString(nome_usuario);
        dest.writeInt(id_atividade);
        dest.writeString(atividade);
        dest.writeInt(id_relatorio);
        dest.writeString(relatorio);
        dest.writeString(tempo_inicio);
        dest.writeString(tempo_fim);
        dest.writeString(local);
    }

    public int getId_trabalho() {
        return id_trabalho;
    }

    public void setId_trabalho(int id_trabalho) {
        this.id_trabalho = id_trabalho;
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

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public int getId_relatorio() {
        return id_relatorio;
    }

    public void setId_relatorio(int id_relatorio) {
        this.id_relatorio = id_relatorio;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
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



}
