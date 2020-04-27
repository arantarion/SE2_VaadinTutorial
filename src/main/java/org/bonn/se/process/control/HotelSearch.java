package org.bonn.se.process.control;

import org.bonn.se.model.objects.dto.Hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelSearch {

    Hotel hotel1 = new Hotel("Steigenberger", 1, "Bonn", "Ein wunderschönes Hotel in Bonn");
    Hotel hotel2 = new Hotel("Maritim Hotel", 2, "Bonn", "Ein wunderschönes Hotel in Bonn");
    Hotel hotel3 = new Hotel("Dorf Hotel", 3, "Köln", "Ein wunderschönes Hotel in Köln");

    private final HashMap<Long, Hotel> contacts = new HashMap<>();

    private HotelSearch() {
    }

    public static HotelSearch search = null;

    public static HotelSearch getInstance() {
        if (search == null) search = new HotelSearch();
        return search;
    }

    public List<Hotel> getHotelbyOrt(String ort) {

        ArrayList<Hotel> liste = new ArrayList<>();

        if (ort.equals("Bonn")) {
            liste.add(hotel1);
            liste.add(hotel2);
        }
        if (ort.equals("Köln")) {
            liste.add(hotel3);
        }
        if (ort.equals("")) {
            liste.add(hotel1);
            liste.add(hotel2);
            liste.add(hotel3);
        }
        return liste;

    }

}
