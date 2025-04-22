package com.veterinary.veterinaryApp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    private String number;

    private boolean balancePaid;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    // Constructores
    public Account() {
    }

    public Account(double balance, String number) {
        this.balance = balance;
        this.number = number;
        this.balancePaid = setBalancePaid(balance);
    }

    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isBalancePaid() {
        return balancePaid;
    }

    public void setBalancePaid(boolean balancePaid) {
        this.balancePaid = balancePaid;
    }

    public boolean setBalancePaid(double balance) {
        this.balance = balance;
        if (balance == 0) {
            this.balancePaid = true;
        }
        return balancePaid;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getUser() {
        return client;
    }

    public void setUser(Client client) {
        this.client = client;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
