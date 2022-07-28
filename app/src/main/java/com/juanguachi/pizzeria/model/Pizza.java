package com.juanguachi.pizzeria.model;

import android.content.Context;
import android.database.Cursor;

public class Pizza {
    private String codigo;
    private String nombre;
    private String tamaño;
    private String ingredientes;
    private String costo;
    private String pvp;
    private String promocion;

    public void GuardarPizza(Context context){
        PizzeriaSQLHelper pizzeriaSQLHelper= new PizzeriaSQLHelper(context);
        String sql;
        sql="INSERT INTO pizza (codigo,nombre,tamaño,ingredientes,costo,pvp,promocion)" +
                " VALUES ('" + getCodigo() + "','"+getNombre()+"','"+getTamaño()+"','"+getIngredientes()+"','"+getCosto()+"','"+getPvp()+"','"+getPromocion()+"')";
        pizzeriaSQLHelper.getWritableDatabase().execSQL(sql);
    }
    public static Cursor ListaPizzas(Context context){
        PizzeriaSQLHelper pizzeriaSQLHelper= new PizzeriaSQLHelper(context);
        String sql;
        sql="SELECT _rowid_ as _id, * FROM pizza";
        return pizzeriaSQLHelper.getReadableDatabase().rawQuery(sql,null);
    }


    public void EditarPizza(Context context){
        PizzeriaSQLHelper pizzeriaSQLHelper= new PizzeriaSQLHelper(context);
        String sql;
        sql="Update pizza set nombre='" + getNombre()+"',tamaño='"+getTamaño()+"',ingredientes='"+getIngredientes()+"',costo='"+getCosto()+"',pvp='"+getPvp()+"',promocion='"+getPromocion()+"' where codigo='"+getCodigo()+"'";
        pizzeriaSQLHelper.getWritableDatabase().execSQL(sql);
    }
    public void EliminarPizza(Context context){
        PizzeriaSQLHelper pizzeriaSQLHelper= new PizzeriaSQLHelper(context);
        String sql;
        sql="DELETE FROM pizza where codigo='" +getCodigo()+ "'";
        pizzeriaSQLHelper.getWritableDatabase().execSQL(sql);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getPvp() {
        return pvp;
    }

    public void setPvp(String pvp) {
        this.pvp = pvp;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }
}
