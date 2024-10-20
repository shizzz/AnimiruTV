package eu.kanade.tachiyomi.ui.browse

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.home.DrawerItem
import tachiyomi.i18n.MR

object BrowseDrawer : DrawerItem() {
    override val index: Int = 2
    override val imageId: Int = R.drawable.anim_browse_enter
    override val title: StringResource = MR.strings.browse

    @Composable
    override fun Content() {
        BrowseTab.Content()
    }
}
