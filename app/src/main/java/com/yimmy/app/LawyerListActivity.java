
package com.yimmy.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class LawyerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView lawyerList = findViewById(R.id.lawyer_list);
        FloatingActionButton addLawyerFab = findViewById(R.id.add_lawyer_fab);

        List<Lawyer> lawyers = new ArrayList<>();
        lawyers.add(new Lawyer("Juan Perez", "Derecho Penal", "3101234567", android.R.drawable.star_on));
        lawyers.add(new Lawyer("Maria Rodriguez", "Derecho Civil", "3111234567", android.R.drawable.star_on));
        lawyers.add(new Lawyer("Carlos Gomez", "Derecho Laboral", "3121234567", android.R.drawable.star_on));

        LawyerAdapter adapter = new LawyerAdapter(lawyers);
        lawyerList.setAdapter(adapter);

        adapter.setOnItemClickListener(lawyer -> {
            Intent intent = new Intent(LawyerListActivity.this, LawyerDetailActivity.class);
            intent.putExtra("lawyerName", lawyer.getName());
            intent.putExtra("lawyerSpecialty", lawyer.getSpecialty());
            intent.putExtra("lawyerPhone", lawyer.getPhone());
            intent.putExtra("lawyerImageResId", lawyer.getImageResId());
            startActivity(intent);
        });

        addLawyerFab.setOnClickListener(v -> {
            Intent intent = new Intent(LawyerListActivity.this, EditLawyerActivity.class);
            startActivity(intent);
        });
    }
}
