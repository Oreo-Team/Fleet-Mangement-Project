package com.example.fleetmanagementsystem.driverFunctionality.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.fleetmanagementsystem.Constants.ObserverStringResponse;
import com.example.fleetmanagementsystem.FirebaseServices.DeleteDataFromFireStore;
import com.example.fleetmanagementsystem.R;
import com.example.fleetmanagementsystem.driverFunctionality.activities.DriversActivity;
import com.example.fleetmanagementsystem.driverFunctionality.models.DriverModel;


public class DeleteDriverFragment extends AppCompatDialogFragment {

    Button delButton , canelButton;
    DriverModel driverModel;
    View view;

    public DeleteDriverFragment(DriverModel driverModel) {
        // Required empty public constructor
        this.driverModel = driverModel;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_delete_driver,null);
        builder.setView(view);
        setCancelable(false);
        deleteButton();
        cancelButton();
        return builder.create();
    }

    private void deleteButton(){
        delButton = view.findViewById(R.id.btn_delete_driver_dialogue);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeleteDataFromFireStore.deleteDriver(driverModel);
                Toast.makeText(getContext() , "Done: Driver Deleted" , Toast.LENGTH_LONG).show();
                DriversActivity.driverActivityRefresher.onNext(ObserverStringResponse.SUCCESS_RESPONSE);
                dismiss();
            }
        });
    }
    private void cancelButton(){
        canelButton = view.findViewById(R.id.btn_cancel_driver_dialogue);
        canelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}