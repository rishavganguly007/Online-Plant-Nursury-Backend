package com.cg.onlineplantnursery.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineplantnursery.Entity.Admin;
import com.cg.onlineplantnursery.Exception.AdminFoundException;
import com.cg.onlineplantnursery.Exception.AdminNotFoundException;
import com.cg.onlineplantnursery.Repository.IAdminRepository;

@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	private IAdminRepository adminrepo;

	@Override
	public Admin addAdmin(Admin admin) {
		Admin obj = adminrepo.findByAdminUsername(admin.getAdminUsername());
		if(obj != null)
			throw new AdminFoundException("Admin already created");
		return adminrepo.save(admin);
	}

	@Override
	public boolean validateAdmin(String username, String password) {
		Optional<Admin> admin = adminrepo.findByAdminUsernameAndAdminpassword(username, password);
		if(admin.get() == null)
			throw new AdminNotFoundException("Admin not created");
		else
			return true;
	}

	@Override
	public Optional<Admin> viewByAdminUserName(String username, String password) {
		Optional<Admin> admin = adminrepo.findByAdminUsernameAndAdminpassword(username, password);
		if(admin.get() == null)
			throw new AdminNotFoundException("Admin not created");
		return admin;		
	}

}
