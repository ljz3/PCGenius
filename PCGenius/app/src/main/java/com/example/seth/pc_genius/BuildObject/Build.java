package com.example.seth.pc_genius.BuildObject;

import com.example.seth.pc_genius.PartObject.Part;

import java.util.ArrayList;

public class Build {
    private String mName;
    private String mDescription;
    private double mPrice;
    private ArrayList<Part> mParts;

    public Build(String name, String description, double price, ArrayList<Part> parts) {
        mName = name;
        mDescription = description;
        mPrice = price;
        mParts = parts;
    }

    public Build(String name, int image) {
        mName = name;
        mDescription = "";
        mPrice = 0.00;
    }

    public ArrayList<Part> getmParts() {
        return mParts;
    }

    public void setmParts(ArrayList<Part> parts) {
        mParts = parts;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

}
