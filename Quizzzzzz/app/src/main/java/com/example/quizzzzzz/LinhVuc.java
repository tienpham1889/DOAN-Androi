package com.example.quizzzzzz;

import android.os.Parcel;
import android.os.Parcelable;

public class LinhVuc implements Parcelable {
    private String tenLinhVuc;
    private Integer ID;

    protected LinhVuc(Parcel in) {
        tenLinhVuc = in.readString();
        if (in.readByte() == 0) {
            ID = null;
        } else {
            ID = in.readInt();
        }
    }

    public static final Creator<LinhVuc> CREATOR = new Creator<LinhVuc>() {
        @Override
        public LinhVuc createFromParcel(Parcel in) {
            return new LinhVuc(in);
        }

        @Override
        public LinhVuc[] newArray(int size) {
            return new LinhVuc[size];
        }
    };

    public String getTenLinhVuc() {
        return tenLinhVuc;
    }

    public void setTenLinhVuc(String tenLinhVuc) {
        this.tenLinhVuc = tenLinhVuc;
    }
    public LinhVuc(String ten, Integer id){
        this.ID=id;
        this.tenLinhVuc=ten;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tenLinhVuc);
        if (ID == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(ID);
        }
    }
}
