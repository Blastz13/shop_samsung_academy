package com.example.shop;

public class AddressesModel {

    private String name;
    private String address;
    private String index;
    private Boolean is_selected_address;

    public AddressesModel(String name, String address, String index, Boolean is_selected_address) {
        this.name = name;
        this.address = address;
        this.index = index;
        this.is_selected_address = is_selected_address;
    }

    public Boolean getIs_selected_address() {
        return is_selected_address;
    }

    public void setIs_selected_address(Boolean is_selected_address) {
        this.is_selected_address = is_selected_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
