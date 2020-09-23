package com.example.android.miwok;

public class Word
{
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId;
    private int mAudioResourceId;
    public Boolean yesimage = false;

    public Word(String defaultTranslation, String miwokTranslaion, int audioResourceId){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslaion;
        mAudioResourceId=audioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslaion, int imageResourceId, int audioResourceId){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslaion;
        mImageResourceId=imageResourceId;
        mAudioResourceId=audioResourceId;
        yesimage=true;
    }


    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    public boolean hasimage(){
        if(yesimage==true)
            return true;
        else
            return false;
    }

}
