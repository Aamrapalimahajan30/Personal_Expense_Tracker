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

@WebServlet("/summary")
public class SummaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String month = request.getParameter("month");

        if (month == null || !month.matches("\\d{4}-\\d{2}")) {
            java.time.LocalDate today = java.time.LocalDate.now();
            month = today.toString().substring(0, 7);
        }

        List<String[]> categoryTotals = new ArrayList<>();
        java.math.BigDecimal monthlyTotal = java.math.BigDecimal.ZERO;

        try (Connection con = DBConnection.getConnection()) {

            String totalSql =
                "SELECT COALESCE(SUM(amount), 0) AS total " +
                "FROM expense WHERE exp_date >= ? AND exp_date < ?";

            java.time.YearMonth selectedMonth =
                java.time.YearMonth.parse(month);

            try (PreparedStatement ps = con.prepareStatement(totalSql)) {
                ps.setDate(1, java.sql.Date.valueOf(
                    selectedMonth.atDay(1)));
                ps.setDate(2, java.sql.Date.valueOf(
                    selectedMonth.plusMonths(1).atDay(1)));

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        monthlyTotal = rs.getBigDecimal("total");
                    }
                }
            }

            String categorySql =
                "SELECT category, SUM(amount) AS total " +
                "FROM expense " +
                "WHERE exp_date >= ? AND exp_date < ? " +
                "GROUP BY category ORDER BY total DESC";

            try (PreparedStatement ps =
                     con.prepareStatement(categorySql)) {

                ps.setDate(1, java.sql.Date.valueOf(
                    selectedMonth.atDay(1)));
                ps.setDate(2, java.sql.Date.valueOf(
                    selectedMonth.plusMonths(1).atDay(1)));

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        categoryTotals.add(new String[] {
                            rs.getString("category"),
                            rs.getBigDecimal("total").toPlainString()
                        });
                    }
                }
            }

            request.setAttribute("month", month);
            request.setAttribute("monthlyTotal", monthlyTotal);
            request.setAttribute("categoryTotals", categoryTotals);

            request.getRequestDispatcher("/summary.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(
                "Unable to load expense summary.", e);
        }
    }
}
