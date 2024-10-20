package eu.kanade.tachiyomi.data.backup.create

import dev.icerock.moko.resources.StringResource
import kotlinx.collections.immutable.persistentListOf
import tachiyomi.i18n.MR

data class BackupOptions(
    val libraryEntries: Boolean = true,
    val categories: Boolean = true,
    val episodes: Boolean = true,
    val tracking: Boolean = true,
    val history: Boolean = true,
    val appSettings: Boolean = true,
    val sourceSettings: Boolean = true,
    val privateSettings: Boolean = false,
    val extensions: Boolean = false,
    // AM (CUSTOM_INFORMATION) -->
    val customInfo: Boolean = false,
    // <-- AM (CUSTOM_INFORMATION)
) {

    fun asBooleanArray() = booleanArrayOf(
        libraryEntries,
        categories,
        episodes,
        tracking,
        history,
        appSettings,
        sourceSettings,
        privateSettings,
        extensions,
        // AM (CUSTOM_INFORMATION) -->
        customInfo,
        // <-- AM (CUSTOM_INFORMATION)
    )

    fun anyEnabled() = libraryEntries || appSettings || sourceSettings

    companion object {
        val libraryOptions = persistentListOf(
            Entry(
                label = MR.strings.entries,
                getter = BackupOptions::libraryEntries,
                setter = { options, enabled -> options.copy(libraryEntries = enabled) },
            ),
            Entry(
                label = MR.strings.categories,
                getter = BackupOptions::categories,
                setter = { options, enabled -> options.copy(categories = enabled) },
                enabled = { it.libraryEntries },
            ),
            Entry(
                label = MR.strings.episodes,
                getter = BackupOptions::episodes,
                setter = { options, enabled -> options.copy(episodes = enabled) },
                enabled = { it.libraryEntries },
            ),
            Entry(
                label = MR.strings.track,
                getter = BackupOptions::tracking,
                setter = { options, enabled -> options.copy(tracking = enabled) },
                enabled = { it.libraryEntries },
            ),
            Entry(
                label = MR.strings.history,
                getter = BackupOptions::history,
                setter = { options, enabled -> options.copy(history = enabled) },
                enabled = { it.libraryEntries },
            ),
            // AM (CUSTOM_INFORMATION) -->
            Entry(
                label = MR.strings.custom_entry_info,
                getter = BackupOptions::customInfo,
                setter = { options, enabled -> options.copy(customInfo = enabled) },
                enabled = { it.libraryEntries },
            ),
            // <-- AM (CUSTOM_INFORMATION)
        )

        val settingsOptions = persistentListOf(
            Entry(
                label = MR.strings.app_settings,
                getter = BackupOptions::appSettings,
                setter = { options, enabled -> options.copy(appSettings = enabled) },
            ),
            Entry(
                label = MR.strings.source_settings,
                getter = BackupOptions::sourceSettings,
                setter = { options, enabled -> options.copy(sourceSettings = enabled) },
            ),
            Entry(
                label = MR.strings.private_settings,
                getter = BackupOptions::privateSettings,
                setter = { options, enabled -> options.copy(privateSettings = enabled) },
                enabled = { it.appSettings || it.sourceSettings },
            ),
        )

        val extensionOptions = persistentListOf(
            Entry(
                label = MR.strings.label_extensions,
                getter = BackupOptions::extensions,
                setter = { options, enabled -> options.copy(extensions = enabled) },
            ),
        )

        fun fromBooleanArray(array: BooleanArray) = BackupOptions(
            libraryEntries = array[0],
            categories = array[1],
            episodes = array[2],
            tracking = array[3],
            history = array[4],
            appSettings = array[5],
            sourceSettings = array[6],
            privateSettings = array[7],
            extensions = array[8],
            // AM (CUSTOM_INFORMATION) -->
            customInfo = array[9],
            // <-- AM (CUSTOM_INFORMATION)

        )
    }

    data class Entry(
        val label: StringResource,
        val getter: (BackupOptions) -> Boolean,
        val setter: (BackupOptions, Boolean) -> BackupOptions,
        val enabled: (BackupOptions) -> Boolean = { true },
    )
}
