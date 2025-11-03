
package com.yimmy.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AbogadoAdapter extends RecyclerView.Adapter<AbogadoAdapter.AbogadoViewHolder> {

    private List<Abogado> abogados;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Abogado abogado);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public AbogadoAdapter(List<Abogado> abogados) {
        this.abogados = abogados;
    }

    public void actualizarDatos(List<Abogado> nuevosAbogados) {
        this.abogados = nuevosAbogados;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AbogadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_abogado, parent, false);
        return new AbogadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbogadoViewHolder holder, int position) {
        Abogado abogado = abogados.get(position);
        holder.nombre.setText(abogado.getNombre());
        holder.especialidad.setText(abogado.getEspecialidad());
        holder.imagen.setImageResource(abogado.getIdImagen());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(abogado);
            }
        });
    }

    @Override
    public int getItemCount() {
        return abogados.size();
    }

    static class AbogadoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView nombre;
        TextView especialidad;

        public AbogadoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen_abogado);
            nombre = itemView.findViewById(R.id.nombre_abogado);
            especialidad = itemView.findViewById(R.id.especialidad_abogado);
        }
    }
}
