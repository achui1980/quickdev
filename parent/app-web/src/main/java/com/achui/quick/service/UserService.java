package com.achui.quick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.achui.quick.common.repository.BaseRepository;
import com.achui.quick.common.service.BaseService;
import com.achui.quick.domain.User;
import com.achui.quick.repository.UserRepository;

@Service("userService")
@Transactional
public class UserService extends BaseService<User, Integer>{

//	@Autowired
//    @Qualifier("userRespository")
//    @Override
//    public void setBaseRepository(BaseRepository<User, Integer> baseRepository) {
//        super.setBaseRepository(baseRepository);
//    }
	
	@Autowired
    private UserRepository getUserRepository() {
        return (UserRepository) baseRepository;
    }
	
	public User findByUsername(String userName){
		return this.getUserRepository().findByUsername(userName);
	}
}
