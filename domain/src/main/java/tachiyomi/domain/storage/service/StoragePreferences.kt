package tachiyomi.domain.storage.service

import tachiyomi.core.preference.Preference
import tachiyomi.core.preference.PreferenceStore
import tachiyomi.core.storage.FolderProvider

class StoragePreferences(
    private val folderProvider: FolderProvider,
    private val preferenceStore: PreferenceStore,
) {

    fun baseStorageDirectory() = preferenceStore.getString(Preference.appStateKey("storage_dir"), folderProvider.path())

    // AM (FILE_SIZE) -->
    fun showEpisodeFileSize() = preferenceStore.getBoolean("pref_show_downloaded_episode_size", true)
    // <-- AM (FILE_SIZE)
}
