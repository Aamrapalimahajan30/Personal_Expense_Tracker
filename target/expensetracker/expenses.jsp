
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List,com.expense.Expense" %>
<%
    List<Expense> expenses =
        (List<Expense>) request.getAttribute("expenses");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Expenses</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5fa;
            padding: 25px;
        }
        .container {
            max-width: 1100px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            overflow-x: auto;
        }
        h1 { color: #263b80; }
        a {
            display: inline-block;
            margin: 10px 10px 15px 0;
            color: #263b80;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 650px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th { background: #263b80; color: white; }
        tr:nth-child(even) { background: #f5f7fc; }
        .empty { padding: 20px 0; }
    </style>
</head>
<body>
<div class="container">
    <h1>All Expenses</h1>

    <a href="index.jsp">+ Add Expense</a>
    <a href="summary">View Monthly Summary</a>

    <% if (expenses == null || expenses.isEmpty()) { %>
        <p class="empty">No expenses found. Add your first expense!</p>
    <% } else { %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Category</th>
                    <th>Amount (₹)</th>
                    <th>Note</th>
                </tr>
            </thead>
            <tbody>
                <% for (Expense e : expenses) { %>
                    <tr>
                        <td><%= e.getId() %></td>
                        <td><%= e.getExpDate() %></td>
                        <td><%= e.getCategory() %></td>
                        <td><%= e.getAmount() %></td>
                        <td><%= e.getNote() == null ? "" : e.getNote() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } %>
</div>
</body>
</html>
