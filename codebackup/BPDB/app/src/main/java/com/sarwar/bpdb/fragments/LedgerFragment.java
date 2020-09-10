package com.sarwar.bpdb.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sarwar.bpdb.R;
import com.sarwar.bpdb.adapter.MyRecyclerViewAdapter;
import com.sarwar.bpdb.models.Identify;
import com.sarwar.bpdb.utils.Constants;
import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by root on 6/23/18.
 */

public class LedgerFragment extends Fragment {
    ProgressDialog prog;

    EditText location_code;
    EditText consumer_number;
    EditText bill_month;
    EditText bill_month_to;
    YearMonthPickerDialog yearMonthPickerDialog;
    YearMonthPickerDialog yearMonthPickerDialogTo;
    Button get_bill;

    TextView customer_name;
    TextView customer_number;
    TextView location_ordivision;
    TextView tariff;

    LinearLayout personal_details;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    List<Identify> myModelList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.frag_ledger, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Ledger");
        // set up the RecyclerView

        recyclerView = (RecyclerView) view.findViewById(R.id.bill_details_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        customer_name = (TextView)view.findViewById(R.id.customer_name);
        customer_number = (TextView)view.findViewById(R.id.customer_number);
        location_ordivision = (TextView)view.findViewById(R.id.location_ordivision);
        tariff = (TextView)view.findViewById(R.id.tariff);

        personal_details = (LinearLayout) view.findViewById(R.id.personal_details);
        personal_details.setVisibility(View.GONE);

        prog = new ProgressDialog(getActivity());
        prog.setCancelable(false);
        prog.setMessage(getActivity().getResources().getString(R.string.pelase_wait));
        get_bill = (Button) view.findViewById(R.id.get_bill);
        get_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {if(location_code.getText().toString().length()<=0){
                location_code.setError(getActivity().getResources().getString(R.string.pelase_fill));
            }
            else if(consumer_number.getText().toString().length()<=0){
                consumer_number.setError(getActivity().getResources().getString(R.string.pelase_fill));
            }
            else if(bill_month.getText().toString().length()<=0){
                bill_month.setError(getActivity().getResources().getString(R.string.pelase_fill));
            }
            else if(bill_month_to.getText().toString().length()<=0){
                bill_month_to.setError(getActivity().getResources().getString(R.string.pelase_fill));
            }
            else {
                personal_details.setVisibility(View.GONE);
                try {
                    myModelList.clear();
                    adapter.notifyDataSetChanged();
                }catch (Exception rrrr){

                }
                getBillInfo();
            }
            }
        });
        location_code = (EditText)view.findViewById(R.id.location_code);
        consumer_number = (EditText)view.findViewById(R.id.consumer_number);
        bill_month_to = (EditText)view.findViewById(R.id.bill_month_to);
        bill_month_to.setFocusableInTouchMode(false);
        bill_month_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yearMonthPickerDialogTo.show();
            }
        });
        bill_month = (EditText)view.findViewById(R.id.bill_month);
        bill_month.setFocusableInTouchMode(false);
        bill_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yearMonthPickerDialog.show();
            }
        });
        yearMonthPickerDialog = new YearMonthPickerDialog(getActivity(), new YearMonthPickerDialog.OnDateSetListener() {
            @Override
            public void onYearMonthSet(int year, int month) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                month++;
                String yearMonth = "";
                if(month > 10)
                    yearMonth = year+""+month;
                else
                    yearMonth = year+"0"+month;
                bill_month.setText(yearMonth);
//                yearMonth.setText(dateFormat.format(calendar.getTime()));
            }
        });
        yearMonthPickerDialogTo = new YearMonthPickerDialog(getActivity(), new YearMonthPickerDialog.OnDateSetListener() {
            @Override
            public void onYearMonthSet(int year, int month) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                month++;
                String yearMonth = "";
                if(month > 10)
                    yearMonth = year+""+month;
                else
                    yearMonth = year+"0"+month;
                bill_month_to.setText(yearMonth);
//                yearMonth.setText(dateFormat.format(calendar.getTime()));
            }
        });
    }

    public void getBillInfo(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = Constants.BASE_URL+ "/Service1.svc/GetLedger?" +
                "LocationCode="+location_code.getText().toString()+
                "&ConsumerNumber="+consumer_number.getText().toString()+
                "&&FromBillMonth="+bill_month.getText().toString()+
                "&ToBillMonth="+bill_month_to.getText().toString();

        prog.show();
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            prog.hide();
                            Log.d("", "");
//                            bill_details.setText(response);
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<Identify>>(){}.getType();
                            myModelList = gson.fromJson(response, listType);

                            adapter = new MyRecyclerViewAdapter(getActivity(), myModelList,false);
                            recyclerView.setAdapter(adapter);

                            customer_name.setText(myModelList.get(0).getCUSTOMERNAME());
                            customer_number.setText(myModelList.get(0).getCONSUMERNUMBER());
                            location_ordivision.setText(myModelList.get(0).getDESCR());
                            tariff.setText(myModelList.get(0).getTARIFF());

                            personal_details.setVisibility(View.VISIBLE);

                            adapter.notifyDataSetChanged();
                        }catch (Exception exx){
                            exx.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    prog.hide();
                    Log.d("", "");
                    showDialogOnBadresponse(getActivity().getResources().getString(R.string.try_again_no_network));
                }catch (Exception ess){
                    ess.printStackTrace();
                }
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void showDialogOnBadresponse(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                getActivity().getResources().getString(R.string.yes_string),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        getBillInfo();
                    }
                });

        builder1.setNegativeButton(
                getActivity().getResources().getString(R.string.no_string),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}