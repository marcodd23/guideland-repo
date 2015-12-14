package it.guideland.app.services;

import it.guideland.app.dto.UserRegistrationDTO;
import it.guideland.app.model.Tourist;

public interface TouristService {

	Tourist createTourist(UserRegistrationDTO userDTO);

}
