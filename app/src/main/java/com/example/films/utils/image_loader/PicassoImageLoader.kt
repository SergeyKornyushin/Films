package com.example.films.utils.image_loader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation.CornerType
import java.io.File

/**
 * Загрузка изображений с помощью @Picasso
 */
class PicassoImageLoader : ImageLoader {

    private val picasso = Picasso.get()
    private lateinit var requestCreator: RequestCreator

    override fun centerCrop(): ImageLoader {
        requestCreator
            .fit()
            .centerCrop()
        return this
    }

    override fun roundedCorners(roundingRadius: Int): ImageLoader {
        requestCreator.transform(
            RoundedCornersTransformation(roundingRadius, 0, CornerType.ALL)
        )
        return this
    }

    override fun circleCrop(): ImageLoader {
        requestCreator.transform(CropCircleTransformation())
        return this
    }

    override fun centerInside(): ImageLoader {
        requestCreator
            .fit()
            .centerInside()
        return this
    }

    override fun placeholder(placeholderDrawable: Drawable): ImageLoader {
        requestCreator.placeholder(placeholderDrawable)
        return this
    }

    override fun error(errorResId: Int): ImageLoader {
        requestCreator.error(errorResId)
        return this
    }

    override fun error(errorDrawable: Drawable): ImageLoader {
        requestCreator.error(errorDrawable)
        return this
    }

    override fun placeholder(placeholderResId: Int): ImageLoader {
        requestCreator.placeholder(placeholderResId)
        return this
    }

    override fun resize(targetWidth: Int, targetHeight: Int): ImageLoader {
        requestCreator.resize(targetWidth, targetHeight)
        return this
    }

    override fun into(imageView: ImageView, callback: ImageLoaderListener?) {
        requestCreator.into(
            imageView,
            object : Callback {
                override fun onError(e: Exception?) {
                    callback?.onError(e.toString())
                }

                override fun onSuccess() {
                    callback?.onSuccess()
                }
            }
        )
    }

    override fun load(url: String?): ImageLoader {
        requestCreator = picasso.load(url)
        return this
    }

    override fun load(file: File): ImageLoader {
        requestCreator = picasso.load(file)
        return this
    }

    override fun load(@DrawableRes resourceId: Int): ImageLoader {
        requestCreator = picasso.load(resourceId)
        return this
    }
}

/**
 * Создает экземпляр загрузчика @Picasso
 */
class PicassoLoaderCreator : LoaderCreator {
    override fun getInstance(context: Context): ImageLoader {
        return PicassoImageLoader()
    }
}