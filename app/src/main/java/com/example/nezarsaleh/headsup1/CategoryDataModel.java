package com.example.nezarsaleh.headsup1;

public class CategoryDataModel {

    int ID,catImageResource;
    String catName;
    int fav;

    public int getCus() {
        return cus;
    }

    public void setCus(int cus) {
        this.cus = cus;
    }

    int cus;

    public CategoryDataModel(long ID, int catImageResource, String catName) {
        this.ID = (int) ID;
        this.catImageResource = catImageResource;
        this.catName = catName;
    }

    public CategoryDataModel() {
    }

    public CategoryDataModel(int ID,int catImageResource, String catName,int fav,int cus) {
        this.ID = ID;
        this.catImageResource = catImageResource;
        this.catName = catName;
        this.fav = fav;
        this.cus = cus;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getCatImageResource() {
        return catImageResource;
    }

    public void setCatImageResource(int catImageResource) {
        this.catImageResource = catImageResource;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}