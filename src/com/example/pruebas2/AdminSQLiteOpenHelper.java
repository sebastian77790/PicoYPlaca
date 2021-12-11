package com.example.pruebas2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

	public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
		super(context, nombre, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	db.execSQL("create table usuarios(placaVehiculo text primary key, tipoVehiculo text )");
	db.execSQL("create table configuraciones(indicador text primary key, descripcion text )");
	db.execSQL("create table picoyplaca(numero int primary key, diaCarro text, diaMoto text )");
	db.execSQL("create table placaxdia(placa int primary key, ultNumero int, diaPicoyplaca text, MsgEmergente int)");

	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (1, 'Viernes','Viernes')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (2, 'Lunes','Lunes')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (3, 'Miercoles','Lunes')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (4, 'Jueves','Martes')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (5, 'Lunes','Martes')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (6, 'Martes','Miercoles')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (7, 'Jueves','Miercoles')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (8, 'Viernes','Jueves')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (9, 'Martes','Jueves')");
	db.execSQL("insert into picoyplaca (numero, diaCarro, diaMoto) values (0, 'Miercoles','Viernes')");
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
		db.execSQL("drop table if exists usuarios");
		db.execSQL("create table usuarios(placaVehiculo text primary key, tipoVehiculo text )");
		
		db.execSQL("drop table if exists configuraciones");
		db.execSQL("create table configuraciones(indicador text primary key, descripcion text )");
		
		db.execSQL("drop table if exists picoyplaca");
		db.execSQL("create table picoyplaca(numero int primary key, diaCarro text, diaMoto text )");
		
		db.execSQL("drop table if exists placaxdia");
		db.execSQL("create table placaxdia(placa int primary key, ultNumero int, diaPicoyplaca text, MsgEmergente int)");
	}	
}

