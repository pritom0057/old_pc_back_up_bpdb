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
import com.sarwar.bpdb.models.BillModel;
import com.sarwar.bpdb.utils.Constants;
import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog;

import java.io.File;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 6/23/18.
 */

public class BIllFragment_updated extends Fragment {
    ProgressDialog prog;

    EditText location_code_et;
    EditText consumer_number_et;
    EditText bill_month_et;
    YearMonthPickerDialog yearMonthPickerDialog;
    Button get_bill;

    LinearLayout customer_and_bill_details;
    List<BillModel> myModelList;

    TextView customer_name;
    TextView sd_and_su;
    TextView customer_number;
    TextView book;
    TextView tariff;
    TextView meter_number;
    TextView meter_condition;


    TextView bill_number;
//    TextView cd;
    TextView princ_amp;
    TextView vat_amp;
    TextView bill_month_total;
    TextView arreer_prn;
    TextView arrear_vat;
    TextView arrear_lps;
    TextView adj_prn;
    TextView adj_vat;
    TextView adj_lps;
    TextView total_bill;
    TextView last_pay_date;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.frag_bill_update, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Bill");

        customer_name = (TextView) view.findViewById(R.id.customer_name);
        customer_number = (TextView)view.findViewById(R.id.customer_number);
        sd_and_su = (TextView)view.findViewById(R.id.sd_and_su);
        book = (TextView)view.findViewById(R.id.book);
        tariff = (TextView)view.findViewById(R.id.tariff);
        meter_number = (TextView)view.findViewById(R.id.meter_number);
        meter_condition = (TextView)view.findViewById(R.id.meter_condition);


        bill_number = (TextView)view.findViewById(R.id.bill_number);
//        cd = (TextView)view.findViewById(R.id.cd);
        princ_amp = (TextView)view.findViewById(R.id.princ_amp);
        vat_amp = (TextView)view.findViewById(R.id.vat_amp);
        bill_month_total = (TextView)view.findViewById(R.id.bill_month_total);
        arreer_prn = (TextView)view.findViewById(R.id.arreer_prn);
        arrear_vat = (TextView)view.findViewById(R.id.arrear_vat);
        arrear_lps = (TextView)view.findViewById(R.id.arrear_lps);
        total_bill = (TextView)view.findViewById(R.id.total_bill);
        last_pay_date = (TextView)view.findViewById(R.id.last_pay_date);
        adj_prn = (TextView) view.findViewById(R.id.adj_prn);
        adj_vat = (TextView) view.findViewById(R.id.adj_vat);
        adj_lps = (TextView) view.findViewById(R.id.adj_lps);


        location_code_et = (EditText)view.findViewById(R.id.location_code_et);
        consumer_number_et = (EditText)view.findViewById(R.id.consumer_number_et);
        bill_month_et = (EditText)view.findViewById(R.id.bill_month_et);

        customer_and_bill_details = (LinearLayout)view.findViewById(R.id.customer_and_bill_details);
        customer_and_bill_details.setVisibility(View.GONE);
        prog = new ProgressDialog(getActivity());
        prog.setCancelable(false);
        prog.setMessage(getActivity().getResources().getString(R.string.pelase_wait));
        get_bill = (Button) view.findViewById(R.id.get_bill);
        get_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(location_code_et.getText().toString().length()<=0){
                    location_code_et.setError(getActivity().getResources().getString(R.string.pelase_fill));
                }
                else if(consumer_number_et.getText().toString().length()<=0){
                    consumer_number_et.setError(getActivity().getResources().getString(R.string.pelase_fill));
                }
                else if(bill_month_et.getText().toString().length()<=0){
                    bill_month_et.setError(getActivity().getResources().getString(R.string.pelase_fill));
                }
                else {
                    customer_and_bill_details.setVisibility(View.GONE);
                    try {
                        myModelList.clear();
                    }catch (Exception rrrr){

                    }
                    getBillInfo();
                }
            }
        });

        bill_month_et.setFocusableInTouchMode(false);
        bill_month_et.setOnClickListener(new View.OnClickListener() {
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
                bill_month_et.setText(yearMonth);
//                yearMonth.setText(dateFormat.format(calendar.getTime()));
            }
        });
    }

    public void getBillInfo(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = Constants.BASE_URL+
                "/Service1.svc/GetBill?LocationCode="+location_code_et.getText().toString()+
                "&ConsumerNumber="+consumer_number_et.getText().toString()+"&BillMonth="+bill_month_et.getText().toString();
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
                            Type listType = new TypeToken<List<BillModel>>(){}.getType();
                            List<BillModel> myModelList = gson.fromJson(response, listType);
                            customer_and_bill_details.setVisibility(View.VISIBLE);

                            DecimalFormat df = new DecimalFormat("####0.00");
                            try{

                                customer_name.setText(myModelList.get(0).getCUSTOMERNAME());
                            }
                            catch (Exception asdasd)
                            {
                                customer_name.setText("");
                            }
                            try{
                                customer_number.setText(myModelList.get(0).getCONSUMERNUMBER());
                            }
                            catch (Exception asdasd)
                            {
                                customer_number.setText("");
                            }
                            try{
                                sd_and_su.setText(myModelList.get(0).getDESCR());
                            }
                            catch (Exception asdasd)
                            {
                                sd_and_su.setText("");
                            }
                            try{
                                book.setText(myModelList.get(0).getBOOKNO());
                            }
                            catch (Exception asdasd)
                            {
                                book.setText("");
                            }
                            try{
                                tariff.setText(myModelList.get(0).getTARIFF());
                            }
                            catch (Exception asdasd)
                            {
                                tariff.setText("");
                            }
                            try{
                                meter_number.setText(myModelList.get(0).getMETERNUMKWH());
                            }
                            catch (Exception asdasd)
                            {
                                meter_number.setText("");
                            }

                            try{
                                meter_condition.setText(myModelList.get(0).getMETERCONDKWH());

                            }
                            catch (Exception asdasd)
                            {
                                meter_condition.setText("");
                            }
                            try{
                                bill_number.setText(myModelList.get(0).getBILLNO());

                            }
                            catch (Exception asdasd)
                            {
                                bill_number.setText("");
                            }


//                            try{
//
//                                cd.setText(myModelList.get(0).getCD());
//                            }
//                            catch (Exception asdasd)
//                            {
//                                cd.setText("");
//                            }
                            try{
                                princ_amp.setText(df.format(myModelList.get(0).getPRNAMT())+"");
                            }
                            catch (Exception asdasd)
                            {
                                princ_amp.setText("");
                            }
                            try{
                                vat_amp.setText(df.format(myModelList.get(0).getCURRENTVAT())+"");
                            }
                            catch (Exception asdasd)
                            {
                                vat_amp.setText("");
                            }
                            try{
                                bill_month_total.setText(df.format(myModelList.get(0).getBILLMONTHTOT())+"");
                            }
                            catch (Exception asdasd)
                            {
                                bill_month_total.setText("");
                            }
                            try{
                                arreer_prn.setText(df.format(myModelList.get(0).getARRADVADJPRN())+"");
                            }
                            catch (Exception asdasd)
                            {
                                arreer_prn.setText("");
                            }
                            try{
                                arrear_vat.setText(df.format(myModelList.get(0).getARRADVADJVAT())+"");
                            }
                            catch (Exception asdasd)
                            {
                                arrear_vat.setText("");
                            }

                            try{
                                arrear_lps.setText(df.format(myModelList.get(0).getARRADVADJLPS())+"");

                            }
                            catch (Exception asdasd)
                            {
                                arrear_lps.setText("");
                            }
                            try{
                                adj_prn.setText(df.format(myModelList.get(0).getADJUSTEDPRN())+"");
                            }
                            catch (Exception asdasd)
                            {
                                adj_prn.setText("");
                            }
                            try{
                                adj_vat.setText(df.format(myModelList.get(0).getADJUSTEDVAT())+"");
                            }
                            catch (Exception asdasd)
                            {
                                adj_vat.setText("");
                            }

                            try{
                                adj_lps.setText(df.format(myModelList.get(0).getADJUSTEDVAT())+"");

                            }
                            catch (Exception asdasd)
                            {
                                adj_lps.setText("");
                            }
                            try{
                                total_bill.setText(df.format(myModelList.get(0).getTOTALBILL())+"");

                            }
                            catch (Exception asdasd)
                            {
                                total_bill.setText("");
                            }

                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            String dateInString = "";
                            try {
                                dateInString = myModelList.get(0).getINVOICEDUEDATE();
                                Date date = formatter.parse(dateInString);
                                SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                                dateInString = formatter2.format(date);
                            }
                            catch (Exception asdsa){
                                dateInString = myModelList.get(0).getINVOICEDUEDATE();
                            }

                            last_pay_date.setText(dateInString+"");

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
                getActivity().getResources().getString(R.string.try_again),
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