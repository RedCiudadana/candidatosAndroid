package org.redciudadana.candidatos.utils.glide

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

private const val ID = "org.redciudadana.utils.RoundCornerTransformation"
private val ID_BYTES = ID.toByteArray()

class RoundCornerTransformation(val resources: Resources): BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }


    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val factory = RoundedBitmapDrawableFactory.create(resources, toTransform)
        factory.cornerRadius = 10f

        val bitmap = Bitmap.createBitmap(factory.intrinsicWidth, factory.intrinsicHeight, toTransform.config)
        val canvas = Canvas(bitmap)
        factory.setBounds(0, 0, factory.intrinsicWidth, factory.intrinsicHeight)
        factory.draw(canvas)
        return bitmap
    }
}