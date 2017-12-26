package com.inventyfy.architecture.ui.home.search.bindingentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeEntity {

    private String itemType;
    private String itemName;

    public TypeEntity(String itemType, String itemName) {
        this.itemType = itemType;
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return itemType;
    }

    public static List<TypeEntity> getTypesFromMedia(final String mediaType) {
        Map<String, List<TypeEntity>> allTypes = getListOfTypes();

        return allTypes.get(mediaType);
    }

    private static Map<String, List<TypeEntity>> getListOfTypes() {
        final Map<String, List<TypeEntity>> typeList = new HashMap<>();

        final List<TypeEntity> movieType = new ArrayList<>();
        movieType.add(new TypeEntity("movieArtist", "Movie Artist"));
        movieType.add(new TypeEntity("movie", "Movie"));
        typeList.put("movie", movieType);

        final List<TypeEntity> podcastType = new ArrayList<>();
        podcastType.add(new TypeEntity("podcastAuthor", "Podcast Author"));
        podcastType.add(new TypeEntity("podcast", "Podcast"));
        typeList.put("podcast", podcastType);

        final List<TypeEntity> softwareType = new ArrayList<>();
        softwareType.add(new TypeEntity("software", "Software"));
        softwareType.add(new TypeEntity("iPadSoftware", "iPad Software"));
        softwareType.add(new TypeEntity("macSoftware", "Mac Software"));
        typeList.put("software", softwareType);

        final List<TypeEntity> ebookType = new ArrayList<>();
        ebookType.add(new TypeEntity("ebook", "ebook"));
        typeList.put("ebook", ebookType);

        final List<TypeEntity> musicType = new ArrayList<>();
        musicType.add(new TypeEntity("musicArtist", "Music Artist"));
        musicType.add(new TypeEntity("musicTrack", "Music Track"));
        musicType.add(new TypeEntity("album", "Album"));
        musicType.add(new TypeEntity("musicVideo", "Music Video"));
        musicType.add(new TypeEntity("song", "Song"));
        typeList.put("music", musicType);

        return typeList;
    }
}
