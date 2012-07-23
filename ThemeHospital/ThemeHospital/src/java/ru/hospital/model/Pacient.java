package ru.hospital.model;

import com.google.appengine.api.datastore.Entity;
import ru.hospital.model.base.HumanEntityExtension;

public class Pacient extends HumanEntityExtension{

	public Pacient(Entity entity){
		super(entity);
	}

}
