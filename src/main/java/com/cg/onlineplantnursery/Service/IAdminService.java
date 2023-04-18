package com.cg.onlineplantnursery.Service;

import java.util.Optional;
import com.cg.onlineplantnursery.Entity.Admin;

public interface IAdminService {
	
	public Admin addAdmin(Admin admin);
	
	public boolean validateAdmin(String username, String password);
	
	public Optional<Admin> viewByAdminUserName(String username, String password);

}
