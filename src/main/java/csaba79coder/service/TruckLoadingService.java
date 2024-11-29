package csaba79coder.service;

import csaba79coder.model.Cargo;
import csaba79coder.model.Truck;

import java.util.ArrayList;
import java.util.List;

public class TruckLoadingService {

    public String loadCargo(Truck truck, List<Cargo> cargos) {
        List<String> output = new ArrayList<>();
        List<String> cannotFitBoxes = new ArrayList<>();
        int currentWeight = 0;

        // Súly szerinti rendezés
        cargos.sort((b1, b2) -> Integer.compare(b2.getWeight(), b1.getWeight()));

        int x = 0, y = 0, z = 0;

        for (Cargo cargo : cargos) {
            if (x + cargo.getWidth() <= truck.getWidth() &&
                    y + cargo.getLength() <= truck.getLength() &&
                    z + cargo.getHeight() <= truck.getHeight() &&
                    currentWeight + cargo.getWeight() <= truck.getMaxWeight()) {

                // Doboz elhelyezése
                cargo.setStartX(x);
                cargo.setStartY(y);
                cargo.setStartZ(z);
                cargo.setEndX(x + cargo.getWidth());
                cargo.setEndY(y + cargo.getLength());
                cargo.setEndZ(z + cargo.getHeight());

                output.add("Box #" + cargo.getId() + " weight: " + cargo.getWeight() +
                        " starts at (" + cargo.getStartX() + ", " + cargo.getStartY() + ", " + cargo.getStartZ() +
                        ") and ends at (" + cargo.getEndX() + ", " + cargo.getEndY() + ", " + cargo.getEndZ() + ")");

                currentWeight += cargo.getWeight();
                y += cargo.getLength();

                if (y >= truck.getLength()) {
                    y = 0;
                    x += cargo.getWidth();
                    if (x >= truck.getWidth()) {
                        x = 0;
                        z += cargo.getHeight();
                    }
                }
            } else {
                cannotFitBoxes.add("Box #" + cargo.getId());
            }
        }

        // Eredmény összeállítása
        StringBuilder result = new StringBuilder("Successfully packed boxes:\n");
        output.forEach(result::append);
        result.append("\nCannot fit boxes:\n");
        cannotFitBoxes.forEach(result::append);
        result.append("\nTotal weight of packed boxes: ").append(currentWeight).append(" kg");
        return result.toString();
    }
}
