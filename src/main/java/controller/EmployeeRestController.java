package controller;

import dao.EmployeeDao;
import representations.Employee;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestController {

    private final Validator validator;

    public EmployeeRestController(Validator validator) {
        this.validator = validator;
    }

    @GET
    public Response getEmployees(){
        return Response.ok(EmployeeDao.getEmployees()).build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Integer id){
        Employee employee = EmployeeDao.getEmployee(id);
        if(employee != null)
            return Response.ok(employee).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createEmployee(Employee employee) throws URISyntaxException {
        // Validation
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Employee e = EmployeeDao.getEmployee(employee.getId());

        if(violations.size() > 0){
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Employee>violation : violations){
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }

        if(e == null){
            EmployeeDao.updateEmployee(employee.getId(), employee);
            return Response.created(new URI("/employees/" + employee.getId())).build();
        } else {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") Integer id, Employee employee){
        // validation
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Employee e = EmployeeDao.getEmployee(employee.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Employee> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            employee.setId(id);
            EmployeeDao.updateEmployee(id, employee);
            return Response.ok(employee).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployeeById(@PathParam("id") Integer id){
        Employee employee = EmployeeDao.getEmployee(id);
        if(employee != null){
            EmployeeDao.removeEmployee(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
