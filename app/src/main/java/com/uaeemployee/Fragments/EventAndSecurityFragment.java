package com.uaeemployee.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.uaeemployee.Activites.BaseActivity;
import com.uaeemployee.R;

import java.util.Calendar;

public class EventAndSecurityFragment extends Fragment {

    View mView;
    MaterialCalendarView mcv;


    public EventAndSecurityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_event_and_security, container, false);
        initViews();
        initObj();
        initListeners();

        initializeCalendar();
        return mView;
    }

    private void initViews() {
    }

    private void initObj() {
        BaseActivity.fragment = new EventAndSecurityFragment();
    }

    private void initListeners() {

    }

    public void initializeCalendar() {
        mcv = (MaterialCalendarView) mView.findViewById(R.id.calendarView);

        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2016, 10, 1))
                .setMaximumDate(CalendarDay.from(2016, 10, 30))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
      mcv.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
    }


}
