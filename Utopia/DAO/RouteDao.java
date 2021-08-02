package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.Route;

public class RouteDao extends BaseDAO<Route> {

    public RouteDao(Connection conn) {
        super(conn);
    }

    public void add(Route route) throws SQLException, ClassNotFoundException {
        save("insert into route (id, origin_id, destination_id) values (?, ?, ?)",
                new Object[] { route.getId(), route.getOriginId(), route.getDestId() });
    }

    public void addToEnd(Route route) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `route`)", null);
        save("insert into route values (@max_id + 1, ?, ?)", new Object[] { route.getOriginId(), route.getDestId() });
    }

    public void update(Route route) throws SQLException, ClassNotFoundException {
        save("update route set origin_id = ?, destination_id = ? where id = ?",
                new Object[] { route.getOriginId(), route.getDestId(), route.getId() });
    }

    public void delete(Route route) throws SQLException, ClassNotFoundException {
        save("delete from route where id = ?", new Object[] { route.getId() });
    }

    public List<Route> readAllRoutes() throws SQLException, ClassNotFoundException {
        return this.read("select * from route", null);
    }

    public List<Route> readRoutesByOriginId(Integer originId) throws SQLException, ClassNotFoundException {
        return this.read("select * from route where origin_id = ?", new Object[] { originId });
    }

    public List<Route> readRoutesByDestId(Integer destId) throws SQLException, ClassNotFoundException {
        return this.read("select * from route where dest_id = ?", new Object[] { destId });
    }

    public Route readRouteById(Integer id) throws SQLException, ClassNotFoundException {
        return readSingle("select * from route where id = ?", new Object[] { id });
    }

    @Override
    public List<Route> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        try {
            List<Route> routeList = new ArrayList<>();
            while (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt("id"));
                route.setOriginId(rs.getString("origin_id"));
                route.setDestId(rs.getString("destination_id"));
                routeList.add(route);
            }
            return routeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Route extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
        try {
            if (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt("id"));
                route.setOriginId(rs.getString("origin_id"));
                route.setDestId(rs.getString("destination_id"));
                return route;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}