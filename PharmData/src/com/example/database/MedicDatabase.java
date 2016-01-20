package com.example.database;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicDatabase extends SQLiteOpenHelper {

	private static final int database_VERSION = 1;

	private static final String database_NAME = "Medicine_DB";
	private static final String table_MEDICINE = "medicine";

	private static final String medicine_ID = "id";
	private static final String medicine_NAME = "name";
	private static final String medicine_PRICE = "oPrice";
	private static final String medicine_PACKSIZE = "packSize";
	private static final String medicine_TYPE = "type";
	private static final String medicine_AVAIL = "available";

	private static final String[] COLUMNS = { medicine_ID, medicine_NAME,
			medicine_PRICE, medicine_PACKSIZE, medicine_TYPE, medicine_AVAIL };

	public MedicDatabase(Context context) {
		super(context, database_NAME, null, database_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_BOOK_TABLE = "CREATE TABLE " + table_MEDICINE + " ( "
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, "
				+ "oPrice TEXT, " + "packSize TEXT, " + "type TEXT, "
				+ "available TEXT )";
		db.execSQL(CREATE_BOOK_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS medicine");
		this.onCreate(db);

	}

	public void createMedicineRecord(Medicine medicine) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(medicine_ID, medicine.getMedicineId());
		values.put(medicine_NAME, medicine.getMedicineName());
		values.put(medicine_PRICE, medicine.getMedicinePrice());
		values.put(medicine_PACKSIZE, medicine.getMedicineSize());
		values.put(medicine_TYPE, medicine.getMedicineType());
		values.put(medicine_AVAIL, medicine.getMedAvailable());
		db.insert(table_MEDICINE, null, values);
		db.close();

	}

	public Medicine readMedicineRecord(int id) {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(table_MEDICINE, COLUMNS, " id = ?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Medicine medicine = new Medicine();
		medicine.setMedicineId(Integer.parseInt(cursor.getString(0)));
		medicine.setMedicineName(cursor.getString(1));
		medicine.setMedicinePrice(cursor.getString(2));
		medicine.setMedicineSize(cursor.getString(3));
		medicine.setMedicineType(cursor.getString(4));
		medicine.setMedAvailable(Boolean.parseBoolean(cursor.getString(5)));
		return medicine;
	}

	public ArrayList<String> getAllMedicines() {
		ArrayList<String> medicines = new ArrayList<String>();

		String query = "SELECT id FROM " + table_MEDICINE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		medicines = new ArrayList<String>();
		if (cursor.moveToFirst()) {
			do {
				medicines.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		return medicines;
	}

}
