package com.example.pharmdata;

import java.util.ArrayList;

import com.example.database.MedicDatabase;
import com.example.database.Medicine;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MedicDataActivity extends FragmentActivity {

	ViewPager viewpager;
	MedicDatabase mdb;
	ArrayList<String> mList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicine_page);
		mdb = new MedicDatabase(this);
		mList = mdb.getAllMedicines();
		viewpager = (ViewPager) findViewById(R.id.medicPager);
		viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
	}

	class ViewPagerAdapter extends FragmentPagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			return new SinglePageFragment(mList.get(position));
		}

		@Override
		public int getCount() {
			return mList.size();
		}
	}
	
	public class SinglePageFragment extends Fragment {
		int position = 0;
		public SinglePageFragment(String string) {
			position = Integer.parseInt(string);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.rowview_single_medicine, container, false);
			Medicine med = mdb.readMedicineRecord(position);
			((TextView) rootView.findViewById(R.id.name_text)).setText(med.getMedicineName());
			((TextView) rootView.findViewById(R.id.size_text)).setText(med.getMedicineSize());
			((TextView) rootView.findViewById(R.id.avail_text)).setText(med.getMedAvailable()?"YES":"NO");
			((TextView) rootView.findViewById(R.id.type_text)).setText(med.getMedicineType());
			((TextView) rootView.findViewById(R.id.price_text)).setText(med.getMedicinePrice());
			
			return rootView;
		}

	}
}
