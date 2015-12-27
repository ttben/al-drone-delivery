package fr.unice.polytech.si5.al.projet.truck;

import fr.unice.polytech.si5.al.projet.truck.domain.Deployment;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.DeliveryID;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Etienne Strobbe (07/11/2015).
 */

@RunWith(MockitoJUnitRunner.class)
public class DeploymentTest {

    private Deployment aDeployment;
    private Drone aDrone;
    private Drone anotherDrone;

    @Mock
    private Delivery aDelivery;

    @Mock
    private Delivery anotherDelivery;



    public void setUp(){

        aDrone.addDelivery(aDelivery);
        anotherDrone.addDelivery(anotherDelivery);
        List<Drone> drones = new ArrayList<>();
        drones.add(aDrone);
        drones.add(anotherDrone);
        Map<DeliveryID,List<Drone>> droneAltAssociation = new HashMap<>();
        aDeployment = new Deployment(drones, droneAltAssociation);

    }

    @Test
    public void aDeployment_WhenBuild_ShouldNotThrow(){
        //aDeployment = new Deployment(deliveryList);
    }

    @Test
    public void aDeployment_WhenIsGoneIsCalledOnUnknownDrone_ShouldThrowException(){

    }
}