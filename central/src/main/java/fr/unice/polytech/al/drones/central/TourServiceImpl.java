package fr.unice.polytech.al.drones.central;

import javax.ws.rs.core.Response;

/**
 * Created by user on 05/11/2015.
 */
public class TourServiceImpl implements TourService {

    public Response getTour() {
        return Response.ok().build();
    }

    public Response newTour() {
        return Response.ok().build();
    }
}
