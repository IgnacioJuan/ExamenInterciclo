package com.juanguachi.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.juanguachi.pizzeria.model.Pizza;

public class CrudPizzad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_pizzad);

        Button btn_guardar=(Button) findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(guardarListener);

        Button btn_listar=(Button) findViewById(R.id.btn_listar);
        btn_listar.setOnClickListener(listarListener);

        Button btn_editar=(Button) findViewById(R.id.btn_editar);
        btn_editar.setOnClickListener(editarListener);

        Button btn_eliminar=(Button) findViewById(R.id.btn_eliminar);
        btn_eliminar.setOnClickListener(eliminarListener);
    }

    View.OnClickListener guardarListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText txt_codigo=(EditText) findViewById(R.id.txt_codigo);
            EditText txt_nombre=(EditText) findViewById(R.id.txt_nombre);
            EditText txt_tamaño=(EditText) findViewById(R.id.txt_tamaño);
            EditText txt_ingredientes=(EditText) findViewById(R.id.txt_ingredientes);
            EditText txt_costo=(EditText) findViewById(R.id.txt_costo);
            EditText txt_pvp=(EditText) findViewById(R.id.txt_pvp);
            EditText txt_promocion=(EditText) findViewById(R.id.txt_promocion);

            Pizza pizza=new Pizza();

            pizza.setCodigo(txt_codigo.getText().toString());
            pizza.setCosto(txt_costo.getText().toString());
            pizza.setIngredientes(txt_ingredientes.getText().toString());
            pizza.setNombre(txt_nombre.getText().toString());
            pizza.setPromocion(txt_promocion.getText().toString());
            pizza.setPvp(txt_pvp.getText().toString());
            pizza.setTamaño(txt_tamaño.getText().toString());

            if(txt_codigo.getText().toString().isEmpty() || txt_nombre.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Complete todos los campos", Toast.LENGTH_LONG).show();
            }else {
                pizza.GuardarPizza(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Pizza creada", Toast.LENGTH_LONG).show();
                listarPersonas();
            }
        }
    };
    View.OnClickListener listarListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listarPersonas();
        }
    };

    public  void listarPersonas(){
        ListView listView=(ListView) findViewById(R.id.listPizzas);
        Cursor cursor=Pizza.ListaPizzas(getApplicationContext());
        //codigo,nombre,tamaño,ingredientes,costo,pvp,promocion
        String[] desde= new String[]{"codigo","nombre","tamaño","ingredientes","costo","pvp","promocion"};
        int[] hasta = new int[]{R.id.txtCODIGO, R.id.txtNOMBRE, R.id.txtTAMAÑO, R.id.txtINGREDIENTES, R.id.txtCOSTO, R.id.txtPVP, R.id.txtPROMOCION};
        CursorAdapter cursorAdapter= new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.detalle_pizza,
                cursor,
                desde,
                hasta,0
        );
        listView.setAdapter(cursorAdapter);
        Toast.makeText(getApplicationContext(),"Listando", Toast.LENGTH_LONG).show();
    }

    View.OnClickListener editarListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText txt_codigo=(EditText) findViewById(R.id.txt_codigo);
            EditText txt_nombre=(EditText) findViewById(R.id.txt_nombre);
            EditText txt_tamaño=(EditText) findViewById(R.id.txt_tamaño);
            EditText txt_ingredientes=(EditText) findViewById(R.id.txt_ingredientes);
            EditText txt_costo=(EditText) findViewById(R.id.txt_costo);
            EditText txt_pvp=(EditText) findViewById(R.id.txt_pvp);
            EditText txt_promocion=(EditText) findViewById(R.id.txt_promocion);

            Pizza pizza=new Pizza();

            pizza.setCodigo(txt_codigo.getText().toString());
            pizza.setCosto(txt_costo.getText().toString());
            pizza.setIngredientes(txt_ingredientes.getText().toString());
            pizza.setNombre(txt_nombre.getText().toString());
            pizza.setPromocion(txt_promocion.getText().toString());
            pizza.setPvp(txt_pvp.getText().toString());
            pizza.setTamaño(txt_tamaño.getText().toString());

            if(txt_codigo.getText().toString().isEmpty() || txt_nombre.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Complete todos los campos", Toast.LENGTH_LONG).show();
            }else {
                pizza.EditarPizza(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Pizza editada", Toast.LENGTH_LONG).show();
                listarPersonas();
            }
        }
    };

    View.OnClickListener eliminarListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText txt_codigo=(EditText) findViewById(R.id.txt_codigo);

            Pizza pizza=new Pizza();
            pizza.setCodigo(txt_codigo.getText().toString());

            if(txt_codigo.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Complete el campo de id", Toast.LENGTH_LONG).show();
            }else {
                pizza.EliminarPizza(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Pizza eliminada", Toast.LENGTH_LONG).show();
                listarPersonas();
            }
        }
    };
}