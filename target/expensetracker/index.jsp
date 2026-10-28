<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal Expense Tracker</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f2f5fa;
            color: #222;
        }

        header {
            background: #263b80;
            color: white;
            padding: 24px;
            text-align: center;
        }

        header h1 {
            margin: 0 0 8px;
        }

        .container {
            max-width: 650px;
            margin: 35px auto;
            padding: 25px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 5px 20px #00000012;
        }

        h2 {
            color: #263b80;
            margin-top: 0;
        }

        label {
            display: block;
            margin-top: 16px;
            font-weight: bold;
        }

        input, select, textarea {
            width: 100%;
            padding: 12px;
            margin-top: 7px;
            border: 1px solid #ccd2df;
            border-radius: 6px;
            font-size: 15px;
        }

        button {
            width: 100%;
            margin-top: 22px;
            padding: 13px;
            border: none;
            border-radius: 6px;
            background: #263b80;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background: #19295e;
        }

        .links {
            text-align: center;
            margin-top: 20px;
        }

        .links a {
            color: #263b80;
            margin: 0 10px;
            text-decoration: none;
        }

        @media (max-width: 700px) {
            .container {
                margin: 20px 12px;
                padding: 20px;
            }
        }
    </style>
</head>

<body>

<header>
    <h1>Personal Expense Tracker</h1>
    <p>Manage your daily expenses easily</p>
</header>

<div class="container">
    <h2>Add New Expense</h2>

    <form action="addExpense" method="post"
          onsubmit="return validateForm()">

        <label for="exp_date">Expense Date</label>
        <input type="date" id="exp_date" name="exp_date" required>

        <label for="category">Category</label>
        <select id="category" name="category" required>
            <option value="">-- Select Category --</option>
            <option value="Food">Food</option>
            <option value="Travel">Travel</option>
            <option value="Shopping">Shopping</option>
            <option value="Education">Education</option>
            <option value="Bills">Bills</option>
            <option value="Health">Health</option>
            <option value="Other">Other</option>
        </select>

        <label for="amount">Amount (₹)</label>
        <input type="number" id="amount" name="amount"
               placeholder="Enter amount"
               min="0.01" step="0.01" required>

        <label for="note">Note</label>
        <textarea id="note" name="note" rows="3"
                  maxlength="255"
                  placeholder="Enter expense details"></textarea>

        <button type="submit">Save Expense</button>
    </form>

    <div class="links">
        <a href="expenses">View Expenses</a>
        <a href="summary">Monthly Summary</a>
    </div>
</div>

<script>
    // Set today's date automatically
    document.getElementById("exp_date").value =
        new Date().toLocaleDateString("en-CA");

    function validateForm() {
        const amount = parseFloat(
            document.getElementById("amount").value
        );

        if (!Number.isFinite(amount) || amount <= 0) {
            alert("Please enter an amount greater than zero.");
            return false;
        }

        if (document.getElementById("category").value === "") {
            alert("Please select a category.");
            return false;
        }

        return true;
    }
</script>

</body>
</html>
