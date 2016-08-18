package com.anokmik.tripassistant;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.anokmik.persistence.model.PhotoAttachment;
import com.anokmik.persistence.model.Trip;
import com.anokmik.persistence.model.TripEvent;
import com.anokmik.persistence.model.User;
import com.anokmik.persistence.repository.mock.MockRepository;
import com.anokmik.persistence.util.TypeEventUtils;
import com.anokmik.tripassistant.databinding.ComponentProvider;
import com.anokmik.tripassistant.util.DateUtils;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.ArrayList;
import java.util.List;

public final class TripAssistantApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).openDatabasesOnInit(true).build());
        DataBindingUtil.setDefaultComponent(new ComponentProvider());
        if (BuildConfig.DEBUG) {
            new InitMockData().start();
        }
    }

    private static class InitMockData extends Thread {

        private final MockRepository<Trip> tripsMockRepository;
        private final MockRepository<TripEvent> tripEventsMockRepository;
        private final MockRepository<PhotoAttachment> photoAttachmentMockRepository;

        private InitMockData() {
            this.tripsMockRepository = new MockRepository<>(Trip.class);
            this.tripEventsMockRepository = new MockRepository<>(TripEvent.class);
            this.photoAttachmentMockRepository = new MockRepository<>(PhotoAttachment.class);
        }

        @Override
        public void run() {
            super.run();
            if (tripsMockRepository.count() == 0) {
                String descriptionParis = "Paris is the home of the most visited art museum in the world, the Louvre, as well as the Musée d'Orsay, noted for its collection of French Impressionist art, and the Musée National d'Art Moderne, a museum of modern and contemporary art.";
                String descriptionDublin = "Founded as a Viking settlement, the Kingdom of Dublin became Ireland's principal city following the Norman invasion.";
                String descriptionReykjavik = "Reykjavík is believed to be the location of the first permanent settlement in Iceland, which, according to Ingólfur Arnarson, was established in AD 874. Until the 19th century, there was no urban development in the city location.";
                String descriptionBarcelona = "Barcelona is one of the world's leading tourist, economic, trade fair and cultural centers, and its influence in commerce, education, entertainment, media, fashion, science, and the arts all contribute to its status as one of the world's major global cities.";
                String descriptionLondon = "London contains four World Heritage Sites: the Tower of London; Kew Gardens; the site comprising the Palace of Westminster, Westminster Abbey, and St Margaret's Church; and the historic settlement of Greenwich (in which the Royal Observatory, Greenwich marks the Prime Meridian, 0° longitude, and GMT).";
                String descriptionBerlin = "Modern Berlin is home to world renowned universities, orchestras, museums, entertainment venues and is host to many sporting events. Its urban setting has made it a sought-after location for international film productions.";

                List<Trip> trips = new ArrayList<>();
                trips.add(newTrip("Paris", descriptionParis, DateUtils.toTime("11/01/2014"), DateUtils.toTime("23/01/2014")));
                trips.add(newTrip("Prague", null, DateUtils.toTime("30/04/2014"), DateUtils.toTime("09/05/2014")));
                trips.add(newTrip("Dublin", descriptionDublin, DateUtils.toTime("22/08/2014"), DateUtils.toTime("24/08/2014")));
                trips.add(newTrip("Oslo", null, DateUtils.toTime("02/12/2014"), DateUtils.toTime("12/12/2014")));
                trips.add(newTrip("Reykjavik", descriptionReykjavik, DateUtils.toTime("01/06/2015"), DateUtils.toTime("14/06/2015")));
                trips.add(newTrip("Barcelona", descriptionBarcelona, DateUtils.toTime("09/09/2015"), DateUtils.toTime("09/10/2015")));
                trips.add(newTrip("New York", null, DateUtils.toTime("25/02/2016"), DateUtils.toTime("25/02/2016")));
                trips.add(newTrip("Batumi", null, DateUtils.toTime("14/06/2016"), DateUtils.toTime("28/06/2016")));
                trips.add(newTrip("London", descriptionLondon, DateUtils.toTime("02/09/2016"), DateUtils.toTime("02/09/2016")));
                trips.add(newTrip("Berlin", descriptionBerlin, DateUtils.toTime("14/12/2016"), DateUtils.toTime("16/12/2016")));

                tripsMockRepository.storeModelsFast(trips);

                if (tripEventsMockRepository.count() == 0) {
                    Trip firstTrip = trips.get(0);
                    if (firstTrip != null) {
                        String commentHotel = "Grand palatial hotel in the heart of Paris, in the 1st arrondissement. The hotel is ranked highly among the most prestigious and luxurious hotels in the world and is a member of The Leading Hotels of the World. The Ritz reopened on 6 June 2016 after a major four-year, multimillion-dollar renovation.";
                        String commentEiffelTower = "Wrought iron lattice tower on the Champ de Mars in Paris, France. It is named after the engineer Gustave Eiffel, whose company designed and built the tower.";
                        String commentLouvre = "The world's largest museum and a historic monument in Paris, France. A central landmark of the city, it is located on the Right Bank of the Seine in the 1st arrondissement (ward). Nearly 35,000 objects from prehistory to the 21st century are exhibited over an area of 60,600 square metres.";

                        List<TripEvent> tripEvents = new ArrayList<>();
                        tripEvents.add(newTripEvent(firstTrip, null, "Ticket", null, TypeEventUtils.Type.TICKET, DateUtils.toTime("12/12/2013"), DateUtils.toTime("12/12/2013")));
                        tripEvents.add(newTripEvent(firstTrip, null, "Return Ticket", null, TypeEventUtils.Type.TICKET, DateUtils.toTime("21/12/2013"), DateUtils.toTime("21/12/2013")));
                        tripEvents.add(newTripEvent(firstTrip, null, "Hotel Ritz Paris", commentHotel, TypeEventUtils.Type.HOTEL, DateUtils.toTime("12/01/2014"), DateUtils.toTime("22/01/2014")));
                        tripEvents.add(newTripEvent(firstTrip, null, "Eiffel Tower", commentEiffelTower, TypeEventUtils.Type.PLACE_OF_INTEREST, DateUtils.toTime("16/01/2014"), DateUtils.toTime("16/01/2014")));
                        tripEvents.add(newTripEvent(firstTrip, null, "Louvre", commentLouvre, TypeEventUtils.Type.PLACE_OF_INTEREST, DateUtils.toTime("19/01/2014"), DateUtils.toTime("19/01/2014")));

                        tripEventsMockRepository.storeModelsFast(tripEvents);

                        if (photoAttachmentMockRepository.count() == 0) {
                            TripEvent tripEvent = tripEvents.get(4);
                            if (tripEvent != null) {
                                String photoBelowGround = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Donjon_chateau_louvre.JPG/640px-Donjon_chateau_louvre.JPG";
                                String photoPsycheRevived = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Amor-Psyche-Canova-JBU02.JPG/480px-Amor-Psyche-Canova-JBU02.JPG";
                                String photoPyramid = "https://upload.wikimedia.org/wikipedia/en/thumb/4/42/Louvre_Pyramid.jpg/640px-Louvre_Pyramid.jpg";

                                List<PhotoAttachment> photoAttachments = new ArrayList<>();
                                photoAttachments.add(newPhotoAttachment(tripEvent, photoBelowGround));
                                photoAttachments.add(newPhotoAttachment(tripEvent, photoPsycheRevived));
                                photoAttachments.add(newPhotoAttachment(tripEvent, photoPyramid));

                                photoAttachmentMockRepository.storeModelsFast(photoAttachments);
                            }
                        }
                    }
                }
            }
        }

        private Trip newTrip(String title, String description, long startDate, long finishDate) {
            Trip trip = new Trip();
            trip.title = title;
            trip.description = description;
            trip.startDate = startDate;
            trip.finishDate = finishDate;
            return trip;
        }

        private TripEvent newTripEvent(Trip trip, User user, String name, String comment, @TypeEventUtils.Type int type, long startDate, long finishDate) {
            TripEvent tripEvent = new TripEvent();
            tripEvent.trip = trip;
            tripEvent.user = user;
            tripEvent.name = name;
            tripEvent.comment = comment;
            tripEvent.type = type;
            tripEvent.startDate = startDate;
            tripEvent.finishDate = finishDate;
            return tripEvent;
        }

        private PhotoAttachment newPhotoAttachment(TripEvent tripEvent, String path) {
            PhotoAttachment photoAttachment = new PhotoAttachment();
            photoAttachment.tripEvent = tripEvent;
            photoAttachment.path = path;
            return photoAttachment;
        }

    }

}