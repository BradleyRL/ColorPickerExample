package android.example.colorpickerexample;


import android.example.colorpicker.ColorPickerDialog;
import android.example.colorpicker.ColorPickerSwatch;
import android.example.colorpicker.ColorSquare;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity  {

	
	// Selected colors
	private int mSelectedColorCal0 = 0;
	
		
	
	// ---------------------------------------------------------------

	//@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final int[] mColor = Utils.ColorUtils.colorChoice(this);
		final boolean isTablet = Utils.isTablet(this);
		ColorSquare square1 = (ColorSquare) findViewById(R.id.menurow_square);
		square1.setBackgroundColor(Utils.ColorUtils.parseWhiteColor());
		//TextView text1 = (TextView) findViewById(R.id.menurow_title);
		
		square1.setOnClickListener(new OnClickListener() {    		
    		
			@Override
			public void onClick(View v) {
				ColorPickerDialog colorcalendar = ColorPickerDialog.newInstance(
						R.string.color_picker_default_title, mColor,
						mSelectedColorCal0, 5,
						isTablet ? ColorPickerDialog.SIZE_LARGE
								: ColorPickerDialog.SIZE_SMALL);
							
				colorcalendar.setOnColorSelectedListener(colorcalendarListener);
				colorcalendar.show(getSupportFragmentManager(), "cal");
				
				
				
			}
			
			
		});
		
		
		//Re-bind listeners
		if (savedInstanceState!=null){
			
			ColorPickerDialog colorcalendar = (ColorPickerDialog) 
					getSupportFragmentManager().findFragmentByTag("cal");
	        if (colorcalendar != null) {
	            // re-bind listener to fragment
	            colorcalendar.setOnColorSelectedListener(colorcalendarListener );
	        }
		}
	}

	
	//-----------------------------------------------------------------------------------------------------------------
	// Listeners
	//-----------------------------------------------------------------------------------------------------------------
	
	
	// Implement listener to get selected color value
	ColorPickerSwatch.OnColorSelectedListener colorcalendarListener = new ColorPickerSwatch.OnColorSelectedListener(){

		@Override
		public void onColorSelected(int color) {	
			mSelectedColorCal0 = color;
			ColorSquare square = (ColorSquare) findViewById(R.id.menurow_square);
			square.setBackgroundColor(mSelectedColorCal0);
		}
	};



}
