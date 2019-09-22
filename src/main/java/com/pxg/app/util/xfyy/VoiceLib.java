package com.pxg.app.util.xfyy;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface VoiceLib extends Library {
    int MSPLogin(String user, String pwd, String params);

    //    PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
    VoiceLib instance = (VoiceLib) Native.loadLibrary("D:\\javademo\\APP\\src\\main\\resources\\xfyy\\msc_x64.dll", VoiceLib.class);
}
