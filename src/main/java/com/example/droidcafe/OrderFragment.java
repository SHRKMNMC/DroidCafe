package com.example.droidcafe;

import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG_FRAGMENT = OrderFragment.class.getSimpleName();

    public OrderFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        // Obtener el TextView para mostrar el postre seleccionado
        TextView textOrdered = view.findViewById(R.id.text_ordered_dessert);

        // Obtener el nombre del postre pasado como argumento
        Bundle args = getArguments();
        if (args != null) {
            String dessertName = args.getString("selected_dessert", "...");
            textOrdered.setText("You ordered a " + dessertName);
        }

        // Obtener el RadioGroup
        RadioGroup radioGroup = view.findViewById(R.id.delivery_radio_group);

        // Listener para cambios de selección
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String message = "";

            if (checkedId == R.id.sameday) {
                message = getString(R.string.same_day_messenger_service);
            } else if (checkedId == R.id.nextday) {
                message = getString(R.string.next_day_ground_delivery);
            } else if (checkedId == R.id.pickup) {
                message = getString(R.string.pick_up);
            } else {
                Log.d(TAG_FRAGMENT, getString(R.string.nothing_clicked));
            }

            if (!message.isEmpty()) {
                showToast(getString(R.string.chosen) + message);
            }
        });

        return view;
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
