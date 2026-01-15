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

            // Mostrar Toast
            Toast.makeText(requireContext(),
                    "You ordered " + selectedDessert.getName(),
                    Toast.LENGTH_SHORT).show();

            // Crear OrderFragment y pasar nombre del postre
            OrderFragment orderFragment = new OrderFragment();
            Bundle args = new Bundle();
            args.putString("selected_dessert", selectedDessert.getName());
            orderFragment.setArguments(args);

            // Detectar landscape
            View orderContainer = requireActivity().findViewById(R.id.fragment_order_container);
            if (orderContainer != null) {
                // Landscape: reemplazamos solo el OrderFragment
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_order_container, orderFragment)
                        .commit();
            } else {
                // Portrait: reemplazamos el container y agregamos al back stack
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, orderFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
