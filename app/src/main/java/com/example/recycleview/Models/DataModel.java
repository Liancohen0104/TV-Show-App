package com.example.recycleview.Models;

public class DataModel
{
    private String name;
    private String description;
    private int image;
    private int id_;

    public DataModel(String name, String description, int image, int id_)
    {
        this.name = name;
        this.description = description;
        this.image = image;
        this.id_ = id_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
