package com.facepp.library.mvp.model.transaction.compress;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public interface Video2JpegContract {
    interface View{}

    interface Presenter{
        String save2Jpeg(byte[] imgData, String fileName,int width, int height);

    }
}
