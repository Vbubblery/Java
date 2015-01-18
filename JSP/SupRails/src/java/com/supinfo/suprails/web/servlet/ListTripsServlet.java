package com.supinfo.suprails.web.servlet;

import com.supinfo.suprails.entity.TrainStation;
import com.supinfo.suprails.entity.Trip;
import com.supinfo.suprails.service.TrainStationService;
import com.supinfo.suprails.service.TripService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bargenson
 */
@WebServlet(urlPatterns = "/trips")
public class ListTripsServlet extends HttpServlet {

    @EJB
    private TripService tripService;
    @EJB
    private TrainStationService trainStationService;

    @PersistenceContext
    private EntityManager em;

    List<TrainStation> trainStations = new ArrayList<TrainStation>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        trainStations = trainStationService.getAllTrainStations();
        req.setAttribute("trainStations", trainStations);

        List<Trip> trips = tripService.getAllTrips();
        req.setAttribute("trips", trips);

        req.getRequestDispatcher("/jsp/listTrips.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departureParam = request.getParameter("departure");
        String arrivalParam = request.getParameter("arrival");
        String priceParam = request.getParameter("price");

        Long departureId = Long.valueOf(departureParam);
        Long arrivalId = Long.valueOf(arrivalParam);
        Long price = Long.valueOf(priceParam);

        trainStations = trainStationService.getAllTrainStations();
        TrainStation departureStation = trainStationService.findTrainStationById(departureId);
        TrainStation arrivalStation = trainStationService.findTrainStationById(arrivalId);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<Trip> query = criteriaBuilder.createQuery(Trip.class);

        Root<Trip> trip = query.from(Trip.class);

        query.where(criteriaBuilder.and(criteriaBuilder.equal(trip.get("departureStation"), departureStation), 
                criteriaBuilder.equal(trip.get("arrivalStation"), arrivalStation), 
                criteriaBuilder.lessThan(trip.<Long>get("price"), price)));
        
       //query.where(criteriaBuilder.equal(trip.get("arrivalStation"), arrivalStation));
        //query.where(criteriaBuilder.lessThan(trip.<Long>get("price"), price));

        List<Trip> results = em.createQuery(query).getResultList();
      // List<Predicate> predicateList = new ArrayList<Predicate>();   

        request.setAttribute("trips", results);
        request.setAttribute("trainStations", trainStations);

        request.getRequestDispatcher("/jsp/listTrips.jsp").forward(request, response);

    }

}
