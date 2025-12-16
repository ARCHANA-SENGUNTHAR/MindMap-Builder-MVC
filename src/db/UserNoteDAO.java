package db;

import model.UserNote;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserNoteDAO {
    public int create(UserNote note) throws SQLException {
        String sql = "INSERT INTO user_notes (node_id, note, created_at) VALUES (?, ?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, note.getNodeId());
            ps.setString(2, note.getNote());
            ps.setTimestamp(3, Timestamp.valueOf(note.getCreatedAt()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return -1;
    }

    public List<UserNote> findByNode(int nodeId) throws SQLException {
        String sql = "SELECT * FROM user_notes WHERE node_id = ?";
        List<UserNote> list = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, nodeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserNote n = new UserNote();
                    n.setId(rs.getInt("id"));
                    n.setNodeId(rs.getInt("node_id"));
                    n.setNote(rs.getString("note"));
                    n.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    list.add(n);
                }
            }
        }
        return list;
    }
}