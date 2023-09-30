package de.ait.ec.services;

import de.ait.ec.dto.NewUserDto;
import de.ait.ec.dto.UserDto;

public interface UserService {

  UserDto register(NewUserDto newUserDto);
}
