package br.com.mh3d.leitorqrcodeatividade.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "atividade_table", indices = {@Index(value = ("id_atividade"), unique = true)})
public class Atividades implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id_atividade")
    private int id_atividade;
    @ColumnInfo(name = "atividade")
    private String atividade;

    //Construtores

    public Atividades(){
    }

    @Ignore
    public Atividades(String Atividade){
        this.atividade = atividade;
    }

    @Ignore
    public Atividades(int id_atividade, String atividade){
        this.id_atividade = id_atividade;
        this.atividade = atividade;
    }

    //Getter and Setter

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


    // Parcelable
    @Ignore
    protected Atividades(Parcel in) {
        id_atividade = in.readInt();
        atividade = in.readString();
    }
    @Ignore
    public static final Creator<Atividades> CREATOR = new Creator<Atividades>() {
        @Override
        public Atividades createFromParcel(Parcel in) {
            return new Atividades(in);
        }

        @Override
        public Atividades[] newArray(int size) {
            return new Atividades[size];
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
        dest.writeInt(id_atividade);
        dest.writeString(atividade);
    }

}
