package br.com.mh3d.leitorqrcodeatividade.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;

public class TrabalhoComAtividadeRelatorio implements Parcelable {

    @Embedded
    private Trabalho trabalho;
    @Embedded
    private Atividades atividades;
    @Embedded
    private Relatorio relatorio;

    public TrabalhoComAtividadeRelatorio(){

    }

    public Trabalho getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }

    public Atividades getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividades atividades) {
        this.atividades = atividades;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    public static Creator<TrabalhoComAtividadeRelatorio> getCREATOR() {
        return CREATOR;
    }

    protected TrabalhoComAtividadeRelatorio(Parcel in) {
        trabalho = in.readParcelable(Trabalho.class.getClassLoader());
        atividades = in.readParcelable(Atividades.class.getClassLoader());
        relatorio = in.readParcelable(Relatorio.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(trabalho, flags);
        dest.writeParcelable(atividades, flags);
        dest.writeParcelable(relatorio, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
}
