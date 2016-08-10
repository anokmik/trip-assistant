package com.anokmik.tripassistant

import android.app.Application
import android.databinding.DataBindingUtil
import com.anokmik.persistence.model.Trip
import com.anokmik.persistence.repository.mock.MockTripRepository
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

        val mockTripRepository = MockTripRepository()

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
            }
        }

        private fun newTrip(titleValue: String?, descriptionValue: String?, startDateValue: Long, finishDateValue: Long): Trip {
            return Trip().apply {
                title = titleValue
                description = descriptionValue
                startDate = startDateValue
                finishDate = finishDateValue
            }
        }

    }

}