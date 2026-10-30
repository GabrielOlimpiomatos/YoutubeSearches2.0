package com.example.youtubesearches20;

import android.os.Bundle;

import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


   private TextInputEditText edtTag;
    private TextInputEditText edtConsulta;
    private ImageButton btnsalvar;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<String> consultas;
    private SearchPreferences searchPreferences;
    private String tagEdicao = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.edtTag = findViewById(R.id.edtTag);
        this.edtConsulta = findViewById(R.id.edtBusca);
        this.btnsalvar = findViewById(R.id.btnSalvar);
        this.recyclerView = findViewById(R.id.recyclerView);

        searchPreferences = new SearchPreferences(MainActivity.this);
        consultas = searchPreferences.getAllTags();

        adapter = new Adapter(consultas, searchPreferences);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        this.recyclerView.setAdapter(this.adapter);
    }
}