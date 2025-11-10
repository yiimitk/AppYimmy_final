package com.yimmy.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class LawyerAdapter extends ListAdapter<Abogado, LawyerAdapter.AbogadoViewHolder> {

    private final boolean isAdmin;
    private OnItemClickListener itemClickListener;
    private OnAdminOptionsClickListener adminOptionsClickListener;

    public LawyerAdapter(boolean isAdmin) {
        super(DIFF_CALLBACK);
        this.isAdmin = isAdmin;
    }

    private static final DiffUtil.ItemCallback<Abogado> DIFF_CALLBACK = new DiffUtil.ItemCallback<Abogado>() {
        @Override
        public boolean areItemsTheSame(@NonNull Abogado oldItem, @NonNull Abogado newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Abogado oldItem, @NonNull Abogado newItem) {
            return oldItem.getNombre().equals(newItem.getNombre()) &&
                    oldItem.getEspecialidad().equals(newItem.getEspecialidad());
        }
    };

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnAdminOptionsClickListener(OnAdminOptionsClickListener listener) {
        this.adminOptionsClickListener = listener;
    }

    @NonNull
    @Override
    public AbogadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_abogado, parent, false);
        return new AbogadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbogadoViewHolder holder, int position) {
        Abogado abogado = getItem(position);
        holder.bind(abogado);
    }

    class AbogadoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameView, specialtyView;
        ImageButton optionsMenuButton;

        public AbogadoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagen_abogado);
            nameView = itemView.findViewById(R.id.nombre_abogado);
            specialtyView = itemView.findViewById(R.id.especialidad_abogado);
            optionsMenuButton = itemView.findViewById(R.id.options_menu_button);

            itemView.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(getItem(getAdapterPosition()));
                }
            });
        }

        void bind(Abogado abogado) {
            nameView.setText(abogado.getNombre());
            specialtyView.setText(abogado.getEspecialidad());
            imageView.setImageResource(abogado.getIdImagen());

            if (isAdmin) {
                optionsMenuButton.setVisibility(View.VISIBLE);
                optionsMenuButton.setOnClickListener(v -> showPopupMenu(v, abogado));
            } else {
                optionsMenuButton.setVisibility(View.GONE);
            }
        }

        private void showPopupMenu(View view, Abogado abogado) {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.getMenuInflater().inflate(R.menu.lawyer_item_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.action_edit) {
                    if (adminOptionsClickListener != null) {
                        adminOptionsClickListener.onEditClick(abogado);
                    }
                    return true;
                } else if (itemId == R.id.action_delete) {
                    if (adminOptionsClickListener != null) {
                        adminOptionsClickListener.onDeleteClick(abogado);
                    }
                    return true;
                }
                return false;
            });
            popup.show();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Abogado abogado);
    }

    public interface OnAdminOptionsClickListener {
        void onEditClick(Abogado abogado);
        void onDeleteClick(Abogado abogado);
    }
}
