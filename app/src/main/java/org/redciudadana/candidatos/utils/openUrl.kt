package org.redciudadana.candidatos.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openUrl(context: Context?, url: String?) {
    if (context != null && url != null) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}