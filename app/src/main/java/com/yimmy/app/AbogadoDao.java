
package com.yimmy.app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface AbogadoDao {

    @Query("SELECT * FROM abogados ORDER BY nombre ASC")
    List<Abogado> obtenerTodos();

    @Query("SELECT * FROM abogados WHERE id = :id")
    Abogado obtenerPorId(int id);

    @Insert
    void insertar(Abogado abogado);

    @Update
    void actualizar(Abogado abogado);

    @Delete
    void eliminar(Abogado abogado);
}
