package com.example.droidcafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OrderFragment extends Fragment {

    private static final String SELECTED_DESSERT = "selected_dessert";

    public OrderFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        TextView textOrdered = view.findViewById(R.id.text_ordered_dessert);

        // Recuperar el postre del Bundle
        Bundle args = getArguments();
        if (args != null && args.containsKey(SELECTED_DESSERT)) {
            String dessertName = args.getString(SELECTED_DESSERT);
            textOrdered.setText("You ordered a " + dessertName);
        } else {
            textOrdered.setText("Select a dessert!");
        }

        RadioGroup radioGroup = view.findViewById(R.id.delivery_radio_group);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String message = "";
            if (checkedId == R.id.sameday) message = getString(R.string.same_day_messenger_service);
            else if (checkedId == R.id.nextday) message = getString(R.string.next_day_ground_delivery);
            else if (checkedId == R.id.pickup) message = getString(R.string.pick_up);

            if (!message.isEmpty()) {
                Toast.makeText(requireContext(),
                        getString(R.string.chosen) + message,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
