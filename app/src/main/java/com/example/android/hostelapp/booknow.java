package com.example.android.hostelapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class booknow extends Activity {

    int nbOfGuests =1;
    int nbOfNights =1;
    int totalPrice=0;
    int totalPriceCheck=0;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booknow);

        //Button home
        Button next = (Button) findViewById(R.id.Home);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });

        //Spinner for the types of Room
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{getString(R.string.DropDown1),getString(R.string.DropDown2),getString(R.string.DropDown3),getString(R.string.DropDown4)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }


    /**
     * This method is called when the plus button of "Number of nights" field is clicked.
     */
    public void incrementDay(View view) {
        if (nbOfNights ==20){
            Toast toast = Toast.makeText(this, getString(R.string.incrementDayToast), Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
            return;
        }
        nbOfNights = nbOfNights + 1;
        displayDay(nbOfNights);
    }


    /**
     * This method is called when the minus button of "Number of nights" field is clicked.
     */
    public void decrementDay(View view) {
        if (nbOfNights == 1) {
            Toast toast = Toast.makeText(this, getString(R.string.decrementDayToast), Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
            return;
        }
        nbOfNights= nbOfNights- 1;
        displayDay(nbOfNights);
    }

    /**
     * This method displays the given quantity of nights on the screen.
     */
    private void displayDay(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.numberOfDays_text_view);
        quantityTextView.setText("" + number);
    }



    /**
     * This method is called when the plus button of the "Number of guests" field is clicked.
     */
    public void increment(View view) {
        if (nbOfGuests ==6){
            Toast toast = Toast.makeText(this, getString(R.string.incrementToast), Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
            return;
        }
        nbOfGuests = nbOfGuests+ 1;
        display(nbOfGuests);
    }

    /**
     * This method is called when the minus button  of the "Number of guests" field is clicked.
     */
    public void decrement(View view) {
        if (nbOfGuests == 1) {
            Toast toast = Toast.makeText(this, getString(R.string.decrementToast), Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
            return;
        }
        nbOfGuests = nbOfGuests - 1;
        display(nbOfGuests);
    }

    /**
     * This method displays the given number of guests on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method shows the price to the stay on the "Click to update" textView
     */
    public void onClickPrice (View view){
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        String roomSelected = String.valueOf(spinner1.getSelectedItem());
        displayPrice(nbOfGuests, nbOfNights, roomSelected);
    }

    /**
     * This method calculates the total price of the booking
     * @param nbOfGuests is the number of Guests selected by the user
     * @param nbOfNights is the number of Nights selected by the user
     * @param roomSelected is the type of room selected by the user
     */
    public int displayPrice(int nbOfGuests, int nbOfNights, String roomSelected){
        int pricePerNight;

        String typeOfRoom1 = getString(R.string.DropDown1);
        String typeOfRoom2 = getString(R.string.DropDown2);


        //Gives the price of the room according to the type of room selected
        if ((roomSelected==typeOfRoom1||(roomSelected==typeOfRoom2))){
            pricePerNight=600;
        } else {
            pricePerNight=550;
        }

        //Calculate the total price of the stay
        totalPrice = pricePerNight * nbOfGuests * nbOfNights;

        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(""+ totalPrice);
        return totalPrice;
    }


    /**
     * This method creates sends the booking request
     */

    public void onClickBookNowMail (View view){
        String emailAddress= getString(R.string.email);

        //Find the user's name
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String nameOfBooker= nameField.getText().toString();

        //Find the Check-in Date
        EditText checkinField = (EditText) findViewById(R.id.CheckInField);
        String checkinDate = checkinField.getText().toString();

        //Find the Type of Room selected
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        String roomSelected = String.valueOf(spinner1.getSelectedItem());

        /**
         * Recalculates the price to check if the price has been correctly updated before sending the booking request
         *
         * Without this step, when the user updates the price and change the parameters of the equation afterwards
         * without reupdating the price, the price sent in the mail is the former price and not the actual price.
         * For example, first the user states that he'll stay one night in the 4-bed mixed dorm and updates the price,
         * the email states that his stay will cost 600NTD. But if he changes his mind and wants to stay two nights (cost of
         * the stay : 1200 NTD) but doesn't update the price, the email will still show 600 NTD.
         *
         */

        totalPriceCheck = displayPrice(nbOfGuests, nbOfNights, roomSelected);


        //Calls the createBookngSummary
        String bookingSummary= createBookingSummary(nameOfBooker, checkinDate, nbOfGuests, nbOfNights, roomSelected, totalPrice);

        if(!((nameOfBooker.matches(""))||(checkinDate.matches(""))||(totalPrice==0)||(totalPriceCheck!=totalPrice))){
            Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
            mailIntent.setData(Uri.parse("mailto:" + emailAddress)); // only email apps should handle this
            if (mailIntent.resolveActivity(getPackageManager()) != null) {
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Booking request from " + nameOfBooker);
                mailIntent.putExtra(Intent.EXTRA_TEXT, bookingSummary);

                startActivity(mailIntent);
            }
        }

    }

    /**
     * This method creates a summary of the booking
     * @param nameOfBooker is the name input by the user
     * @param checkinDate is the check-in date input by the user
     * @param nbOfGuests is the number of Guests selected by the user
     * @param nbOfNights is the number of Nights selected by the user
     * @param roomSelected is the type of room selected by the user
     * @param totalPrice is the total price of the stay
     * @return Booking summary
     */
    public String createBookingSummary(String nameOfBooker, String checkinDate, int nbOfGuests, int nbOfNights, String roomSelected, int totalPrice) {
        if (nameOfBooker.matches("")||checkinDate.matches("")||(totalPrice==0)||(totalPriceCheck!=totalPrice)){
            Toast toast = Toast.makeText(this, getString(R.string.BookToast), Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
            return "";
        }

        String bookingSummary = getString(R.string.booking_name) + " " + nameOfBooker;
        bookingSummary += " " + getString(R.string.booking_nbOfGuests) + " " + nbOfGuests;

        if (nbOfGuests==1){
            bookingSummary += " " + getString(R.string.booking_typeOfRoomSG) + " "+ roomSelected;
        } else {
            bookingSummary += " " + getString(R.string.booking_typeOfRoomPL) + " "+ roomSelected;
        }

        if (nbOfGuests==1){
            bookingSummary += getString(R.string.booking_checkinDateSG) + " "+ checkinDate;
        } else {
            bookingSummary += getString(R.string.booking_checkinDatePL) + " "+ checkinDate;
        }

        bookingSummary += " " +getString(R.string.booking_nbOfNights) + " " + nbOfNights;

        if (nbOfNights==1){
            bookingSummary += " " +getString(R.string.booking_priceSG) + " " + totalPrice;
        } else {
            bookingSummary += " " +getString(R.string.booking_pricePL) + " " + totalPrice;
        }
        bookingSummary += " " +getString(R.string.booking_end) + "\n" + nameOfBooker;
        return bookingSummary;
    }



}


