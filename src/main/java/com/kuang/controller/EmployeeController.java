package com.kuang.controller;


import com.kuang.dao.DepartmentDao;
import com.kuang.dao.EmployeeDao;
import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;


@Controller
public class EmployeeController {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddpage(Model model) {
        //查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String Add(Employee employee) {
        //添加操作
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        //查出原来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);
        //查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    @PostMapping("/emp/update")
    public String Update(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping("/emp/delete/{id}")
    public String Delete(@PathVariable("id") Integer id) {
        employeeDao.deleteEmployeeById(id);
        return "redirect:/emps";
    }

    @RequestMapping("/out")
    public String loginOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }
}

