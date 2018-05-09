package com.example.yak.si_kk2;

import android.widget.ImageView;

/**
 * Created by Yak on 07/05/2018.
 */

public class PostFeedKomunitas {
    private int postID;
    private int ivResID;
    private String postJudul;
    private String postDescription;
    private int biaya;
    private int rate;

    public PostFeedKomunitas(int postID, int ivResID, String postJudul, String postDescription, int biaya, int rate) {
        this.postID = postID;
        this.ivResID = ivResID;
        this.postJudul = postJudul;
        this.postDescription = postDescription;
        this.biaya = biaya;
        this.rate = rate;
    }


    public int getPostID() {
        return postID;
    }

    public int getIvResID() {
        return ivResID;
    }

    public String getPostJudul() {
        return postJudul;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public int getBiaya() {
        return biaya;
    }

    public int getRate() {
        return rate;
    }

}
