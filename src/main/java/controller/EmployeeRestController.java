package controller;

import dao.EmployeeDao;
import representations.Employee;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}
