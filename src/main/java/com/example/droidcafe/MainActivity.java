package com.example.droidcafe;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private boolean isLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Detectar orientación: existe container de order → landscape
        isLandscape = findViewById(R.id.fragment_order_container) != null;

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleBold);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Droid Cafe");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        FragmentManager fm = getSupportFragmentManager();

        // Fragment de lista
        if (fm.findFragmentById(isLandscape ? R.id.fragment_list_container : R.id.fragment_container) == null) {
            fm.beginTransaction()
                    .replace(isLandscape ? R.id.fragment_list_container : R.id.fragment_container,
                            new DessertListFragment())
                    .commit();
        }

        // Fragment de detalle (solo landscape)
        if (isLandscape && fm.findFragmentById(R.id.fragment_order_container) == null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_order_container, new OrderFragment())
                    .commit();
        }

        // 🔹 Escuchar cambios en BackStack para mostrar/ocultar botón de volver
        fm.addOnBackStackChangedListener(() -> {
            boolean hasBackStack = fm.getBackStackEntryCount() > 0;
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackStack);
                getSupportActionBar().setTitle("Droid Cafe");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Volver al fragment anterior
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
