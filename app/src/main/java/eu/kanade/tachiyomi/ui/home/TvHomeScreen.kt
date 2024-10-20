package eu.kanade.tachiyomi.ui.home

import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import eu.kanade.domain.ui.UiPreferences
import eu.kanade.presentation.util.Screen
import eu.kanade.tachiyomi.R
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.i18n.stringResource
import uy.kohesive.injekt.injectLazy
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.Text
import eu.kanade.tachiyomi.ui.browse.BrowseDrawer
import eu.kanade.tachiyomi.ui.library.anime.AnimeLibraryDrawer
import eu.kanade.tachiyomi.ui.more.MoreDrawer
import eu.kanade.tachiyomi.ui.recents.RecentsDrawer

object TvHomeScreen : Screen() {
    private fun readResolve(): Any = TvHomeScreen
    private val uiPreferences: UiPreferences by injectLazy()
    private val defaultTab = uiPreferences.startScreen().get().tab

    @Composable
    override fun Content() {
        val drawers = listOf(
            AnimeLibraryDrawer,
            RecentsDrawer,
            BrowseDrawer,
            MoreDrawer
        )

        var selectedIndex by remember { mutableIntStateOf(0) }

        NavigationDrawer(
            drawerContent = {
                Column(
                    Modifier
                        .background(Color.Gray)
                        .fillMaxHeight()
                        .padding(12.dp)
                        .selectableGroup(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    drawers.forEach { drawer ->
                        val index = drawer.index

                        NavigationDrawerItem(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            content = { Text(stringResource(drawer.title)) },
                            leadingContent = {
                                Icon(
                                    imageVector = AnimatedImageVector.animatedVectorResource(drawer.imageId).imageVector,
                                    contentDescription = null,
                                )
                            },
                        )
                    }
                }
            },
        ) {
            val selectedDrawerItem = drawers.first { it.index == selectedIndex }
            selectedDrawerItem.Content()
        }
    }
}
