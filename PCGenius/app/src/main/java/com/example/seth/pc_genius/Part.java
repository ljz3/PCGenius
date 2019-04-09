package com.example.seth.pc_genius;

public class Part {

    private String mName;
    private String mDescription;
    private double mPrice;
    private int mImageResourceId;

    public Part(String name, String description, double price, int image) {
        mName = name;
        mDescription = description;
        mPrice = price;
        setmImageResourceId(image);
    }
    public Part(String name,int image){
        mName = name;
        mDescription = "";
        mPrice = 0.00;
        setmImageResourceId(image);
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public void setmImageResourceId(int mImageResourceId) {
        if (mImageResourceId != -1)
            this.mImageResourceId = mImageResourceId;
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
