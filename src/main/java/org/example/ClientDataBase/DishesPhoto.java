package org.example.ClientDataBase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.util.Arrays;

public class DishesPhoto {
    final private String bucketName = "dishesphoto";
    public Storage storage = StorageOptions.getDefaultInstance().getService();
    public String getDishesPhotoFromGoogleCloud(String fileName){
        Blob blob = storage.get(bucketName, fileName);
        return Arrays.toString(blob.getContent());
    }
}

