package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImplementation implements DistanceService {

    private static final String OSRM_API = "http://router.project-osrm.org/route/v1/driving/";
    @Override
    public double calculateDistance(Point src, Point dest) {
        try {

            String uri = src.getX() + "," + src.getY() + ";" + dest.getX() + "," + dest.getY() + "?overview=false";
            OSRMResponseDTO osrmResponseDTO =RestClient.builder()
                    .baseUrl(OSRM_API)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDTO.class);

            return osrmResponseDTO.getRoutes().get(0).getDistance() / 1000;
        }
        catch (Exception e){
            throw new RuntimeException("Error fetching data from OSRM "+e.getLocalizedMessage());
        }

    }

}
@Data
class OSRMResponseDTO{
    private List<OSRMRoutes> routes;
}
@Data
class OSRMRoutes{
    private Double distance;
}
