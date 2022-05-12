package com.example.cucumber.employee;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeEntity toEmployeeEntity(Employee employee);

    Employee toEmployee(EmployeeEntity employeeEntity);

    PhoneEntity toPhoneEntity(Phone phone);

    Phone toPhone(PhoneEntity phoneEntity);
}
