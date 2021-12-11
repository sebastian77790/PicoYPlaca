package com.example.pruebas2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class Eliminar_vehiculo extends Activity {

	private EditText et1;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.eliminar_vehiculo);
    
    et1 = (EditText) findViewById(R.id.txtplaca);
    
	}
	
	public void CerrarVentana(View v) {
   	 
    	Intent i = new Intent(this, AgregarVehiculo.class);
     	startActivity(i);
     	finish();
     	
     }
	
	public void EliminarV(View v) {
   	 
		try{
			
		String placaTexto = et1.getText().toString();
		
		if(placaTexto.length()==0){
   		 
	    Toast.makeText(this, "Placa incorrecta.", Toast.LENGTH_SHORT).show();
	    return;
		
		}else{
		
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();      
        String placa = et1.getText().toString();
        int cant = bd.delete("usuarios", "placaVehiculo='" + placa + "'", null);
        int cant2 = bd.delete("placaxdia", "placa='" + placa + "'", null);
        bd.close();
        
        if (cant == 1){
            Toast.makeText(this, "Vehiculo borrado correctamente.", Toast.LENGTH_SHORT).show();
            et1.setText("");
        }
        else{
            Toast.makeText(this, "El vehiculo con la placa: " + placa + " no existe.", Toast.LENGTH_SHORT).show();
        }
        
		}
        
		}catch(Exception e){
			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
        
     }
	
	
}
