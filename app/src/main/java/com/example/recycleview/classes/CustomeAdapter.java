package com.example.recycleview.classes;

import android.app.AlertDialog;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.recycleview.R;
import com.example.recycleview.Models.DataModel;
import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {

    private ArrayList<DataModel> originalList; // The original full list
    private ArrayList<DataModel> filteredList; // The filtered list to display

    public CustomeAdapter(ArrayList<DataModel> dataSet) {
        this.originalList = new ArrayList<>(dataSet); // Save a copy of the original list
        this.filteredList = dataSet; // Initialize with the full list
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView);
            textViewDescription = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position) {
        DataModel dataModel = filteredList.get(position);
        holder.textViewName.setText(dataModel.getName());
        holder.textViewDescription.setText(dataModel.getDescription());
        holder.imageView.setImageResource(dataModel.getImage());

        holder.itemView.setOnClickListener(v -> {
            // יצירת ImageView חדש להצגה בדיאלוג
            ImageView imageViewCopy = new ImageView(v.getContext());
            imageViewCopy.setImageDrawable(holder.imageView.getDrawable());
            imageViewCopy.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            // יצירת דיאלוג
            AlertDialog dialog = new AlertDialog.Builder(v.getContext())
                    .setTitle(holder.textViewName.getText())
                    .setMessage(holder.textViewDescription.getText())
                    .setView(imageViewCopy)
                    .setPositiveButton("Close", (dialogInterface, which) -> dialogInterface.dismiss())
                    .create();

            dialog.show();

            // שינוי גודל הדיאלוג לאחר ההצגה
            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(800, 800); // גודל קבוע לדיאלוג
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return filteredList.size();
    }

    public void filter(String text)
    {
        text = text.toLowerCase();
        filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            for (DataModel item : originalList) {
                if (item.getName().toLowerCase().contains(text))
                {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
