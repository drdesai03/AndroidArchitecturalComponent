package com.inventyfy.architecture.network.support;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import retrofit2.Response;

public class ApiResponse<R> {

    private static final String TAG = ApiResponse.class.getSimpleName();

    public final int code;
    @Nullable
    public final R body;
    @Nullable
    public final String errorMessage;

    public ApiResponse(final Throwable error) {
        code = 500;
        body = null;
        errorMessage = error.getMessage();
    }

    public ApiResponse(final Response<R> response) {
        code = response.code();
        if (isSuccess()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            try {
                message = response.errorBody().string();
            } catch (IOException ex) {
                Log.d(TAG, "Error Captured");
            }

            if (TextUtils.isEmpty(message)) {
                message = response.message();
            }

            errorMessage = message;
            body = null;
        }
    }

    private boolean isSuccess() {
        return code >= 200 && code < 300;
    }
}
