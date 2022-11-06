package edu.illinois.cs.cs124.ay2022.mp.models;

import java.util.ArrayList;


import java.util.List;




/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {}

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
  }

  // ID of the place
  private String id;

  public static List<Place> search(final List<Place> places, final String searchInput)  {

    if (places == null || searchInput == null) {
      throw new IllegalArgumentException();
    }
    if (places.size() == 0 || searchInput.isEmpty() || searchInput.isBlank()) {
      return places;
    }

    List<Place> newList = new ArrayList<>();

    String newSearch = searchInput.trim();

    for (int k = 0; k < places.size(); k++) {
      Place test = places.get(k);
      String one = test.getDescription();
      String two = one.replace('.', ' ');
      String three = two.replace('!', ' ');
      String four = three.replace('?', ' ');
      String five = four.replace(',', ' ');
      String six = five.replace(':', ' ');
      String seven = six.replace(';', ' ');
      String eight = seven.replace('/', ' ');


      char[] ch = eight.toCharArray();
      //char[] newCh = new char[];
      int index = 0;
      String ten = "";
      for (int i = 0; i < ch.length; i++) {
        if (Character.isDigit(ch[i]) || Character.isAlphabetic(ch[i]) || Character.isWhitespace(ch[i])) {
          ten += ch[i];
          //newCh[index] = ch[i];
          index++;

        }

      }
      String get = ten;
      String[] get1 = get.split(" ");

      for (int i = 0; i < get1.length; i++) {
        //System.out.println(get1[i]);
        if (get1[i].equalsIgnoreCase(newSearch)) {
          newList.add(places.get(k));
          break;
        }
      }


    }


    return newList;
  }

  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Latitude and longitude of the place
  private double latitude;

  public double getLatitude() {
    return latitude;
  }

  private double longitude;

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private String description;

  public String getDescription() {
    return description;
  }
}
