package eu.kanade.tachiyomi.ui.more

import androidx.compose.runtime.Composable
import androidx.tv.material3.Text
import dev.icerock.moko.resources.StringResource
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.home.DrawerItem
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.i18n.stringResource

object MoreDrawer : DrawerItem() {
    override val index: Int = 3
    override val imageId: Int = R.drawable.anim_more_enter
    override val title: StringResource = MR.strings.label_more

    @Composable
    override fun Content() {
        MoreTab.Content()
    }
}
