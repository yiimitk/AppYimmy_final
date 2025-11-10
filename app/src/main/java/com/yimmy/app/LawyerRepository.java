package com.yimmy.app;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LawyerRepository {

    private static LawyerRepository instance;
    private final AbogadoDao abogadoDao;
    private final ExecutorService executorService;

    private LawyerRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        this.abogadoDao = db.abogadoDao();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public static synchronized LawyerRepository getInstance(Context context) {
        if (instance == null) {
            instance = new LawyerRepository(context.getApplicationContext());
        }
        return instance;
    }

    // 1. Ahora devuelve LiveData directamente desde el DAO
    public LiveData<List<Abogado>> getAbogados() {
        return abogadoDao.obtenerTodos();
    }

    public void addAbogado(Abogado abogado) {
        executorService.execute(() -> abogadoDao.insertar(abogado));
    }

    public void removeAbogado(Abogado abogado) {
        executorService.execute(() -> abogadoDao.eliminar(abogado));
    }
}
