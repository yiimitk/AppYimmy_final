package com.yimmy.app;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final LawyerRepository repository;
    private final LiveData<List<Abogado>> allAbogados;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = LawyerRepository.getInstance(application);
        allAbogados = repository.getAbogados();
    }

    // La actividad observará este método para obtener los datos
    public LiveData<List<Abogado>> getAllAbogados() {
        return allAbogados;
    }

    // La actividad llamará a este método para eliminar un abogado
    public void removeAbogado(Abogado abogado) {
        repository.removeAbogado(abogado);
    }
}
