package com.yimmy.app;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Abogado.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AbogadoDao abogadoDao();

    private static volatile AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "abogado-database") // Nombre de archivo de BD más estándar
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AbogadoDao dao = INSTANCE.abogadoDao();
                
                // Insertar datos iniciales
                dao.insertar(new Abogado("Juan Perez", "Derecho Penal", "3101234567", android.R.drawable.star_on));
                dao.insertar(new Abogado("Maria Rodriguez", "Derecho Civil", "3111234567", android.R.drawable.star_on));
                dao.insertar(new Abogado("Carlos Gomez", "Derecho Laboral", "3121234567", android.R.drawable.star_on));
            });
        }
    };
}
