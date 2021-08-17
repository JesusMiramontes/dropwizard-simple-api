package controller;

import javax.validation.Validator;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestController {

    private final Validator validator;

    public EmployeeRestController(Validator validator) {
        this.validator = validator;
    }
}
