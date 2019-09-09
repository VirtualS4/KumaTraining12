package com.example.rona.kumatraining12;

import android.os.Parcel;
import android.os.Parcelable;

public class OldBear implements Parcelable {
    int id;
    String tanggal,nama,kelamin,alamat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.tanggal);
        dest.writeString(this.nama);
        dest.writeString(this.kelamin);
        dest.writeString(this.alamat);
    }

    public OldBear() {
    }

    protected OldBear(Parcel in) {
        this.id = in.readInt();
        this.tanggal = in.readString();
        this.nama = in.readString();
        this.kelamin = in.readString();
        this.alamat = in.readString();
    }

    public static final Parcelable.Creator<OldBear> CREATOR = new Parcelable.Creator<OldBear>() {
        @Override
        public OldBear createFromParcel(Parcel source) {
            return new OldBear(source);
        }

        @Override
        public OldBear[] newArray(int size) {
            return new OldBear[size];
        }
    };
}
