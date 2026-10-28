
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
    String month = (String) request.getAttribute("month");
    java.math.BigDecimal total =
        (java.math.BigDecimal) request.getAttribute("monthlyTotal");
    List<String[]> categories =
        (List<String[]>) request.getAttribute("categoryTotals");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Expense Summary</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5fa;
            padding: 25px;
        }
        .container {
            max-width: 850px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 12px;
        }
        h1, h2 { color: #263b80; }
        .total {
            background: #263b80;
            color: white;
            padding: 22px;
            border-radius: 8px;
            margin: 20px 0;
        }
        .amount { font-size: 28px; font-weight: bold; }
        input, button {
            padding: 10px;
            margin: 8px 5px 8px 0;
        }
        button {
            background: #263b80;
            color: white;
            border: 0;
            cursor: pointer;
        }
        table { width: 100%; border-collapse: collapse; }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th { background: #263b80; color: white; }
        a { color: #263b80; margin-right: 15px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Expense Summary</h1>

    <a href="index.jsp">Add Expense</a>
    <a href="expenses">View All Expenses</a>

    <form action="summary" method="get">
        <label for="month">Select Month:</label>
        <input type="month" id="month" name="month"
               value="<%= month %>" required>
        <button type="submit">View Summary</button>
    </form>

    <div class="total">
        <div>Total Expenses for <%= month %></div>
        <div class="amount">₹ <%= total.toPlainString() %></div>
    </div>

    <h2>Category-Wise Totals</h2>

    <% if (categories == null || categories.isEmpty()) { %>
        <p>No expenses found for this month.</p>
    <% } else { %>
        <table>
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Total Amount (₹)</th>
                </tr>
            </thead>
            <tbody>
                <% for (String[] row : categories) { %>
                    <tr>
                        <td><%= row[0] %></td>
                        <td>₹ <%= row[1] %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } %>
</div>
</body>
</html>
