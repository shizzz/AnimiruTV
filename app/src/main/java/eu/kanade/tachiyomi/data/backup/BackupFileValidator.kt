package eu.kanade.tachiyomi.data.backup

import android.content.Context
import android.net.Uri
import eu.kanade.tachiyomi.data.track.TrackerManager
import tachiyomi.domain.source.anime.service.AnimeSourceManager
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class BackupFileValidator(
    private val context: Context,
    private val animeSourceManager: AnimeSourceManager = Injekt.get(),
    private val trackerManager: TrackerManager = Injekt.get(),
) {

    /**
     * Checks for critical backup file data.
     *
     * @return List of missing sources or missing trackers.
     */
    fun validate(uri: Uri): Results {
        val backup = try {
            BackupDecoder(context).decode(uri)
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }

        val animesources = backup.backupAnimeSources.associate { it.sourceId to it.name }
        val missingSources = animesources
            .filter { animeSourceManager.get(it.key) == null }
            .values.map {
                val id = it.toLongOrNull()
                if (id == null) {
                    it
                } else {
                    animeSourceManager.getOrStub(id).toString()
                }
            }
            .distinct()
            .sorted()

        val animeTrackers = backup.backupAnime
            .flatMap { it.tracking }
            .map { it.syncId }
        val trackers = (animeTrackers).distinct()
        val missingTrackers = trackers
            .mapNotNull { trackerManager.get(it.toLong()) }
            .filter { !it.isLoggedIn }
            .map { it.name }
            .sorted()

        return Results(missingSources, missingTrackers)
    }

    data class Results(
        val missingSources: List<String>,
        val missingTrackers: List<String>,
    )
}
