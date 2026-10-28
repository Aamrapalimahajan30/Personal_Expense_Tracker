package com.expense;

import java.math.BigDecimal;
import java.sql.Date;

public class Expense {

    private int id;
    private Date expDate;
    private String category;
    private BigDecimal amount;
    private String note;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getExpDate() { return expDate; }
    public void setExpDate(Date expDate) { this.expDate = expDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
