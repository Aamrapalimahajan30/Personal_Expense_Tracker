package com.expense;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;

@WebServlet("/addExpense")
public class ExpenseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String date = request.getParameter("exp_date");
        String category = request.getParameter("category");
        String amountText = request.getParameter("amount");
        String note = request.getParameter("note");

        try {
            java.sql.Date expDate = java.sql.Date.valueOf(date);
            BigDecimal amount = new BigDecimal(amountText);

            if (category == null || category.trim().isEmpty()
                    || amount.compareTo(BigDecimal.ZERO) <= 0) {
                response.sendError(400, "Invalid expense details.");
                return;
            }

            String sql = "INSERT INTO expense "
                    + "(exp_date, category, amount, note) "
                    + "VALUES (?, ?, ?, ?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setDate(1, expDate);
                ps.setString(2, category.trim());
                ps.setBigDecimal(3, amount);
                ps.setString(4, note == null ? "" : note.trim());

                ps.executeUpdate();
            }

            response.sendRedirect("expenses");

        } catch (IllegalArgumentException e) {
            response.sendError(
                    400, "Please enter a valid date and amount.");
        } catch (SQLException e) {
            throw new ServletException(
                    "Unable to save expense.", e);
        }
    }
}
