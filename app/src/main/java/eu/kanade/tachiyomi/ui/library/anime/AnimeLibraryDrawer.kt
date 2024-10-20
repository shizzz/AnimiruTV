package eu.kanade.tachiyomi.ui.library.anime

import androidx.compose.runtime.Composable
import androidx.tv.material3.Text
import dev.icerock.moko.resources.StringResource
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.home.DrawerItem
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.i18n.stringResource

object AnimeLibraryDrawer : DrawerItem() {
    override val index: Int = 0
    override val imageId: Int = R.drawable.anim_animelibrary_leave
    override val title: StringResource = MR.strings.label_library

    @Composable
    override fun Content() {
        AnimeLibraryTab.Content()
    }
}
