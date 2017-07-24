package kr.or.connect.jgb.controller.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.jgb.domain.ReservationInfo;
import kr.or.connect.jgb.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationApiController {
	
	ReservationService reservationService;

	@Autowired
	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@PostMapping
    public String create(@RequestBody ReservationInfo reservationInfo) {
        ReservationInfo reser = reservationService.addReservation(reservationInfo);
        System.out.println(reser.getReservationName());
        return "redirect:/";
    }
	
	
	
	
}