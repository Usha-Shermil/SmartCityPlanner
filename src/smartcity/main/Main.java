package smartcity.main;

import smartcity.graph.Graph;
import smartcity.location.LocationTree;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph();
        LocationTree tree = new LocationTree();
        int choice;

        do {
            System.out.println("\n--- Smart City Route Planner ---");
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections");
            System.out.println("6. Display all locations (Tree)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number (1–7).");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter location name: ");
                    String name = sc.nextLine().trim();
                    if (!name.isEmpty()) {
                        graph.addLocation(name);
                        tree.insert(name);
                        System.out.println("✅ Location '" + name + "' added successfully.");
                    } else {
                        System.out.println("⚠️ Location name cannot be empty!");
                    }
                }
                case 2 -> {
                    System.out.print("Enter location name to remove: ");
                    String name = sc.nextLine().trim();
                    if (graph.getLocations().contains(name)) {
                        graph.removeLocation(name);
                        tree.remove(name);
                        System.out.println("🗑️ Location '" + name + "' removed successfully.");
                    } else {
                        System.out.println("⚠️ Location not found!");
                    }
                }
                case 3 -> {
                    System.out.print("Enter first location: ");
                    String from = sc.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String to = sc.nextLine().trim();
                    if (graph.getLocations().contains(from) && graph.getLocations().contains(to)) {
                        graph.addRoad(from, to);
                        System.out.println("🛣️ Road added between " + from + " and " + to);
                    } else {
                        System.out.println("⚠️ Both locations must exist before adding a road!");
                    }
                }
                case 4 -> {
                    System.out.print("Enter first location: ");
                    String from = sc.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String to = sc.nextLine().trim();
                    if (graph.getLocations().contains(from) && graph.getLocations().contains(to)) {
                        graph.removeRoad(from, to);
                        System.out.println("🧹 Road removed between " + from + " and " + to);
                    } else {
                        System.out.println("⚠️ Both locations must exist before removing a road!");
                    }
                }
                case 5 -> {
                    System.out.println("\n📍 All Connections:");
                    graph.displayConnections();
                }
                case 6 -> {
                    System.out.println("\n🌳 All Locations (in sorted order):");
                    tree.display();
                }
                case 7 -> System.out.println("👋 Exiting program... Thank you!");
                default -> System.out.println("❌ Invalid choice! Please select between 1–7.");
            }
        } while (choice != 7);

        sc.close();
    }
}
