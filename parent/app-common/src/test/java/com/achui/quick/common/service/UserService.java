package com.achui.quick.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.achui.quick.common.domain.TestUser;
import com.achui.quick.common.repository.BaseRepository;

//@Service
public class UserService extends BaseService<TestUser, Integer>{

	@Autowired
    @Qualifier("userRespository")
    @Override
    public void setBaseRepository(BaseRepository<TestUser, Integer> baseRepository) {
        super.setBaseRepository(baseRepository);
    }
}
