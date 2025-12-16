package db;

import model.Node;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NodeDAO {
    public int create(Node node) throws SQLException {
        String sql = "INSERT INTO nodes (title, topic, content, created_at, next_review_date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, node.getTitle());
            ps.setString(2, node.getTopic());
            ps.setString(3, node.getContent());

            // created_at safety
            LocalDate created = (node.getCreatedAt() != null)
                    ? node.getCreatedAt()
                    : LocalDate.now();
            ps.setDate(4, Date.valueOf(created));

            // next_review_date safety ⭐
            LocalDate nextReview = (node.getNextReviewDate() != null)
                    ? node.getNextReviewDate()
                    : LocalDate.now();
            ps.setDate(5, Date.valueOf(nextReview));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return -1;
    }

    public Node findById(int id) throws SQLException {
        String sql = "SELECT * FROM nodes WHERE id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Node n = new Node();
                    n.setId(rs.getInt("id"));
                    n.setTitle(rs.getString("title"));
                    n.setTopic(rs.getString("topic"));
                    n.setContent(rs.getString("content"));
                    Date createdSql = rs.getDate("created_at");
                    Date reviewSql = rs.getDate("next_review_date");

                    n.setCreatedAt(createdSql != null
                            ? createdSql.toLocalDate()
                            : LocalDate.now());

                    n.setNextReviewDate(reviewSql != null
                            ? reviewSql.toLocalDate()
                            : LocalDate.now());

                    return n;
                }
            }
        }
        return null;
    }

    public List<Node> findByTopic(String topic) throws SQLException {
        String sql = "SELECT * FROM nodes WHERE topic = ?";
        List<Node> list = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, topic);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Node n = new Node();
                    n.setId(rs.getInt("id"));
                    n.setTitle(rs.getString("title"));
                    n.setTopic(rs.getString("topic"));
                    n.setContent(rs.getString("content"));
                    Date createdSql = rs.getDate("created_at");
                    Date reviewSql = rs.getDate("next_review_date");

                    n.setCreatedAt(createdSql != null
                            ? createdSql.toLocalDate()
                            : LocalDate.now());

                    n.setNextReviewDate(reviewSql != null
                            ? reviewSql.toLocalDate()
                            : LocalDate.now());

                    list.add(n);
                }
            }
        }
        return list;
    }

    public List<Node> findAll() throws SQLException {
        String sql = "SELECT * FROM nodes";
        List<Node> list = new ArrayList<>();
        try (Statement st = DBConnection.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Node n = new Node();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setTopic(rs.getString("topic"));
                n.setContent(rs.getString("content"));
                Date createdSql = rs.getDate("created_at");
                Date reviewSql = rs.getDate("next_review_date");

                n.setCreatedAt(createdSql != null
                        ? createdSql.toLocalDate()
                        : LocalDate.now());

                n.setNextReviewDate(reviewSql != null
                        ? reviewSql.toLocalDate()
                        : LocalDate.now());

                list.add(n);
            }
        }
        return list;
    }

    public void updateNextReview(int nodeId, LocalDate nextDate) throws SQLException {
        String sql = "UPDATE nodes SET next_review_date = ? WHERE id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(nextDate));
            ps.setInt(2, nodeId);
            ps.executeUpdate();
        }
    }
}