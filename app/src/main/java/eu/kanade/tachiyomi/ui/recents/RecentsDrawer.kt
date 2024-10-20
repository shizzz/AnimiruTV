package eu.kanade.tachiyomi.ui.recents

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.home.DrawerItem
import tachiyomi.i18n.MR

object RecentsDrawer : DrawerItem() {
    override val index: Int = 1
    override val imageId: Int = R.drawable.anim_recents_enter
    override val title: StringResource = MR.strings.label_recent_recents

    @Composable
    override fun Content() {
        RecentsTab(false).Content()
    }
}
