package eu.kanade.tachiyomi.ui.home

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource

abstract class DrawerItem {
    abstract val index: Int
    abstract val imageId: Int
    abstract val title: StringResource

    @Composable
    abstract fun Content()
}
