package csaba79coder.contorller;

import csaba79coder.service.TruckLoadingService;

// annotations using lombok!
public class TruckLoadingController {

    private final TruckLoadingService truckLoadingService;

    public TruckLoadingController(TruckLoadingService truckLoadingService) {
        this.truckLoadingService = truckLoadingService;
    }

    /*@PostMapping - something like this! must fit into our Spring Boot application
    public String optimizePacking(@RequestBody PackingRequest request) {
        return packingService.packBoxes(request.getTruck(), request.getBoxes());
    }*/
}
