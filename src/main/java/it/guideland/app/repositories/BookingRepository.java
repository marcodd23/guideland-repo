package it.guideland.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.guideland.app.model.Booking;
import it.guideland.app.repositories.repositoriesCustom.BookingRepositoryCustom;

public interface BookingRepository extends JpaRepository<Booking, Long>, BookingRepositoryCustom{

}
