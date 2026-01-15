package com.example.droidcafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DessertListFragment extends Fragment {

    private static final String SELECTED_DESSERT = "selected_dessert";

    public DessertListFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dessert_list, container, false);

        ListView listView = view.findViewById(R.id.listViewDesserts);

        ArrayList<Dessert> desserts = new ArrayList<>();
        desserts.add(new Dessert("Donut", "Donut clásico glaseado", R.drawable.donut_circle));
        desserts.add(new Dessert("Froyo", "Froyo cremoso estilo Android", R.drawable.froyo_circle));
        desserts.add(new Dessert("Ice Cream", "Helado refrescante", R.drawable.icecream_circle));

        DessertAdapter adapter = new DessertAdapter(requireContext(), desserts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Dessert selectedDessert = desserts.get(position);

            // Guardamos en Bundle para restaurar tras rotación
            Bundle args = new Bundle();
            args.putString(SELECTED_DESSERT, selectedDessert.getName());

            OrderFragment orderFragment = new OrderFragment();
            orderFragment.setArguments(args);

            View orderContainer = requireActivity().findViewById(R.id.fragment_order_container);

            if (orderContainer != null) {
                // Landscape → reemplazar solo el OrderFragment
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_order_container, orderFragment)
                        .commit();
            } else {
                // Portrait → reemplazar container principal
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, orderFragment)
                        .addToBackStack(null)
                        .commit();
            }

            Toast.makeText(requireContext(),
                    "You ordered " + selectedDessert.getName(),
                    Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
