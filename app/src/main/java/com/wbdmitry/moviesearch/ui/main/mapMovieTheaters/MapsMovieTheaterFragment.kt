package com.wbdmitry.moviesearch.ui.main.mapMovieTheaters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.wbdmitry.moviesearch.R
import com.wbdmitry.moviesearch.model.entity.cinemaList

private const val ZOOM_GOOGLE_MAP = 10f

class MapsMovieTheaterFragment : Fragment() {

    companion object {
        fun newInstance() = MapsMovieTheaterFragment()
    }

    private val callback = OnMapReadyCallback { googleMap ->

        cinemaList.forEach {
            googleMap.addMarker(
                MarkerOptions()
                    .position(it.position)
                    .title(it.title)
                    .snippet(it.snippet)
            )
        }

        googleMap.moveCamera(
            CameraUpdateFactory.newLatLng(
                cinemaList.first().position
            )
        )

        googleMap.setMinZoomPreference(ZOOM_GOOGLE_MAP)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_movie_theater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}