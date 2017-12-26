package com.inventyfy.architecture.ui.home.search.bindingentity;

import java.util.ArrayList;
import java.util.List;

public class MediaEntity {

    private String mediaType;
    private String mediaName;

    public MediaEntity(final String mediaType, final String mediaName) {
        this.mediaType = mediaType;
        this.mediaName = mediaName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getMediaName() {
        return mediaName;
    }

    @Override
    public String toString() {
        return mediaType;
    }

    public static List<MediaEntity> getAllMediaType() {
        final List<MediaEntity> mediaEntities = new ArrayList<>();
        mediaEntities.add(new MediaEntity("all", "All"));
        mediaEntities.add(new MediaEntity("movie", "Movie"));
        mediaEntities.add(new MediaEntity("podcast", "Podcast"));
        mediaEntities.add(new MediaEntity("music", "Music"));
        mediaEntities.add(new MediaEntity("software", "Software"));
        mediaEntities.add(new MediaEntity("ebook", "e-book"));
        return mediaEntities;
    }
}
