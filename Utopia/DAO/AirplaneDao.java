package Utopia.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utopia.Domain.Airplane;

public class AirplaneDao extends BaseDAO<Airplane> {

    public AirplaneDao(Connection conn) {
        super(conn);
    }

    public void add(Airplane airplane) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO airplane VALUES (?, ?)", new Object[] { airplane.getId(), airplane.getTypeId() });
    }

    public void addToEnd(Airplane airplane) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(id) FROM `tbl_airplane`)", null);
        save("insert into airplane values (@max_id + 1, ?)", new Object[] { airplane.getTypeId() });
    }

    public void update(Airplane airplane) throws SQLException, ClassNotFoundException {
        this.save("UPDATE airplane SET type_id = ? where id = ?",
                new Object[] { airplane.getTypeId(), airplane.getId() });
    }

    public void delete(Airplane airplane) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM airplane WHERE id = ?", new Object[] { airplane.getId() });
    }

    public List<Airplane> readAllAirplanes() throws SQLException, ClassNotFoundException {
        return read("select * from airplane", null);
    }

    public Airplane readAirplanesByTypeId(Integer typeId) throws SQLException, ClassNotFoundException {
        return this.readSingle("select * from airplane where type_id = ?", new Object[] { typeId });
    }

    public Airplane readAirplaneById(Integer airplaneId) throws SQLException, ClassNotFoundException {
        return this.readSingle("select * from airplane where id = ?", new Object[] { airplaneId });
    }

    @Override
    public List<Airplane> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Airplane> airplaneList = new ArrayList<>();
        while (rs.next()) {
            Airplane airplane = new Airplane();
            airplane.setId(rs.getInt("id"));
            airplane.setTypeId(rs.getInt("type_id"));
            airplaneList.add(airplane);
        }
        return airplaneList;
    }

    @Override
    public Airplane extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            Airplane airplane = new Airplane();
            airplane.setId(rs.getInt("id"));
            airplane.setTypeId(rs.getInt("type_id"));
            return airplane;
        }
        return null;
    }

}