package com.achui.quick.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.achui.quick.common.domain.User;
import com.achui.quick.common.repository.BaseRepository;

@Service
public class UserService extends BaseService<User, Integer>{

	@Autowired
    @Qualifier("userRespository")
    @Override
    public void setBaseRepository(BaseRepository<User, Integer> baseRepository) {
        super.setBaseRepository(baseRepository);
    }
}
