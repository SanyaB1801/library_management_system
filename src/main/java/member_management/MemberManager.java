package member_management;

import db.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberManager {
    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (name) VALUES (?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                members.add(new Member(rs.getInt("id"), rs.getString("name")));
            }
        }
        return members;
    }
}
