package com.anokmik.tripassistant

import android.app.Application
import android.databinding.DataBindingUtil
import com.anokmik.persistence.model.PhotoAttachment
import com.anokmik.persistence.model.Trip
import com.anokmik.persistence.model.TripEvent
import com.anokmik.persistence.model.User
import com.anokmik.persistence.repository.mock.MockRepository
import com.anokmik.tripassistant.databinding.ComponentProvider
import com.anokmik.tripassistant.util.DateUtils
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import java.util.*

class TripAssistantApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(FlowConfig.Builder(this).openDatabasesOnInit(true).build())
        DataBindingUtil.setDefaultComponent(ComponentProvider())
        if (BuildConfig.DEBUG) {
            InitMockData().start()
        }
    }

    private class InitMockData() : Thread() {

        val mockTripRepository = MockRepository<Trip>(Trip::class.java)
        val mockTripEventRepository = MockRepository<TripEvent>(TripEvent::class.java)
        val mockPhotoAttachmentRepository = MockRepository<PhotoAttachment>(PhotoAttachment::class.java)

        override fun run() {
            super.run()
            if (mockTripRepository.count() == 0L) {
                val descriptionParis = "Paris is the home of the most visited art museum in the world, the Louvre, as well as the Musée d'Orsay, noted for its collection of French Impressionist art, and the Musée National d'Art Moderne, a museum of modern and contemporary art."
                val descriptionDublin = "Founded as a Viking settlement, the Kingdom of Dublin became Ireland's principal city following the Norman invasion."
                val descriptionReykjavik = "Reykjavík is believed to be the location of the first permanent settlement in Iceland, which, according to Ingólfur Arnarson, was established in AD 874. Until the 19th century, there was no urban development in the city location."
                val descriptionBarcelona = "Barcelona is one of the world's leading tourist, economic, trade fair and cultural centers, and its influence in commerce, education, entertainment, media, fashion, science, and the arts all contribute to its status as one of the world's major global cities."
                val descriptionLondon = "London contains four World Heritage Sites: the Tower of London; Kew Gardens; the site comprising the Palace of Westminster, Westminster Abbey, and St Margaret's Church; and the historic settlement of Greenwich (in which the Royal Observatory, Greenwich marks the Prime Meridian, 0° longitude, and GMT)."
                val descriptionBerlin = "Modern Berlin is home to world renowned universities, orchestras, museums, entertainment venues and is host to many sporting events. Its urban setting has made it a sought-after location for international film productions."

                val trips = ArrayList<Trip>()
                trips.add(newTrip("Paris", descriptionParis, DateUtils.toTime("12/01/2014"), DateUtils.toTime("23/01/2014")))
                trips.add(newTrip("Prague", null, DateUtils.toTime("30/04/2014"), DateUtils.toTime("09/05/2014")))
                trips.add(newTrip("Dublin", descriptionDublin, DateUtils.toTime("22/08/2014"), DateUtils.toTime("24/08/2014")))
                trips.add(newTrip("Oslo", null, DateUtils.toTime("02/12/2014"), DateUtils.toTime("12/12/2014")))
                trips.add(newTrip("Reykjavik", descriptionReykjavik, DateUtils.toTime("01/06/2015"), DateUtils.toTime("14/06/2015")))
                trips.add(newTrip("Barcelona", descriptionBarcelona, DateUtils.toTime("09/09/2015"), DateUtils.toTime("09/10/2015")))
                trips.add(newTrip("New York", null, DateUtils.toTime("25/02/2016"), DateUtils.toTime("25/02/2016")))
                trips.add(newTrip("Batumi", null, DateUtils.toTime("14/06/2016"), DateUtils.toTime("28/06/2016")))
                trips.add(newTrip("London", descriptionLondon, DateUtils.toTime("02/09/2016"), DateUtils.toTime("02/09/2016")))
                trips.add(newTrip("Berlin", descriptionBerlin, DateUtils.toTime("14/12/2016"), DateUtils.toTime("16/12/2016")))

                mockTripRepository.storeModelsFast(trips)

                if (mockTripEventRepository.count() == 0L) {
                    val firstTrip = trips[0];
                    val commentHotel = "Grand palatial hotel in the heart of Paris, in the 1st arrondissement. The hotel is ranked highly among the most prestigious and luxurious hotels in the world and is a member of The Leading Hotels of the World. The Ritz reopened on 6 June 2016 after a major four-year, multimillion-dollar renovation.";
                    val commentEiffelTower = "Wrought iron lattice tower on the Champ de Mars in Paris, France. It is named after the engineer Gustave Eiffel, whose company designed and built the tower.";
                    val commentLouvre = "The world's largest museum and a historic monument in Paris, France. A central landmark of the city, it is located on the Right Bank of the Seine in the 1st arrondissement (ward). Nearly 35,000 objects from prehistory to the 21st century are exhibited over an area of 60,600 square metres.";

                    val tripEvents = ArrayList<TripEvent>();
                    tripEvents.add(newTripEvent(firstTrip, null, "Ticket", null, DateUtils.toTime("12/12/2013"), DateUtils.toTime("12/12/2013")));
                    tripEvents.add(newTripEvent(firstTrip, null, "Return Ticket", null, DateUtils.toTime("21/12/2013"), DateUtils.toTime("21/12/2013")));
                    tripEvents.add(newTripEvent(firstTrip, null, "Hotel Ritz Paris", commentHotel, DateUtils.toTime("12/01/2014"), DateUtils.toTime("22/01/2014")));
                    tripEvents.add(newTripEvent(firstTrip, null, "Eiffel Tower", commentEiffelTower, DateUtils.toTime("16/01/2014"), DateUtils.toTime("16/01/2014")));
                    tripEvents.add(newTripEvent(firstTrip, null, "Louvre", commentLouvre, DateUtils.toTime("19/01/2014"), DateUtils.toTime("19/01/2014")));

                    mockTripEventRepository.storeModelsFast(tripEvents);

                    if (mockPhotoAttachmentRepository.count() == 0L) {
                        val tripEvent = tripEvents[4]
                        val photoBelowGround = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Donjon_chateau_louvre.JPG/640px-Donjon_chateau_louvre.JPG"
                        val photoPsycheRevived = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Amor-Psyche-Canova-JBU02.JPG/480px-Amor-Psyche-Canova-JBU02.JPG"
                        val photoPyramid = "https://upload.wikimedia.org/wikipedia/en/thumb/4/42/Louvre_Pyramid.jpg/640px-Louvre_Pyramid.jpg"

                        val photoAttachments = ArrayList<PhotoAttachment>()
                        photoAttachments.add(newPhotoAttachment(tripEvent, photoBelowGround))
                        photoAttachments.add(newPhotoAttachment(tripEvent, photoPsycheRevived))
                        photoAttachments.add(newPhotoAttachment(tripEvent, photoPyramid))

                        mockPhotoAttachmentRepository.storeModelsFast(photoAttachments)
                    }
                }
            }
        }

        private fun newTrip(titleValue: String?, descriptionValue: String?, startDateValue: Long, finishDateValue: Long) = Trip().apply {
            title = titleValue
            description = descriptionValue
            startDate = startDateValue
            finishDate = finishDateValue
        }

        private fun newTripEvent(tripValue: Trip?, userValue: User?, nameValue: String?, commentValue: String?, startDateValue: Long, finishDateValue: Long) = TripEvent().apply {
            trip = tripValue;
            user = userValue;
            name = nameValue;
            comment = commentValue;
            startDate = startDateValue;
            finishDate = finishDateValue;
        }

        private fun newPhotoAttachment(tripEvenValue: TripEvent, pathValue: String) = PhotoAttachment().apply {
            tripEvent = tripEvenValue;
            path = pathValue;
        }

    }

}