package com.example.quizzzzzz;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CauHoiLoader extends AsyncTaskLoader<String> {
    private int id;
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public CauHoiLoader(@NonNull Context context,int id_linhvuc) {
        super(context);
        this.id = id_linhvuc;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getJSONData("cau-hoi?linhvuc="+id,"GET");
    }
}
