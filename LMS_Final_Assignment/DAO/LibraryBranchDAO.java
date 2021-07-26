package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.Domain.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {

    public LibraryBranchDAO(Connection conn) {
        super(conn);
    }

    public void add(LibraryBranch branch) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO tbl_library_branch VALUES (?, ?, ?)",
                new Object[] { branch.getId(), branch.getName(), branch.getAddress() });
    }

    public void addToEnd(LibraryBranch branch) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(branchId) FROM `tbl_library_branch`)", null);
        save("insert into tbl_library_branch values (@max_id + 1, ?, ?)",
                new Object[] { branch.getName(), branch.getAddress() });
    }

    public void update(LibraryBranch branch) throws SQLException, ClassNotFoundException {
        this.save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? where branchId = ?",
                new Object[] { branch.getName(), branch.getAddress(), branch.getId() });
    }

    public void delete(LibraryBranch branch) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[] { branch.getId() });
    }

    public List<LibraryBranch> readAllBranches() throws SQLException, ClassNotFoundException {
        return read("select * from tbl_library_branch", null);
    }

    public LibraryBranch readBranchFromId(Integer id) throws SQLException, ClassNotFoundException {
        return readSingle("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[] { id });
    }

    @Override
    List<LibraryBranch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
        List<LibraryBranch> branches = new ArrayList<>();
        while (rs.next()) {
            LibraryBranch branch = new LibraryBranch();
            branch.setId(rs.getInt("branchId"));
            branch.setName(rs.getString("branchName"));
            branch.setAddress(rs.getString("branchAddress"));
            branches.add(branch);
        }
        return branches;
    }

    @Override
    LibraryBranch extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
        if (rs.next()) {
            LibraryBranch branch = new LibraryBranch();
            branch.setId(rs.getInt("branchId"));
            branch.setName(rs.getString("branchName"));
            branch.setAddress(rs.getString("branchAddress"));
            return branch;
        }
        return null;
    }

    // @Override
    // List<LibraryBranch> extract(ResultSet resultSet) throws SQLException,
    // ClassNotFoundException {
    // return null;
    // }

}