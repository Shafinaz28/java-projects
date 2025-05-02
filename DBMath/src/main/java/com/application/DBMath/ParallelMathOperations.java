package com.application.DBMath;

import java.sql.*;
import java.time.LocalDateTime;

public class ParallelMathOperations {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/calculate"; // Update with your database name
    static final String USER = "postgres"; // Your PostgreSQL username
    static final String PASS = "admin123"; // Your PostgreSQL password

    public static void main(String[] args) {

        Thread subtractThread = new Thread(() -> updateOperation("subtraction"));
        Thread multiplyThread = new Thread(() -> updateOperation("multiplication"));
        Thread divideThread   = new Thread(() -> updateOperation("division"));
        Thread addThread      = new Thread(() -> updateOperation("additional"));

        subtractThread.start();
        multiplyThread.start();
        divideThread.start();
        addThread.start();
    }

    private static void updateOperation(String operation) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery("SELECT * FROM calculate")) {

            while (rs.next()) {
                int A = rs.getInt("a");
                int B = rs.getInt("b");

                // Debug log to check if values are fetched correctly
                System.out.println("Updating row with A = " + A + " and B = " + B);

                LocalDateTime startTime = LocalDateTime.now();
                Thread.sleep(10);  // simulate slight processing delay
                LocalDateTime endTime = LocalDateTime.now();

                switch (operation) {
                    case "subtraction":
                        rs.updateInt("subtraction_result", A - B);
                        rs.updateTimestamp("subtraction_start", Timestamp.valueOf(startTime));
                        rs.updateTimestamp("subtraction_end", Timestamp.valueOf(endTime));
                        break;
                    case "multiplication":
                        rs.updateInt("multiplication_result", A * B);
                        rs.updateTimestamp("multiplication_start", Timestamp.valueOf(startTime));
                        rs.updateTimestamp("multiplication_end", Timestamp.valueOf(endTime));
                        break;
                    case "division":
                        double divisionResult = (B != 0) ? (double) A / B : 0.0;
                        rs.updateDouble("division_result", divisionResult);
                        rs.updateTimestamp("division_start", Timestamp.valueOf(startTime));
                        rs.updateTimestamp("division_end", Timestamp.valueOf(endTime));
                        break;
                    case "additional":
                        rs.updateInt("additional_result", A + B);
                        rs.updateTimestamp("additional_start", Timestamp.valueOf(startTime));
                        rs.updateTimestamp("additional_end", Timestamp.valueOf(endTime));
                        break;
                }

                // Debug log to ensure the row is being updated
                System.out.println("Updated row: " + operation + " result for A = " + A + " and B = " + B);

                rs.updateRow(); // Save updates to the row
            }

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
