package db;

import model.Link;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LinkDAO {
    public int create(Link link) throws SQLException {
        String sql = "INSERT INTO links (from_node, to_node, relation) VALUES (?, ?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, link.getFromNodeId());
            ps.setInt(2, link.getToNodeId());
            ps.setString(3, link.getRelation());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return -1;
    }

    public List<Link> findByFrom(int fromId) throws SQLException {
        String sql = "SELECT * FROM links WHERE from_node = ?";
        List<Link> list = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, fromId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Link l = new Link();
                    l.setId(rs.getInt("id"));
                    l.setFromNodeId(rs.getInt("from_node"));
                    l.setToNodeId(rs.getInt("to_node"));
                    l.setRelation(rs.getString("relation"));
                    list.add(l);
                }
            }
        }
        return list;
    }

    public List<Link> findAll() throws SQLException {
        String sql = "SELECT * FROM links";
        List<Link> list = new ArrayList<>();
        try (Statement st = DBConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Link l = new Link();
                l.setId(rs.getInt("id"));
                l.setFromNodeId(rs.getInt("from_node"));
                l.setToNodeId(rs.getInt("to_node"));
                l.setRelation(rs.getString("relation"));
                list.add(l);
            }
        }
        return list;
    }
}