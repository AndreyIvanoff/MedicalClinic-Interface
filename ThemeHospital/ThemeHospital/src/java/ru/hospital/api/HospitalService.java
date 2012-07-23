package ru.hospital.api;

import com.google.inject.Inject;
import com.google.inject.internal.Nullable;
import com.google.inject.name.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import ru.hospital.filter.InjectInfo;
import ru.hospital.manager.DoctorManager;
import ru.hospital.manager.PacientManager;
import ru.hospital.model.Doctor;
import ru.hospital.model.Pacient;
import ru.hospital.utils.ConstLiteral;

@Path("/service")
@Consumes(ConstLiteral.FORMAT_APPLICATION_JSON_UTF8)
@Produces(ConstLiteral.FORMAT_APPLICATION_JSON_UTF8)
public class HospitalService {

	@Inject
	@Nullable
	@Named("info")
	private InjectInfo info;

	@PUT
	@Path("/create/doctor")
	public Response createDoctor(Doctor doctor){
		Long id = DoctorManager.createDoctor(doctor, info.getUserId());
		return Response.ok(id).build();
	}

	@POST
	@Path("/update/doctor")
	public Response updateDoctor(Doctor doctor){
		Long id = DoctorManager.updateDoctor(doctor, info.getUserId());
		return Response.ok(id).build();
	}

	@GET
	@Path("/get/doctor")
	public Response getDoctor(Doctor doctor){
		Doctor result = DoctorManager.getDoctor(doctor.getId());
		return Response.ok(result).build();
	}

	@PUT
	@Path("/create/pacient")
	public Response createPacient(Doctor doctor){
		Long id = DoctorManager.createDoctor(doctor, info.getUserId());
		return Response.ok(id).build();
	}

	@POST
	@Path("/update/pacient")
	public Response updatePacient(Pacient pacient){
		Long id = PacientManager.updatePacient(pacient, info.getUserId());
		return Response.ok(id).build();
	}

	@GET
	@Path("/get/pacient")
	public Response getPacient(Pacient doctor){
		Pacient result = PacientManager.getPacient(doctor.getId());
		return Response.ok(result).build();
	}

}
