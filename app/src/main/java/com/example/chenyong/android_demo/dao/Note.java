package com.example.chenyong.android_demo.dao;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

/**
 * Created by focus on 17/1/5.
 */
@Entity(indexes = {
        @Index(value = "text, date DESC", unique = true)
})
public class Note {
    @Id
    private Long id;

    @NotNull
    private String text;
    private Date date;
    private String comment;
    @Convert(converter = NoteTypeConverter.class, columnType = String.class)
    private NoteType type;

    @Generated(hash = 1891581733)
    public Note(Long id, @NotNull String text, Date date, String comment,
                NoteType type) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.comment = comment;
        this.type = type;
    }

    public Note(Long id, @NotNull String text, Date date, String comment) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.comment = comment;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public NoteType getType() {
        return this.type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }
}
