package com.juanguachi.pizzeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button mibotoningresar;
    Button mibotonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mibotoningresar=findViewById(R.id.btn_loginIngresar);//Enlace con el elemento
        mibotonRegistrar=findViewById(R.id.btn_loginRegistrar);//Enlace con el elemento
        iniciaControl();
    }
    private void iniciaControl(){
        mibotoningresar.setOnClickListener(l->autenticarIngreso());
        mibotonRegistrar.setOnClickListener(l->autenticarregistro());
    }
    private void autenticarregistro(){
        TextView txtUsurio=findViewById(R.id.txt_loginUsuario);
        TextView txtContraseña=findViewById(R.id.txt_loginContraseña);

        if(!txtUsurio.getText().toString().isEmpty() && !txtContraseña.getText().toString().isEmpty()){
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(txtUsurio.getText().toString(), txtContraseña.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),CrudPizzad.class);
                                startActivity(intent);
                            }else{
                                alerta();
                            }
                        }
                    });
        }
    }
    private void autenticarIngreso(){
        TextView txtUsurio=findViewById(R.id.txt_loginUsuario);
        TextView txtContraseña=findViewById(R.id.txt_loginContraseña);

        if(!txtUsurio.getText().toString().isEmpty() && !txtContraseña.getText().toString().isEmpty()){
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(txtUsurio.getText().toString(), txtContraseña.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(),CrudPizzad.class);
                                startActivity(intent);
                            }else{
                                alerta();
                            }
                        }
                    });
        }
    }
    private void alerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Ha ocurrido un error");
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}