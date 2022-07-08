package com.uiAutomation.testScripts.samplePage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uiAutomation.helper.database.DatabaseHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class ApplicationDBQuery {

	public int getEmpSalary(int empId) throws NumberFormatException,
			SQLException {
		int salary = 0;
		String dbQuery = "SELECT salary FROM person.employee where idEmployee = "
				+ empId;
		ResultSet result = DatabaseHelper.getResultSet(dbQuery);
		while (result.next()) {
			salary = Integer.parseInt(result.getString("Salary"));
		}
		return salary;
	}

	public List<Employee> getEmployee() throws SQLException {
		List<Employee> data = new ArrayList<Employee>();

		String dbQuery = "SELECT * FROM person.employee";
		ResultSet result = DatabaseHelper.getResultSet(dbQuery);
		while (result.next()) {
			Employee emp = new Employee();
			emp.setEmpId(Integer.parseInt(result.getString("idEmployee"))); // emp.setEmpId(empId);
			emp.setSalary(Integer.parseInt(result.getString("Salary")));
			emp.setName(result.getString("Name"));
			emp.setAddress(result.getString("Address"));
			data.add(emp);
		}
		return data;
	}

	public static void main(String[] args) throws NumberFormatException,
			SQLException {
		ApplicationDBQuery appDb = new ApplicationDBQuery();

		// To Test method getEmpSalary(int empId)
		int Salary = appDb.getEmpSalary(15);
		System.out.println(Salary);

		// To Test method getEmployee()
		List<Employee> empList = appDb.getEmployee();
		for (Employee employee : empList) {
			// System.out.println(empList);
			System.out.println("empId is: " + employee.getEmpId()
					+ " empName is: " + employee.getName() + " empSalary is: "
					+ employee.getSalary() + "empAddress is: "
					+ employee.getAddress());
		}

	}
}
