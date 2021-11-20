package com.kuang.dao;

import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //主键自增
    private static Integer initId = 1006;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001, new Employee(1001, "AA1", "24734674@qq.com", 1, new Department(101, "教学部"), new Date()));
        employees.put(1002, new Employee(1002, "AA2", "24734674@qq.com", 0, new Department(102, "市场部"), new Date()));
        employees.put(1003, new Employee(1003, "AA3", "24734674@qq.com", 1, new Department(103, "教研部"), new Date()));
        employees.put(1004, new Employee(1004, "AA4", "24734674@qq.com", 0, new Department(104, "运营部"), new Date()));
        employees.put(1005, new Employee(1005, "AA5", "24734674@qq.com", 1, new Department(105, "后勤部"), new Date()));
    }

    @Autowired
    private DepartmentDao departmentDao;

    //增加一位员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //查询全部员工
    public Collection<Employee> getAll() {
        return employees.values();
    }

    //通过ID查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //通过ID删除一位员工
    public void deleteEmployeeById(Integer id) {
        employees.remove(id);
    }

}

