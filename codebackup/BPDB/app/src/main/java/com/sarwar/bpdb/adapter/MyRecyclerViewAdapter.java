package com.sarwar.bpdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sarwar.bpdb.R;
import com.sarwar.bpdb.models.Identify;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 6/30/18.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Identify> myModelList;
    private LayoutInflater mInflater;
    private boolean isBill;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<Identify> data,boolean isBill) {
        this.mInflater = LayoutInflater.from(context);
        this.myModelList = data;
        this.isBill = isBill;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_bill_details, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.reading.setText(((int) Math.round(myModelList.get(position).getOPNKWHSRRDNG())) + "");
        }catch (Exception we){
            we.printStackTrace();
            holder.reading.setText("");
        }
//        holder.consumtion.setText(myModelList.get(position).getCONSKWHSR()+"");
        DecimalFormat df = new DecimalFormat("####0.00");
        try {
            holder.current_bill.setText(df.format(myModelList.get(position).getCURRENTBILL()) + "");
        }catch (Exception ewr){
            holder.current_bill.setText("");
        }
        try {
            holder.arrear_bill.setText(df.format(myModelList.get(position).getARREARBILL()) + "");
        }catch (Exception qwe){
            holder.arrear_bill.setText("");
        }

        try {
            holder.total_bill.setText(df.format(myModelList.get(position).getTOTALBILL())+"");
        }catch (Exception qwe){
            holder.total_bill.setText("");
        }
        try {
            holder.bill_number.setText(myModelList.get(position).getBILLNO()+"");
        }catch (Exception qwe){
            holder.bill_number.setText("");
        }
        try {
            holder.meter_condition.setText(myModelList.get(position).getMETERCONDKWH()+"");
        }catch (Exception qwe){
            holder.meter_condition.setText("");
        }
        try {
            holder.cd.setText(myModelList.get(position).getCD()+"");
        }catch (Exception qwe){
            holder.cd.setText("");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateInString = "";
        try {
            dateInString = myModelList.get(position).getINVOICEDUEDATE();
            Date date = formatter.parse(dateInString);
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            dateInString = formatter2.format(date);
        }
        catch (Exception asdsa){
            dateInString = myModelList.get(position).getINVOICEDUEDATE();
        }

        holder.due_date.setText(dateInString+"");
        if(this.isBill){

            holder.paid_ll.setVisibility(View.INVISIBLE);
            holder.pay_date_ll.setVisibility(View.INVISIBLE);
            holder.due_date_ll.setVisibility(View.VISIBLE);
        }
        else {

            holder.pay_date_ll.setVisibility(View.VISIBLE);
            holder.paid_ll.setVisibility(View.VISIBLE);
            holder.due_date_ll.setVisibility(View.INVISIBLE);
        }
        try{
            holder.paid.setText(df.format(myModelList.get(position).getPAIDAMT()) + "");
            holder.pay_date.setText(dateInString+ "");
        }catch (Exception we){

            holder.paid.setText("0.0");
            holder.pay_date.setText("");
        }
//        holder.consumer_check_digit.setText(myModelList.get(position).getCONSCHKDIGIT()+"");

        try {
            String month = theMonth(Integer.parseInt(myModelList.get(position).getBILLCYCLECODE().substring(4, 6)) - 1);
            String year = myModelList.get(position).getBILLCYCLECODE().substring(0, 4);

            holder.month_year_name.setText(month + ", " + year);
        }catch (Exception asd){

            holder.month_year_name.setText("");
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        int size = 0;
        try {
            size = myModelList.size();
        }
        catch (Exception asdsa ){}
        return size;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView reading;
//        TextView consumtion;
        TextView current_bill;
        TextView arrear_bill;
        TextView total_bill;
        TextView bill_number;
        TextView meter_condition;
        TextView cd;
//        TextView consumer_check_digit;
        TextView month_year_name;
        TextView due_date;
        TextView paid;
        TextView pay_date;
        LinearLayout due_date_ll;
        LinearLayout paid_ll;
        LinearLayout pay_date_ll;

        ViewHolder(View itemView) {
            super(itemView);
            reading = (TextView) itemView.findViewById(R.id.reading);
//            consumtion = (TextView) itemView.findViewById(R.id.consumtion);
            current_bill = (TextView) itemView.findViewById(R.id.current_bill);
            arrear_bill = (TextView) itemView.findViewById(R.id.arrear_bill);
            total_bill = (TextView) itemView.findViewById(R.id.total_bill);
            bill_number = (TextView) itemView.findViewById(R.id.bill_number);
            meter_condition = (TextView) itemView.findViewById(R.id.meter_condition);
            cd = (TextView) itemView.findViewById(R.id.cd);
//            consumer_check_digit = (TextView) itemView.findViewById(R.id.consumer_check_digit);
            month_year_name = (TextView) itemView.findViewById(R.id.month_year_name);
            due_date = (TextView) itemView.findViewById(R.id.due_date);
            paid = (TextView) itemView.findViewById(R.id.paid);
            pay_date = (TextView) itemView.findViewById(R.id.pay_date);
            due_date_ll = (LinearLayout) itemView.findViewById(R.id.due_date_ll);
            paid_ll = (LinearLayout) itemView.findViewById(R.id.paid_ll);
            pay_date_ll = (LinearLayout) itemView.findViewById(R.id.pay_date_ll);
        }

    }
    public static String theMonth(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
}