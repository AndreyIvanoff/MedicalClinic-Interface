package ru.hospital.api;

import com.google.inject.Inject;
import com.google.inject.internal.Nullable;
import com.google.inject.name.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import ru.hospital.filter.InjectInfo;
import ru.hospital.manager.AdministratorManager;
import ru.hospital.model.Administrator;
import ru.hospital.utils.ConstLiteral;

@Path("/admin")
@Consumes(ConstLiteral.FORMAT_APPLICATION_JSON_UTF8)
@Produces(ConstLiteral.FORMAT_APPLICATION_JSON_UTF8)
public class AdminService {

	@Inject
	@Nullable
	@Named("info")
	private InjectInfo info;

	@PUT
	@Path("/create/administrator")
	public Response createAdmin(Administrator administrator){
		Long id = AdministratorManager.registerAdministrator(administrator, info.getUserId());
		return Response.ok(id).build();
	}

	@POST
	@Path("/update/administrator")
	public Response updateAdmin(Administrator administrator){
		Long id = AdministratorManager.updateAdministrator(administrator, info.getUserId());
		return Response.ok(id).build();
	}

	@GET
	@Path("/get/administrator")
	public Response getAdmin(Administrator administrator){
		Administrator result = AdministratorManager.getAdministrator(administrator.getId());
		return Response.ok(result).build();
	}

}
