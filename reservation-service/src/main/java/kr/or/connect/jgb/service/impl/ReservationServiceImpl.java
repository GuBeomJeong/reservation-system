package kr.or.connect.jgb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.jgb.dao.ReservationDao;
import kr.or.connect.jgb.domain.ReservationInfo;
import kr.or.connect.jgb.service.ReservationService;


@Service
public class ReservationServiceImpl implements ReservationService{
	private ReservationDao reservationDao;
	
	@Autowired
	ReservationServiceImpl(ReservationDao reservationDao){
		this.reservationDao = reservationDao;
	}

	@Override
	public ReservationInfo addReservation(ReservationInfo reservationInfo) {
		int id = reservationDao.insert(reservationInfo);
		reservationInfo.setId(id);
		return reservationInfo;
	}

}
