package com.example.pruebas2;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ContentValues;
//import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.view.Window;
//import android.view.WindowManager;
//import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
import android.widget.EditText;


public class AgregarVehiculo extends Activity {
	
	private EditText et1;
	private Spinner ListaOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.agregar_vehiculo);
        
        et1 = (EditText) findViewById(R.id.txtplaca);
        ListaOpciones = (Spinner) findViewById(R.id.spinner1);
           
    }


  
    public void AgregarVehiculo(View v) {
    	 
    	 
    	 try{
        
    	 String placa = et1.getText().toString();
    	 char ultimo = 0;
    	 int contador = 0;
    	 int contador2 = 0;
    	 String dia = "";
    	 int MsgEmergente = 0;
    	 String tipo = ListaOpciones.getSelectedItem().toString();

    	 if(placa.length()==0){
    		 
    	 Toast.makeText(this, "Placa incorrecta.", Toast.LENGTH_SHORT).show();
    	 return;	
    	 
    	 }else{
    		 
    		 
    	if(placa.length() != 6){
    		Toast.makeText(this, "La placa debe contener seis caracteres.", Toast.LENGTH_SHORT).show();
			 return;
    	}	 
    		 
 
    	 if(tipo.equals("Carro")){
    		 
    		 char uno = placa.charAt(0);
    		 char dos = placa.charAt(1);
    		 char tres = placa.charAt(2);
    		 char cuatro = placa.charAt(3);
    		 char cinco = placa.charAt(4);
    		 char seis = placa.charAt(5);
 

    		 if(!Character.isDigit(uno)){
    			 contador2++;
    		 }
    		 if(!Character.isDigit(dos)){
    			 contador2++;
    		 }
    		 if(!Character.isDigit(tres)){
    			 contador2++;
    		 }
    		 if(Character.isDigit(cuatro)){
    		   contador2++; 
    		 }
    		 if(Character.isDigit(cinco)){
    			 contador2++;
    		 }
    		 if(Character.isDigit(seis)){
    			 contador2++;
    		 }
    		 
    		 if(contador2 != 6){
    			 
    			 Toast.makeText(this, "Placa para vehiculo tipo Carro incorrecta", Toast.LENGTH_SHORT).show();
        		 contador2 = 0;
    			 return;
    		 }
    		 
    		 
    		 
    	 }	
    	 
    	 if(tipo.equals("Moto")){
    		 
    		 
    		 char uno = placa.charAt(0);
    		 char dos = placa.charAt(1);
    		 char tres = placa.charAt(2);
    		 char cuatro = placa.charAt(3);
    		 char cinco = placa.charAt(4);
    		 char seis = placa.charAt(5);
 

    		 if(!Character.isDigit(uno)){
    			 contador2++;
    		 }
    		 if(!Character.isDigit(dos)){
    			 contador2++;
    		 }
    		 if(!Character.isDigit(tres)){
    			 contador2++;
    		 }
    		 if(Character.isDigit(cuatro)){
    		   contador2++; 
    		 }
    		 if(Character.isDigit(cinco)){
    			 contador2++;
    		 }
    		 if(!Character.isDigit(seis)){
    			 contador2++;
    		 }
    		 
    		 if(contador2 != 6){
    			 
    			 Toast.makeText(this, "Placa para vehiculo tipo Moto incorrecta", Toast.LENGTH_SHORT).show();
        		 contador2 = 0;
    			 return;
    		 }
    		 
    	 }
    	 	 
    		 
    	 AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
    	 SQLiteDatabase bd = admin.getWritableDatabase();
    	 
    	 
    	 Cursor fila = bd.rawQuery("select COUNT(*) from usuarios where placaVehiculo='" + placa + "'", null);
    	 fila.moveToFirst();
    	 int cuenta = fila.getInt(0);
    	 fila.close();
    	     	   	 
    	 if(cuenta == 0){
    		 
    		 //guardar vehiculo en usuarios en bd
    		 
    		 ContentValues registro = new ContentValues();
        	 registro.put("placaVehiculo", placa);
        	 registro.put("tipoVehiculo", tipo);
        	 bd.insert("usuarios", null, registro);
        	 
        	 //guardar vehiculo en placaxdia

        	 
        	 if(tipo.length()==5){                    	
             	ultimo = placa.charAt(5);
             	contador = 1;
             }
              if(tipo.length()==4){	
             	ultimo = placa.charAt(4);
             	contador = 2;
             } 
        	 
              
			  if (contador == 1){  
			  	  Cursor fila1 = bd.rawQuery("select diaCarro from picoyplaca where numero= " + ultimo, null);
			      fila1.moveToFirst();
			      dia = fila1.getString(0);
			  }
			  if(contador == 2){
			      Cursor fila1 = bd.rawQuery("select diaMoto from picoyplaca where numero= " + ultimo, null); 
			      fila1.moveToFirst();
			      dia = fila1.getString(0);
			  }
 
			 String ultimoNum = "" + ultimo;
			  
             ContentValues registro2 = new ContentValues();
         	 registro2.put("placa", placa);
         	 registro2.put("ultNumero", ultimoNum);
         	 registro2.put("diaPicoyplaca", dia);
         	 registro2.put("MsgEmergente", MsgEmergente);
         	 bd.insert("placaxdia", null, registro2);
              
        	 bd.close();
        	 
        	 Toast.makeText(this, "Datos guardatos correctamente.", Toast.LENGTH_SHORT).show(); 
        	 
        	 //crear alarma, taer el dia del vihucuo
        	 
        	 
        	 
        	 
        	Intent i = new Intent(this, MainActivity.class);
          	startActivity(i);
          	finish();
    		 
    	 }else{
    		 
    		 Toast.makeText(this, "Esa placa ya existe.", Toast.LENGTH_SHORT).show();
    		 return;
    	 }

    	 }
    	 
    	 
    	 } catch (Exception e) {
 			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();		
 		}
    	 
 
     }
    
     
     public void CerrarVentana(View v) {
    	 
    	Intent i = new Intent(this, MainActivity.class);
     	startActivity(i);
     	finish();
     	
     	
     }
     
     public void EliminarVehiculo(View v) {
    	 
    	 Intent i = new Intent(this, Eliminar_vehiculo.class);
     	 startActivity(i);
     	
     	
     }
    
    
    
    
}












