package com.example.fleetmanagementsystem.driverFunctionality;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fleetmanagementsystem.FirebaseServices.RetrieveDataFromFireStore;
import com.example.fleetmanagementsystem.R;

import java.util.List;

import io.reactivex.functions.Consumer;

public class DriversBusFragment extends Fragment implements DriversAdapter.onItemClicked {

    private List<DriverModel> driverModels;
    private DriversAdapter driverAdapter;
    private RecyclerView recyclerView;

    public DriversBusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drivers_bus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.drivers_bus_recyclerView);
        driverAdapter = new DriversAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(driverAdapter);

        new RetrieveDataFromFireStore().retrieveAllDrivers();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDrivers();
    }

    public void getDrivers() {
        RetrieveDataFromFireStore.driverSubject.subscribe(new Consumer<List<DriverModel>>() {
            @Override
            public void accept(List<DriverModel> driverModels) throws Exception {
                DriversBusFragment.this.driverModels = driverModels;
                driverAdapter.setList(driverModels);
            }
        });
    }

    @Override
    public void onItemClicked(int postion) {
        Intent intent = new Intent(getContext(),DriversDetailsActivity.class);
        startActivity(intent);
    }
}