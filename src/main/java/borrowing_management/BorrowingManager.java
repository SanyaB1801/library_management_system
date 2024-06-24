package borrowing_management;

import db.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingManager {
    public void borrowBook(int bookId, int memberId) throws SQLException {
        String sql = "INSERT INTO borrowings (book_id, member_id, borrow_date) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, memberId);
            pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            pstmt.executeUpdate();
        }
    }

    public List<Borrowing> getAllBorrowings() throws SQLException {
        List<Borrowing> borrowings = new ArrayList<>();
        String sql = "SELECT * FROM borrowings";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                borrowings.add(new Borrowing(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                ));
            }
        }
        return borrowings;
    }
}
