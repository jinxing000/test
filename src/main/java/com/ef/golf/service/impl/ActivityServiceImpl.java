package com.ef.golf.service.impl;

import com.ef.golf.mapper.ActivityMapper;
import com.ef.golf.pojo.Activity;
import com.ef.golf.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl  implements ActivityService{

	@Autowired
	ActivityMapper ActivityMapper;
	@Override
	public Activity getActivity() {
		return ActivityMapper.getActivity();
	}
}
