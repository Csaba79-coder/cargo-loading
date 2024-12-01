package aaa;

import java.util.ArrayList;
import java.util.List;

class Box {
    int id;
    int weight;
    int width;
    int length;
    int height;

    public Box(int id, int weight, int width, int length, int height) {
        this.id = id;
        this.weight = weight;
        this.width = width;
        this.length = length;
        this.height = height;
    }
}

public class TruckPacking3 {
    // Truck dimensions
    static int truckWidth = 50;
    static int truckLength = 50;
    static int truckHeight = 200;

    public static void main(String[] args) {
        // List of boxes
        List<Box> boxes = new ArrayList<>();
        boxes.add(new Box(1, 50, 15, 15, 40));
        boxes.add(new Box(2, 30, 15, 15, 40));
        boxes.add(new Box(3, 30, 15, 15, 40));
        boxes.add(new Box(4, 50, 15, 15, 40));
        boxes.add(new Box(5, 30, 15, 15, 40));
        boxes.add(new Box(6, 30, 15, 15, 40));
        boxes.add(new Box(7, 50, 15, 15, 40));
        boxes.add(new Box(8, 30, 15, 15, 40));
        boxes.add(new Box(9, 30, 15, 15, 40));
        boxes.add(new Box(10, 30, 15, 15, 40));
        boxes.add(new Box(11, 30, 15, 15, 40));
        boxes.add(new Box(12, 50, 15, 15, 40));
        boxes.add(new Box(13, 30, 15, 15, 40));
        boxes.add(new Box(14, 30, 15, 15, 40));
        boxes.add(new Box(15, 50, 15, 15, 40));
        boxes.add(new Box(16, 30, 15, 15, 40));
        boxes.add(new Box(17, 30, 15, 15, 40));
        boxes.add(new Box(18, 30, 15, 15, 40));

        // Packing the boxes into the truck
        packBoxes(boxes);
    }

    public static void packBoxes(List<Box> boxes) {
        int x = 0, y = 0, z = 0; // Starting coordinates
        int layer = 1; // Truck layer

        // Iterate through the boxes to pack them
        for (Box box : boxes) {
            // Check if the box fits in the current layer (width and length check)
            if (x + box.width > truckWidth) {
                x = 0; // Reset x-coordinate (move to the next row)
                y += box.length; // Move to the next row in the current layer

                // Check if the box fits in the truck's length
                if (y + box.length > truckLength) {
                    y = 0; // Reset y-coordinate (move to the next column)
                    z += box.height; // Move to a new layer
                    layer++; // Increase layer number

                    // If there's no space in the next layer, stop
                    if (z + box.height > truckHeight) {
                        System.out.println("No more space in the truck for Box #" + box.id);
                        break;
                    }
                }
            }

            // Output the box placement: starts and ends positions (x, y, z)
            System.out.println(
                    "Box #" + box.id + " weight: " + box.weight + " starts at (" +
                            x + ", " + y + ", " + z + ") and ends at (" +
                            (x + box.width) + ", " + (y + box.length) + ", " + (z + box.height) +
                            ") in Truck layer: " + layer
            );

            // Update the x-coordinate for the next box
            x += box.width;
        }
    }
}
