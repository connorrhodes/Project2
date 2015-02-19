package project2.rhodes.com.project2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent; //onEditorAction boolean
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;   // click listener activity
import android.app.Activity;
import android.content.SharedPreferences; //getMethods to save values
import android.content.SharedPreferences.Editor; // for putMethods

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class Project2Activity extends Activity implements OnClickListener, OnSeekBarChangeListener
{

    //create private variables for the current widgets
    private TextView editTextField;
    private TextView degreeCalc;
    private Button buttonConverter;

    private SharedPreferences savedValues;
    private String degreeAmountString;

    private SeekBar tempSeekBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project2);


        //make references for the widgets
        editTextField = (TextView) findViewById(R.id.editTextField);
        degreeCalc = (TextView) findViewById(R.id.degreeCalc);
        buttonConverter = (Button) findViewById(R.id.buttonConverter);

        tempSeekBar = (SeekBar) findViewById(R.id.tempSeekBar);

        //set fahrenheit
        // = 0.0f;

        //set the listeners
        //editTextField.setOnEditorActionListener(this);
        buttonConverter.setOnClickListener(this);

        tempSeekBar.setOnSeekBarChangeListener(this);
       // tempSeekBar.setOnKeyListener(this);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }



    @Override
    public void onPause()
    {
        //save the instance variables
        Editor editor = savedValues.edit();
        editor.putString("degreeAmountString", degreeAmountString);
       // editor.putString("degreeCalc", degreeCalc);
        //editor.putFloat("");
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        //get the instance variables
        degreeAmountString = savedValues.getString("degreeAmountString", "");
       // degreeCalc = savedValues.getString("degreeCalc", "");

        //set the temp amount on the widget
        editTextField.setText(degreeAmountString);

       // int progress = Math.round();



        calculateAndDisplay();
    }



    //Calculate user input
    public void calculateAndDisplay()
    {
        //get degree amount
 //       degreeAmountString = editTextField.getText().toString();
        float degreeAmount;
        //no text in the field will make the celcius number = 0
        //once text is entered in the field and entered, the input will be calculated

/*        if(degreeAmountString.equals(""))
        {
            degreeAmount = 0;
        }
        else
        {
            degreeAmount = Float.parseFloat(degreeAmountString);
        }
*/
        int progress = tempSeekBar.getProgress();
        degreeAmount = (float) progress;

        //formula c = ((f - 32) * 5) /9
        float convertedDegree = ((degreeAmount - 32) * 5) / 9 ;
        String celciusText = String.valueOf(convertedDegree);
        //Display
        degreeCalc.setText(celciusText);



    }

    //create class function for OnEditorActionListener
    //IME_ACTION_DONE / IME_ACTION_UNSPECIFIED allows the user to use both the
    //soft and hard keyboards


  /* @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }
*/
    //links percent amount to seekbar
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        editTextField.setText(progress + "°");
        calculateAndDisplay();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    ///////////////////////////////////Determine which method you want///////////////////////////////
    ///////////////////////////////////to calculate the degree amount///////////////////////////////
    ///////////////////////////////////by seekbar or button click///////////////////////////////////
    //calculates amounts from seekbar percent after you stop moving the text bar
    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {


    }

    //create class function for OnClickListener
    //calculates the seekbar amount by clicking the button
    @Override
    public void onClick(View v)
    {
        calculateAndDisplay();
    }



}
