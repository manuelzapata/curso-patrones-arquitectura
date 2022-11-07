package course.microservices.reservation.rest;

import course.microservices.reservation.business.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationRESTService {

    private ReservationService service;

    public ReservationRESTService() {
        service = new ReservationService();
    }

    @RequestMapping("/check")
    public boolean checkCancellation(@RequestParam(value = "days") int days,
                                     @RequestParam(value = "propertyType") int propertyType){

        boolean result = service.couldCancel(days, propertyType);
        return result;

    }
}
