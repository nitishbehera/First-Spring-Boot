package com.test.First_SpringBoot_App.repository;

import com.test.First_SpringBoot_App.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // @Query("SELECT e FROM Employee e WHERE e.city = :city")
    List<Employee> findByCity(String city);


}
