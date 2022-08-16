package com.noob.notesv2;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Note implements Parcelable {
    //TODO: Создать коллекцию

    public static final Random random = new Random();
    private static Note[] notes;
    private static int counter;

    private int id;



    private String title;
    private String description;
    private LocalDateTime creationDate;

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public static Note[] getNotes() {
        return notes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    {
        id = ++counter;
    }

    static {
        notes = new Note[10];
        for (int i = 0; i< notes.length; i++) {
            notes[i] = Note.getNote(i);
        }
    }

    public Note(String title, String description, LocalDateTime creationDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("DefaultLocale")
    public static Note getNote(int index) {
        String title = String.format("Заметка %d", index);
        String description  = String.format("Описание заметки %d", index);
        LocalDateTime creationDate = LocalDateTime.now().plusDays(-random.nextInt(5));
        return new Note(title, description, creationDate);
    }

    //вспомогательный конструктор---------------------------
    protected Note(Parcel parcel){
        id = parcel.readInt();
        title = parcel.readString();
        description = parcel.readString();
        creationDate = (LocalDateTime) parcel.readSerializable();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getTitle());
        parcel.writeString(getDescription());
        parcel.writeSerializable(getCreationDate());


    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }
        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
