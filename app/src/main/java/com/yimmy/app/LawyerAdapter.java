
package com.yimmy.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LawyerAdapter extends RecyclerView.Adapter<LawyerAdapter.LawyerViewHolder> {

    private List<Lawyer> lawyers;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Lawyer lawyer);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public LawyerAdapter(List<Lawyer> lawyers) {
        this.lawyers = lawyers;
    }

    @NonNull
    @Override
    public LawyerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lawyer_list_item, parent, false);
        return new LawyerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LawyerViewHolder holder, int position) {
        Lawyer lawyer = lawyers.get(position);
        holder.name.setText(lawyer.getName());
        holder.specialty.setText(lawyer.getSpecialty());
        holder.image.setImageResource(lawyer.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(lawyer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lawyers.size();
    }

    static class LawyerViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView specialty;

        public LawyerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.lawyer_image);
            name = itemView.findViewById(R.id.lawyer_name);
            specialty = itemView.findViewById(R.id.lawyer_specialty);
        }
    }
}
