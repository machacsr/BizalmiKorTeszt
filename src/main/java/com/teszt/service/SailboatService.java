package com.teszt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teszt.entity.Sailboat;
import com.teszt.repository.SailboatRepository;

@Service
public class SailboatService {
	
	private SailboatRepository sailboatRepo;

	@Autowired
	public void setSailboatRepo(SailboatRepository sailboatRepo) {
		this.sailboatRepo = sailboatRepo;
	}

	public List<Sailboat> getSailboats() {
		return sailboatRepo.findAll();
	}

	public String saveSailboat(String sailboatName, byte sailboatCapacity, boolean sailboatState) {
		Sailboat checkSailboat = sailboatRepo.findByName(sailboatName);
		
		if	(checkSailboat != null) {
			return "Már létezik ez a vitorlás!";
		}
		
		Sailboat sailboat = sailboatRepo.save(new Sailboat(sailboatName, sailboatCapacity, sailboatState));
		
		if (sailboat == null)
			return "Hiba a tranzakció közben!";
		
		
		return "Ok!";
	}

	public boolean setSailboatState(String sailboatName, boolean sailboatState) {
		Sailboat sailboat = sailboatRepo.findByName(sailboatName);
		
		if (sailboat == null)
			return false;
		
		Integer returnValue = sailboatRepo.updateSailboatState(sailboatName, sailboatState);
		if (returnValue == 1)
			return true;
		return false;
	}

	public boolean deleteSailboat(String sailboatName) {
		Sailboat sailboat = sailboatRepo.findByName(sailboatName);
		
		if (sailboat == null)
			return false;
		
		sailboatRepo.delete(sailboat);
		
		return true;
	}

	public boolean checkSailboatByName(String sailboatName) {
		Sailboat sailboat = sailboatRepo.findByName(sailboatName);
		
		if (sailboat == null)
			return false;
		
		return true;
	}

	public Sailboat findSailboatByName(String name) {
		Sailboat sailboat = sailboatRepo.findByName(name);
		
		if (sailboat != null) {
			return sailboat;
		}else {
			return null;
		}
	}
	
}
