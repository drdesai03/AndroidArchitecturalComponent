package com.inventyfy.architecture.helper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ResourcesResponse<T> {

    @NonNull
    public final StatusConstant status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public ResourcesResponse(@NonNull StatusConstant status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ResourcesResponse<T> success(@Nullable T data) {
        return new ResourcesResponse<>(StatusConstant.SUCCESS, data, null);
    }

    public static <T> ResourcesResponse<T> error(String msg, @Nullable T data) {
        return new ResourcesResponse<>(StatusConstant.ERROR, data, msg);
    }

    public static <T> ResourcesResponse<T> loading(@Nullable T data) {
        return new ResourcesResponse<>(StatusConstant.LOADING, data, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResourcesResponse<?> resource = (ResourcesResponse<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
