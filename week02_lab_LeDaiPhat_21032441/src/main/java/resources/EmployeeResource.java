package resources;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.EmployeeService;

import java.util.List;
import java.util.Optional;

@Path("/employees")
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EmployeeResource() {
        employeeService = new EmployeeService();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getEmp(@PathParam("id") long eid) {
       Employee emp = employeeService.findById(eid);
        return Response.ok(emp).build();
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        List<Employee> lst = employeeService.getAll();
        return Response.ok(lst).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response insert(Employee employee) {
        //ResponseEntity
        employeeService.insertEmp(employee);
        return Response.ok(employee).build();
    }
}
