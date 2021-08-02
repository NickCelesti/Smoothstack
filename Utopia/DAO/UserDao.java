package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.User;

public class UserDao extends BaseDAO<User> {

    public UserDao(Connection conn) {
        super(conn);
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[] { user.getId(), user.getRoleId(), user.getGivenName(), user.getFamilyName(),
                        user.getUsername(), user.getEmail(), user.getPassword(), user.getPhone() });
    }

    public void addToEnd(User user) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `user`)", null);
        save("insert into user values (@max_id + 1, ?, ?, ?, ?, ?, ?, ?)",
                new Object[] { user.getRoleId(), user.getGivenName(), user.getFamilyName(), user.getUsername(),
                        user.getEmail(), user.getPassword(), user.getPhone() });
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        this.save(
                "UPDATE user SET role_id = ?, given_name = ?, family_name = ?, username = ?, email = ?, password = ?, phone = ? where id = ?",
                new Object[] { user.getRoleId(), user.getGivenName(), user.getFamilyName(), user.getUsername(),
                        user.getEmail(), user.getPassword(), user.getPhone(), user.getId() });
    }

    public void delete(User user) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM user WHERE id = ?", new Object[] { user.getId() });
    }

    public List<User> readAllUsers() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM user", null);
    }

    public List<User> readUsersByRoleId(Integer roleId) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM user WHERE role_id = ?", new Object[] { roleId });
    }

    public User readUserById(Integer id) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from user where id = ?", new Object[] { id });
    }

    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setRoleId(rs.getInt("role_id"));
            user.setGivenName(rs.getString("given_name"));
            user.setFamilyName(rs.getString("family_name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setRoleId(rs.getInt("role_id"));
            user.setGivenName(rs.getString("given_name"));
            user.setFamilyName(rs.getString("family_name"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            return user;
        }
        return null;
    }

}