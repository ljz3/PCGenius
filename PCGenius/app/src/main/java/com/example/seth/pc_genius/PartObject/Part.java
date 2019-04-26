package com.example.seth.pc_genius.PartObject;

public class Part {

    private String mName;
    private String mDescription;
    private double mPrice;
    private int mImageResourceId;


    private String mType;
    private String mNum;
    private String mBrand;
    private String mModel;
    private int mRank;
    private int mBenchmark;
    private int mSamples;
    private String mURL;

    public Part(String name, String description, double price, int image) {
        mName = name;
        mDescription = description;
        mPrice = price;
        setmImageResourceId(image);
    }

    public Part(String name, int image) {
        mName = name;
        mDescription = "";
        mPrice = 0.00;
        setmImageResourceId(image);
    }

    public Part(String type, String num, String brand, String model, String rank, String benchmark, String samples, String URL) {


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

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmNum() {
        return mNum;
    }

    public void setmNum(String mNum) {
        this.mNum = mNum;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmModel() {
        return mModel;
    }

    public void setmModel(String mModel) {
        this.mModel = mModel;
    }

    public int getmRank() {
        return mRank;
    }

    public void setmRank(int mRank) {
        this.mRank = mRank;
    }

    public int getmBenchmark() {
        return mBenchmark;
    }

    public void setmBenchmark(int mBenchmark) {
        this.mBenchmark = mBenchmark;
    }

    public int getmSamples() {
        return mSamples;
    }

    public void setmSamples(int mSamples) {
        this.mSamples = mSamples;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }
}
