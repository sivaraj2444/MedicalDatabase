package com.example.database;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

public class Medicine {

	private int medicineId;
	private String medicineName;
	private String medicinePrice;
	private String medicineType;
	private String medicineSize;
	private boolean isMedAvailable;

	public Medicine() {
	}

	public Medicine(JSONObject object) {
		super();

		try {
			this.setMedicineId(object.getInt("id"));
			this.medicineName = object.getString("name");
			this.medicinePrice = object.getString("oPrice");
			this.medicineType = object.getString("type");
			this.medicineSize = object.getString("packSize");
			this.isMedAvailable = object.getBoolean("available");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}


	@Override
	public String toString() {
		return "Book [" + medicineId + "," + medicineName + "," + medicinePrice
				+ "," + medicineType + "," + medicineSize + ","
				+ isMedAvailable + "]";
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(String medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public String getMedicineSize() {
		return medicineSize;
	}

	public void setMedicineSize(String medicineSize) {
		this.medicineSize = medicineSize;
	}

	public boolean getMedAvailable() {
		return isMedAvailable;
	}

	public void setMedAvailable(boolean isMedAvailable) {
		this.isMedAvailable = isMedAvailable;
	}
}