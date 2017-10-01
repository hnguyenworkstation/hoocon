package com.hoocons.hooconsandroid.Configurations;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.AppGlideModule;

import java.lang.annotation.Annotation;

/**
 * Created by hungnguyen on 9/30/17.
 */
@GlideModule
public class GlideConfiguration extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }
}
