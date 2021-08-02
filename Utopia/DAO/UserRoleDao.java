package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.UserRole;

public class UserRoleDao extends BaseDAO<UserRole> {

    public UserRoleDao(Connection conn) {
        super(conn);
    }

    public void add(UserRole userRole) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO user_role VALUES (?, ?)", new Object[] { userRole.getId(), userRole.getName() });
    }

    public void addToEnd(UserRole userRole) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `user_role`)", null);
        save("insert into user_role values (@max_id + 1, ?)", new Object[] { userRole.getName() });
    }

    public void update(UserRole userRole) throws SQLException, ClassNotFoundException {
        this.save("UPDATE user_role SET name = ? where id = ?", new Object[] { userRole.getName(), userRole.getId() });
    }

    public void delete(UserRole userRole) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM user_role WHERE id = ?", new Object[] { userRole.getId() });
    }

    public List<UserRole> readAllUserRoles() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM user_role", null);
    }

    public List<UserRole> readUserRolesByName(String name) throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM user_role WHERE name = ?", new Object[] { name });
    }

    public UserRole readUserRoleById(Integer id) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from user_role where id = ?", new Object[] { id });
    }

    @Override
    public List<UserRole> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<UserRole> airplaneList = new ArrayList<>();
        while (rs.next()) {
            UserRole userRole = new UserRole();
            userRole.setId(rs.getInt("id"));
            userRole.setName(rs.getString("name"));
            airplaneList.add(userRole);
        }
        return airplaneList;
    }

    @Override
    public UserRole extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            UserRole userRole = new UserRole();
            userRole.setId(rs.getInt("id"));
            userRole.setName(rs.getString("name"));
            return userRole;
        }
        return null;
    }

}