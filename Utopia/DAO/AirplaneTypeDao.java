package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.AirplaneType;

public class AirplaneTypeDao extends BaseDAO<AirplaneType> {

    public AirplaneTypeDao(Connection conn) {
        super(conn);
    }

    public void add(AirplaneType airplaneType) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO airplane_type VALUES (?, ?)",
                new Object[] { airplaneType.getId(), airplaneType.getMaxCapacity() });
    }

    public void addToEnd(AirplaneType airplaneType) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `airplane_type`)", null);
        save("insert into airplane_type values (@max_id + 1, ?)", new Object[] { airplaneType.getMaxCapacity() });
    }

    public void update(AirplaneType airplaneType) throws SQLException, ClassNotFoundException {
        this.save("UPDATE airplane_type SET max_capacity = ? where id = ?",
                new Object[] { airplaneType.getMaxCapacity(), airplaneType.getId() });
    }

    public void delete(AirplaneType airplaneType) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM airplane_type WHERE id = ?", new Object[] { airplaneType.getId() });
    }

    public List<AirplaneType> readAllAirplaneTypes() throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM airplane_type", null);
    }

    public List<AirplaneType> readAirplaneTypesByMaxCapacity(Integer maxCapacity)
            throws SQLException, ClassNotFoundException {
        return read("SELECT * FROM airplane_type WHERE max_capacity = ?", new Object[] { maxCapacity });
    }

    public AirplaneType readAirplaneTypeById(Integer airplaneTypeId) throws SQLException, ClassNotFoundException {
        return this.readSingle("SELECT * from airplane_type where id = ?", new Object[] { airplaneTypeId });
    }

    @Override
    public List<AirplaneType> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<AirplaneType> airplaneTypeList = new ArrayList<>();
        while (rs.next()) {
            AirplaneType airplaneType = new AirplaneType();
            airplaneType.setId(rs.getInt("id"));
            airplaneType.setMaxCapacity(rs.getInt("max_capacity"));
            airplaneTypeList.add(airplaneType);
        }
        return airplaneTypeList;
    }

    @Override
    public AirplaneType extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            AirplaneType airplaneType = new AirplaneType();
            airplaneType.setId(rs.getInt("id"));
            airplaneType.setMaxCapacity(rs.getInt("max_capacity"));
            return airplaneType;
        }
        return null;
    }

}