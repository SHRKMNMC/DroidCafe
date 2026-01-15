package com.example.droidcafe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private boolean isLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Se cargará activity_main.xml o activity_main.xml en landscape

        // Detectar si el layout tiene fragment_order_container → estamos en landscape
        isLandscape = findViewById(R.id.fragment_order_container) != null;

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Droid Cafe");
        }

        // Configurar título bold y blanco usando style
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleBold);

        // Inicializar fragmentos
        if (savedInstanceState == null) {
            // Siempre cargamos la lista de postres
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            isLandscape ? R.id.fragment_list_container : R.id.fragment_container,
                            new DessertListFragment()
                    )
                    .commit();

            // Si estamos en landscape, cargamos también el OrderFragment vacío
            if (isLandscape) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_order_container, new OrderFragment())
                        .commit();
            }
        }

        // 🔹 Escuchar cambios en el BackStack para mostrar u ocultar el botón de volver
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            FragmentManager fm = getSupportFragmentManager();
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
