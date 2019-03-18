package com.example.pets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {
    private  Context context;
    private ArrayList<Pet> petList;
    private OnPetClickListener onPetClickListener;

    public interface OnPetClickListener{
        void OnPetClick(Pet pet);
    }
    public void setOnPetClickListener(OnPetClickListener onPetClickListener){
        this.onPetClickListener = onPetClickListener;
    }


    public PetAdapter(Context context, ArrayList<Pet> petList) {
        this.context = context;
        this.petList = petList;
    }



    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.pet_list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PetAdapter.ViewHolder viewHolder, int position) {

        final Pet pet = petList.get(position);
        viewHolder.petNameTextView.setText(pet.getName());
        viewHolder.petOwnerTextView.setText(pet.getOwnerName());
        viewHolder.petImageView.setImageDrawable(ContextCompat.getDrawable(context,pet.getImageId()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPetClickListener.OnPetClick(pet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView petNameTextView;
        TextView petOwnerTextView;
        ImageView  petImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            petNameTextView = itemView.findViewById(R.id.pet_list_item_name);
            petOwnerTextView = itemView.findViewById(R.id.pet_list_item_owner_name);
            petImageView = itemView.findViewById(R.id.pet_list_item_image);
        }
    }
}
