package com.example.fleetmanagementsystem.carsFunctionality.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fleetmanagementsystem.R;
import com.example.fleetmanagementsystem.carsFunctionality.activites.FleetActivity;
import com.example.fleetmanagementsystem.carsFunctionality.adapter.FleetAdapter;
import com.example.fleetmanagementsystem.carsFunctionality.models.FleetModel;

import java.util.List;

public class TruckFragment extends Fragment  {

    private List<FleetModel> vehicleModel;
    private FleetAdapter fleetAdapter;
    private RecyclerView vehicleRecyclerView;

    public TruckFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trucks, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vehicleRecyclerView = view.findViewById(R.id.vehicle_recycler_view);
        fleetAdapter = new FleetAdapter(R.drawable.truck_icon);

        vehicleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        vehicleRecyclerView.setAdapter(fleetAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getVehicles();
        super.onActivityCreated(savedInstanceState);
    }

    public void getVehicles() {
        FleetActivity fleetActivity=(FleetActivity)getActivity();
        this.vehicleModel=fleetActivity.fleetFilter.getTrucks();
        fleetAdapter.setList(vehicleModel);
    }
}