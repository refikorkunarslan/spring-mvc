package com.spring.springboot.caseStudy.controller;

import com.spring.springboot.caseStudy.entity.Employee;
import com.spring.springboot.caseStudy.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService theEmployeeService)
    {
        employeeService=theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel)
    {
        List<Employee> theEmployees =employeeService.findAll();
        theModel.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }
    @GetMapping("/AddEmp")
    public String AddEmp(Model theModel) {


        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

   @PostMapping("/save")
   public String saveEmployee(@ModelAttribute("employee") Employee theEmployee)
   {
       employeeService.save(theEmployee);
       return "redirect:/employees/list";
   }

    @GetMapping("/UpdateEmp")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {


        Employee theEmployee = employeeService.findById(theId);


        theModel.addAttribute("employee", theEmployee);


        return "employees/employee-form";
    }

    @GetMapping("/delete")

    public String delete(@RequestParam("employeeId") int theId) {

        employeeService.deleteById(theId);
        return "redirect:/employees/list";

    }

    @GetMapping("/{employeeId}")
    @ResponseBody
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }


}
