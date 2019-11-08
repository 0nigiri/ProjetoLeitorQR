package br.com.mh3d.leitorqrcodeatividade.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "relatorio_table", indices = {@Index(value = ("id_relatorio"), unique = true)})
public class Relatorio implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id_relatorio")
    private int id_relatorio;

    @ColumnInfo(name = "relatorio")
    private String relatorio;

    //Construtores

    public Relatorio(){
    }

    @Ignore
    public Relatorio(String relatorio){
        this.relatorio = relatorio;
    }

    @Ignore
    public Relatorio(int id_relatorio, String relatorio){
        this.id_relatorio = id_relatorio;
        this.relatorio = relatorio;
    }

    // getter and setters



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



    //Parcelable

    @Ignore
    protected Relatorio(Parcel in) {
        id_relatorio = in.readInt();
        relatorio = in.readString();
    }

    @Ignore
    public static final Creator<Relatorio> CREATOR = new Creator<Relatorio>() {
        @Override
        public Relatorio createFromParcel(Parcel in) {
            return new Relatorio(in);
        }

        @Override
        public Relatorio[] newArray(int size) {
            return new Relatorio[size];
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
        dest.writeInt(id_relatorio);
        dest.writeString(relatorio);
    }
}
