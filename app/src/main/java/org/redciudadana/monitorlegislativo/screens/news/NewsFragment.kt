package org.redciudadana.monitorlegislativo.screens.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.twitter.sdk.android.tweetui.SearchTimeline
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter
import kotlinx.android.synthetic.main.fragment_news.*
import org.redciudadana.monitorlegislativo.R

class NewsFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timeline = SearchTimeline.Builder()
            .query("Congreso Guatemala")
            .query("Diputado Guatemala")
            .query("Ley Guatemala")
            .build()
        val adapter = TweetTimelineListAdapter.Builder(context)
            .setTimeline(timeline)
            .build()
        news_list.adapter = adapter
    }
}