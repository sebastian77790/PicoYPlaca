package com.example.pruebas2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;



public class MainActivity extends Activity {

	private TextView et1, et2;
	private Button bt1;
	 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main);
    
    et1 = (TextView) findViewById(R.id.txtresul);
    et2 = (TextView) findViewById(R.id.txt1);
    bt1 = (Button) findViewById(R.id.btnContinuar);
    
 
    }

    
    @Override
    protected void onStart()
    {   
    	
    	try {
			
		String Indicador = "1";
		Integer Cuenta4 = -1;
		
    		
    	
		 AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
		 SQLiteDatabase bd = admin.getWritableDatabase();
		 
		 
		 Cursor fila3 = bd.rawQuery("select COUNT(*) from configuraciones where indicador='" + Indicador + "'", null);
		 fila3.moveToFirst();
		 Cuenta4 = fila3.getInt(0);
		 fila3.close();
		 
		
		 //Toast.makeText(this, "Indicador " + Cuenta4, Toast.LENGTH_SHORT).show();
		 
		 
		 if(Cuenta4 == 0){

		 Cursor fila = bd.rawQuery("select COUNT(*) from usuarios", null);
		 fila.moveToFirst();
		 int cuenta = fila.getInt(0);
		 fila.close();
		 
		 if(cuenta == 0){
			 
			 //Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
			 bd.close();
			 
		 }else{
			 		
			 et2.setVisibility(View.VISIBLE);
			 bt1.setVisibility(View.VISIBLE);
			 //Toast.makeText(this, "Hay al menos 1 dato", Toast.LENGTH_SHORT).show();			 
			 Cursor fila2 = bd.rawQuery("select placaVehiculo, tipoVehiculo from usuarios", null);
			 et1.setText("");
			 
			 if (fila2.moveToFirst()) {
				 
				 do {
			         String cod = fila2.getString(0);
			         String nom = fila2.getString(1);
			          
			         et1.append(" " + cod + " - " + nom + "\n");
			          
				 }while(fila2.moveToNext());
				 
			 }
			 
		 }
    	
		 bd.close();
		 
           }else{
			 
			 Intent i = new Intent(this, Usuario_pantalla.class);
	     	 startActivity(i);
	    	 
	     	 finish();
			 
		     }
		 
    		} catch (Exception e) {
    			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();		
    		}
    	
        super.onStart();
    }
    
    

    public void clickBotonAgregar(View v) {
        
        //startActivity(new Intent(this, AgregarVehiculo.class));
        //overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    	
    	Intent i = new Intent(this, AgregarVehiculo.class);
    	startActivity(i);
        
    }
    
    
	 public void clickBotonContinuar(View v) {
	         
		 
		 try{
		
		  
			 String Indicador = "1";
			 String Descripcion = "usuario";
			 
			 AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
		     SQLiteDatabase bd = admin.getWritableDatabase();
				 
		     ContentValues registro = new ContentValues();
	    	 registro.put("indicador", Indicador);
	    	 registro.put("descripcion", Descripcion);
	    	 bd.insert("configuraciones", null, registro);
			
	    	 bd.close();
        
	    	 Intent i = new Intent(this, Usuario_pantalla.class);
	     	 startActivity(i);
	    	 
	     	 finish();
	    	 
    	 
		 }catch (Exception e){
			 
			 Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
			 
		 }
		 
		 
	       
	 }
	 
	 
  
}












