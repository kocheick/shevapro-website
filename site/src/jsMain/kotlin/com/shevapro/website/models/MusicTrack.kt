package com.shevapro.website.models

import kotlinx.serialization.Serializable

/**
 * Represents a music track.
 * 
 * @property id Unique identifier for the music track
 * @property title Title of the track
 * @property artist Artist or composer of the track
 * @property album Album name (if applicable)
 * @property duration Duration of the track in seconds
 * @property releaseDate Release date of the track (ISO format: YYYY-MM-DD)
 * @property coverArtUrl URL to the cover art image
 * @property audioUrl URL to the audio file
 * @property streamingUrls Map of streaming platform names to their URLs (e.g., "spotify" to Spotify URL)
 * @property genres List of genres associated with the track
 * @property description Description or story behind the track
 * @property featured Whether this track should be featured on the homepage
 * @property lyrics Lyrics of the track (if available)
 * @property credits Information about people involved in creating the track
 */
@Serializable
data class MusicTrack(
    val id: String,
    val title: String,
    val artist: String,
    val album: String? = null,
    val duration: Int, // in seconds
    val releaseDate: String,
    val coverArtUrl: String,
    val audioUrl: String? = null,
    val streamingUrls: Map<String, String> = emptyMap(),
    val genres: List<String> = emptyList(),
    val description: String? = null,
    val featured: Boolean = false,
    val lyrics: String? = null,
    val credits: String? = null
)