package ru.merion.aqa.homeworks.lesson15;

import ru.merion.aqa.homeworks.lesson15.model.CreateEmployee;
import ru.merion.aqa.homeworks.lesson15.model.Employee;

import java.io.IOException;
import java.util.List;

public class DemoClass {

    public static void main(String[] args) throws IOException {
        String url = "https://x-clients-be.onrender.com";
        String username = "leonardo";
        String password = "leads";

        EmployeeService service = new EmployeeService(url, username, password);

        List<Employee> empsForCompany = service.getByCompanyId(2340);
        System.out.println(empsForCompany.size());

        int id = service.create(new CreateEmployee("D", "E", 2363, "e@mail.ru", "+7985678"));

        Employee newEmp =  service.getById(id);
        System.out.println(newEmp);
    }
}
