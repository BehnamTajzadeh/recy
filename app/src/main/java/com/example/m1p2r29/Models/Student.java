package com.example.m1p2r29.Models;

public class Student {

    private String Id;
    private String Identifier;
    private String Name;
    private String Field;
    private byte[] photo;

    public Student(String id, String identifier, String name, String field, byte[] photo) {
        setId(id);
        setIdentifier(identifier);
        setName(name);
        setField(field);
        setPhoto(photo);
    }

    public Student() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
