package org.redciudadana.candidatos.screens.election_process

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davemorrissey.labs.subscaleview.ImageSource
import kotlinx.android.synthetic.main.fragment_election_process.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.screens.main.MainActivity

class ElectionProcessFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_election_process, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        election_image.setImage(
            ImageSource.resource(R.drawable.infography).dimensions(612, 792),
            ImageSource.resource(R.drawable.infography)
        )
    }

    override fun onResume() {
        super.onResume()
        val activity = activity as MainActivity
        activity.setTitle(R.string.title_election_process)
    }
}