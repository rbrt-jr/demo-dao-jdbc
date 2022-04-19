package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection con;

	public DepartmentDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("INSERT INTO department " + "(Name) " + "VALUES " + "(?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getName());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!!!");
			}
		} catch (SQLException i) {
			throw new DbException(i.getMessage());
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE department " + "SET Name = ?" + "WHERE Id = ?");

			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());

			ps.executeUpdate();

		} catch (SQLException i) {
			throw new DbException(i.getMessage());
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM department " + "WHERE Id = ?");

			ps.setInt(1, id);

			int rowns = ps.executeUpdate();
			if (rowns == 0) {
				throw new DbException("Id invalid");
			}
		} catch (SQLException i) {
			throw new DbException(i.getMessage());
		} finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement("SELECT * FROM department WHERE Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				return obj;
			}
			return null;
		} catch (SQLException i) {
			throw new DbException(i.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement("SELECT * FROM department ORDER BY Name");

			rs = st.executeQuery();

			List<Department> list = new ArrayList<>();

			while (rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				list.add(obj);
			}
			return list;

		} catch (SQLException i) {
			throw new DbException(i.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

}
