package com.expense;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/expenses")
public class ViewExpensesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Expense> expenses = new ArrayList<>();

        String sql = "SELECT id, exp_date, category, amount, note "
                   + "FROM expense ORDER BY exp_date DESC, id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Expense expense = new Expense();

                expense.setId(rs.getInt("id"));
                expense.setExpDate(rs.getDate("exp_date"));
                expense.setCategory(rs.getString("category"));
                expense.setAmount(rs.getBigDecimal("amount"));
                expense.setNote(rs.getString("note"));

                expenses.add(expense);
            }

            request.setAttribute("expenses", expenses);

            request.getRequestDispatcher("/expenses.jsp")
                   .forward(request, response);

        } catch (java.sql.SQLException e) {
            throw new ServletException(
                "Unable to load expenses.", e);
        }
    }
}
