package com.example.shop;

import static com.example.shop.DeliveryActivity.SELECT_ADDRESS;
import static com.example.shop.AccountFragment.MANAGE_ADDRESS;
import static com.example.shop.MyAddressesActivity.refreshItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.Viewholder> {

    private List<AddressesModel> addressesModelList;
    private int MODE;
    private int preSelectedPosition;

    public AddressesAdapter(List<AddressesModel> addressesModelList, int mode) {
        this.addressesModelList = addressesModelList;
        this.MODE = mode;
        preSelectedPosition = Address.selectedAddress;
    }

    @NonNull
    @Override
    public AddressesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressesAdapter.Viewholder holder, int position) {
        String name = addressesModelList.get(position).getName();
        String address = addressesModelList.get(position).getAddress();
        String index = addressesModelList.get(position).getIndex();
        Boolean is_selected_address = addressesModelList.get(position).getIs_selected_address();
        holder.setData(name, address, index, is_selected_address, position);
    }

    @Override
    public int getItemCount() {
        return addressesModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView address;
        private TextView index;
        private ImageView icon;
        private LinearLayout optionLinearLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_address_item);
            address = itemView.findViewById(R.id.address_item);
            index = itemView.findViewById(R.id.index_address_item);
            icon = itemView.findViewById(R.id.icon_check);
            optionLinearLayout = itemView.findViewById(R.id.option);
        }

        private void setData(String nameUser, String addressUser, String indexUser, Boolean selected, int position){
            name.setText(nameUser);
            address.setText(addressUser);
            index.setText(indexUser);
            if(MODE == SELECT_ADDRESS){
                icon.setImageResource(R.drawable.ic_check);
                if (selected){
                    icon.setVisibility(View.VISIBLE);
                    preSelectedPosition = position;
                }
                else{
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (preSelectedPosition != position) {
                            addressesModelList.get(position).setIs_selected_address(true);
                            addressesModelList.get(preSelectedPosition).setIs_selected_address(false);
                            refreshItem(preSelectedPosition, position);
                            preSelectedPosition = position;
                            Address.selectedAddress = position;
                        }
                    }
                });

            }
            else if(MODE == MANAGE_ADDRESS){
                optionLinearLayout.setVisibility(View.GONE);
                icon.setImageResource(R.drawable.ic_more);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionLinearLayout.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPosition, preSelectedPosition);
                        preSelectedPosition = position;
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshItem(preSelectedPosition, preSelectedPosition);
                        preSelectedPosition = -1;
                    }
                });
            }
        }
    }
}
