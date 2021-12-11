package com.example.pruebas2;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;



public class Usuario_pantalla extends Activity {

	
	 private ArrayList<String> placa = new ArrayList<String>();
	 private ListView userList;
	 private TextView et1, et2, Lunes, Martes, Miercoles, Jueves, Viernes;
	 private RadioGroup rb1;
	 private Button bt1;
	 private TableLayout tl1;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    
	    super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.pantalla_usuario);
	    
	    et1 = (TextView) findViewById(R.id.txtMensaje1);
	    et2 = (TextView) findViewById(R.id.txtMensaje2);
	    rb1 = (RadioGroup) findViewById(R.id.rbOpciones);
	    bt1 = (Button) findViewById(R.id.btnGuardarCambios);
	    tl1 = (TableLayout) findViewById(R.id.tabla1);
	    Lunes = (TextView) findViewById(R.id.Lunes);
	    Martes = (TextView) findViewById(R.id.Martes);
	    Miercoles = (TextView) findViewById(R.id.Miercoles);
	    Jueves = (TextView) findViewById(R.id.Jueves);
	    Viernes = (TextView) findViewById(R.id.Viernes);
	    

	    userList = (ListView) findViewById(R.id.listView1);
	    
	    final AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

	    userList.setOnItemClickListener(
	            new OnItemClickListener()
	            {

	                @Override
	                public void onItemClick(AdapterView<?> arg0, View view,
	                        int position, long id) {
	                	
	                	try{
	                	
	                    //Sacar placa, tipo y ultimo numero
	                		
	                	char ultimo = 0;
	                	int MsgEmergente = 0;
	                	
	                    Object o = userList.getItemAtPosition(position);
	                    String pen = o.toString();

	                    String delimitador = " ";
	                    String cadena = pen;
	                    String[] temp;
	                    int contador = 0;
	                    temp = cadena.split(delimitador);
	                     
	                    String placa = temp[2].trim();
	                    String tipo = temp[4].trim();

	                    if(tipo.length()==5){                    	
	                    	ultimo = placa.charAt(5);
	                    	contador = 1;
	                    }
                         if(tipo.length()==4){	
                        	 ultimo = placa.charAt(4);
                        	 contador = 2;
	                    } 
	                    
                       
	                     //traer desde la base de datos los dias de pico y placa 
	                     //segun el tipo del vehiculo seleccionado    
	                         
	                     String dia = "No funciono";   
	                         
	                   	 SQLiteDatabase bd = admin.getWritableDatabase();
	                   	                    	  
	                   	  if (contador == 1){  
	                  	  Cursor fila1 = bd.rawQuery("select diaCarro from picoyplaca where numero= " + ultimo, null);
	                  	  fila1.moveToFirst();
	                  	  dia = fila1.getString(0);
	                  	  fila1.close();
	                  	  }
	                  	  if(contador == 2){
	                  	  Cursor fila1 = bd.rawQuery("select diaMoto from picoyplaca where numero= " + ultimo, null); 
	                  	  fila1.moveToFirst();
	                  	  dia = fila1.getString(0);
	                  	  fila1.close();
	                  	  }
	                      
	                  	  Cursor fila2 = bd.rawQuery("select MsgEmergente from placaxdia where placa= '" + placa + "'", null); 
	                  	  fila2.moveToFirst();
	                  	  MsgEmergente = fila2.getInt(0);
	                  	  
	                  	  bd.close();
	                  	  
	                      //Toast.makeText(getApplicationContext(), placa + " " + tipo + " " + ultimo + " " + dia, Toast.LENGTH_LONG).show();
		                    
	                      //Mostrar menu de configuracion por vehiculo
	                  	  
	                  	  
	                  	  et1.setVisibility(View.VISIBLE);
	                  	  et2.setVisibility(View.VISIBLE);
	                  	  rb1.setVisibility(View.VISIBLE);
	                  	  bt1.setVisibility(View.VISIBLE);
	                  	  tl1.setVisibility(View.VISIBLE);

                     
	                  	  //Cambiar color al dia
	                  	  
		                  	if(dia.equals("Lunes")) { 
		                  		
		                  		Martes.setTextColor(Color.BLACK); 
		                  		Miercoles.setTextColor(Color.BLACK); 
		                  		Jueves.setTextColor(Color.BLACK); 
		                  		Viernes.setTextColor(Color.BLACK);
		               		
		                  		Lunes.setTextColor(Color.RED);              		  
		                  	  }
		                  	if(dia.equals("Martes")) {  
		                  		Lunes.setTextColor(Color.BLACK); 
		                  		Miercoles.setTextColor(Color.BLACK); 
		                  		Jueves.setTextColor(Color.BLACK); 
		                  		Viernes.setTextColor(Color.BLACK);
		                  		
		                  		Martes.setTextColor(Color.RED); 		                  
		                  	  }
		                  	if(dia.equals("Miercoles")) {
		                  		Martes.setTextColor(Color.BLACK); 
		                  		Lunes.setTextColor(Color.BLACK); 
		                  		Jueves.setTextColor(Color.BLACK); 
		                  		Viernes.setTextColor(Color.BLACK);
		                  		
		                  		Miercoles.setTextColor(Color.RED);              		  
		                  	  }
		                  	if(dia.equals("Jueves")){
		                  		Martes.setTextColor(Color.BLACK); 
		                  		Miercoles.setTextColor(Color.BLACK); 
		                  		Lunes.setTextColor(Color.BLACK); 
		                  		Viernes.setTextColor(Color.BLACK);
		                  		
		                  		Jueves.setTextColor(Color.RED);              		  
		                  	  }
		                  	if(dia.equals("Viernes")) { 
		                  		Martes.setTextColor(Color.BLACK); 
		                  		Miercoles.setTextColor(Color.BLACK); 
		                  		Jueves.setTextColor(Color.BLACK); 
		                  		Lunes.setTextColor(Color.BLACK);
		                  		
		                  		Viernes.setTextColor(Color.RED);              		  
		                  	  }
		                  	  
		                  	
		                  	if(MsgEmergente == 0){
		                  		
		                  		//Toast.makeText(getApplicationContext(), "No ha establecido la opcion de mensaje emergente para el vehiculo: " + placa , Toast.LENGTH_LONG).show();
		                  		
		                  	}
		                  	
	                  	  
		                  	//no ha establecido la opcion de mensaje emergente para el vehiculo xxx
		                  	
	                  }catch(Exception e){	 
	                   Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
	                  }
                                                           
	              }       
	         }   	            
	    );	    
	    
	 }
	 
	 
	 @Override
	 protected void onStart()
	 {
		 
		 
	  try{
		
		int contador = 1;
		placa.clear(); 
		
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		Cursor fila1 = bd.rawQuery("select placaVehiculo, tipoVehiculo from usuarios", null);
	
		 if (fila1.moveToFirst()) {
	            do {
	                placa.add(contador + ")." + " Placa: " + fila1.getString(0) + " Tipo: " + fila1.getString(1));
	                contador++;
	            } while (fila1.moveToNext());
	        }	        

	        fila1.close();
	   
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1, placa);
	        
	        userList.setAdapter(dataAdapter);
	        Helper.getListViewSize(userList);
 
	        
	   }catch(Exception e){
		
		   Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
		
	   }	 
		 
	   super.onStart();
	 }
	 
    public void clickBotonAgregar(View v) {
    	 
    	 try{
    		 
    		 Intent i = new Intent(this, AgregarVehiculo.class);
    	     startActivity(i);
    		 
    	      
    	     
    	     
    	 }catch(Exception e){
    		 
    		 
    	 }
     }
    
    public void clickGuardarCambios(View v) {
   	 
   	 try{
   	
	
   		 
   	  et1.setVisibility(View.INVISIBLE);
   	  et2.setVisibility(View.INVISIBLE);
   	  rb1.setVisibility(View.INVISIBLE);
   	  bt1.setVisibility(View.INVISIBLE);
   	  tl1.setVisibility(View.INVISIBLE);
   		
   	  
   	  //Toast.makeText(getApplicationContext(), "El vehiculo con placa: " + placa2 + ", ha sido configurado correctamente.", Toast.LENGTH_LONG).show();
   	  
   	  //guardar en una base de datos el cambio.
   	  
   	 }catch(Exception e){
   		 
   		 
   	 }
    }
    


  
  
    
	
}
