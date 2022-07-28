package com.juanguachi.pizzeria.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PizzeriaSQLHelper extends SQLiteOpenHelper {
    private static final String BASE="pizzeriadb.db";
    private Context mocontexto;

    public PizzeriaSQLHelper(@Nullable Context context) {
        super(context, BASE, null, 3);
        mocontexto=context;
        File rutaArchivo = mocontexto.getDatabasePath(BASE);
        if(!existeBase(rutaArchivo.getAbsolutePath())) {
            copiarBD(rutaArchivo);
        }
    }

    private void copiarBD(File rutaArchivo){
        try {
            InputStream inputStream=mocontexto.getAssets().open(BASE);
            OutputStream outputStream = new FileOutputStream(rutaArchivo);
            byte[] buffer=new byte[1024];
            int largo;
            while ((largo=inputStream.read(buffer))>0){
                outputStream.write(buffer,0,largo);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean existeBase(String ruta){
        SQLiteDatabase siDB=null;
        siDB=SQLiteDatabase.openDatabase(ruta,null,SQLiteDatabase.OPEN_READONLY);
        if (siDB != null) {
            siDB.close();
            return true;
        }
        return false;

    };
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
