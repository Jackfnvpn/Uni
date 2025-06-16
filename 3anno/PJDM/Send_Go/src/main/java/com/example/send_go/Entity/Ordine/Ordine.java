package com.example.send_go.Entity.Ordine;

import com.example.send_go.Entity.Spedizione.ListSpedizione;

import java.util.ArrayList;

public class Ordine {
    private int id;
    private double price;
    private int cliente;

    public Ordine(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public Ordine(double price,int cliente) {
        this.price = price;
        this.cliente = cliente;
    }

    public Ordine(int id, double price,int cliente) {
        this.id = id;
        this.price = price;
        this.cliente = cliente;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}


