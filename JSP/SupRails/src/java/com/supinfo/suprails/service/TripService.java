package com.supinfo.suprails.service;

import com.supinfo.suprails.dao.TripDao;
import com.supinfo.suprails.entity.Trip;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author bargenson
 */
@Stateless
@Path(value="tripserviceRest")
public class TripService {
    
    @EJB
    private TripDao tripDao;
    
    
    public Trip addTrip(Trip trip) {
        return tripDao.addTrip(trip);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)

   public List<Trip> getAllTrips() {
         List<Trip> trips= tripDao.getAllTrips();
        return trips;
    }

    public void removeTrip(Long tripId) {
        tripDao.removeTrip(findTripById(tripId));
    }
    
    public Trip findTripById(Long tripId) {
        return tripDao.findTripById(tripId);
    }

}
