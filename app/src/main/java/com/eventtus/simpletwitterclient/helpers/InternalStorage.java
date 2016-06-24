package com.eventtus.simpletwitterclient.helpers;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Bassem on 11/12/2015.
 */
public final class InternalStorage {
    public static String NEWS_SAVED_KEY = "NEWS_SAVED_KEY";
    public static String WIDGET_SAVED_KEY = "WIDGET_SAVED_KEY";

    private InternalStorage() {
    }

    public static void addTwitterFollowesWriteObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }
}