package controller;

import dao.EmployeeDao;
import representations.Employee;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
