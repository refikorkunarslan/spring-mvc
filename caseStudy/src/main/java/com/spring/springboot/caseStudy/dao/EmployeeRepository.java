package com.spring.springboot.caseStudy.dao;

import com.spring.springboot.caseStudy.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {



}
