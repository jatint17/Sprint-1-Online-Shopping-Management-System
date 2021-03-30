package com.cg.onlineshoppingms.userms.controller;

import com.cg.onlineshoppingms.userms.dto.UserDetailsResponse;
import com.cg.onlineshoppingms.userms.entity.User;
import com.cg.onlineshoppingms.userms.service.IUserService;
import com.cg.onlineshoppingms.userms.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserControllerImplUnitTesting {
	
	@Mock
	IUserService userService;
	
	@Mock
	UserUtil userUtil;
	
	@Spy
	@InjectMocks
	UserController userController;


	
	@Test
	public void testFindById_1() {
		long userId = 1;
		User user = mock(User.class);
		UserDetailsResponse UserDetails = mock(UserDetailsResponse.class);
		when(userService.findById(userId)).thenReturn(user);
		when(userUtil.toUserDetails(user)).thenReturn(UserDetails);
		UserDetailsResponse userDetails = userController.findById(userId);
		assertSame(UserDetails, userDetails);
		verify(userService).findById(userId);
		verify(userUtil).toUserDetails(user);
	}
}
