/*SELECT*
FROM ARTIST
;

SELECT*
FROM ALBUM
;

SELECT*
FROM SONG
;

SELECT*
FROM GENRE
;

SELECT*
FROM PLAYLIST
;
SELECT*
FROM Associated
;
SELECT*
FROM PARTOF
;
-- 10 Queries
-- 1
SELECT SONG.SongID, SONG.Title AS SongTitle, ALBUM.AlbumID, ALBUM.Title AS AlbumTitle, ALBUM.ReleaseDate,ALBUM.NumTracks, ALBUM.Label
FROM SONG JOIN ALBUM ON SONG.AlbumID=ALBUM.AlbumID
;

-- 2 list all album information in order of number of tracks on each album 
SELECT *
FROM ALBUM
ORDER BY NumTracks
;

-- 3 list the songs on each playlist
SELECT PARTOF.SongID, SONG.Title, PARTOF.PlaylistID, PLAYLIST.Playlistname
FROM SONG JOIN PARTOF ON SONG.SongID=PARTOF.SongID JOIN PLAYLIST ON PARTOF.PlaylistID=PLAYLIST.PlaylistID  
;

-- 4 what playist(s) is the song outro on 
SELECT SONG.SongID, SONG.Title, PLAYLIST.Playlistname, PLAYLIST.PlaylistID
FROM SONG JOIN PARTOF ON SONG.SongID=PARTOF.SongID  JOIN PLAYLIST ON PARTOF.PlaylistID=PLAYLIST.PlaylistID  
WHERE SONG.Title ='Outro'
;

-- 5 list song artist and playlist
SELECT SONG.Title AS SongTitle, ARTIST.Name AS ArtistName, PLAYLIST.Playlistname
FROM ARTIST JOIN SONG ON ARTIST.AlbumID=SONG.AlbumID JOIN PARTOF ON SONG.SongID=PARTOF.SongID JOIN PLAYLIST ON PARTOF.PlaylistID=PLAYLIST.PlaylistID
;

-- 6 number of songs on the playlist
SELECT PLAYLIST.Playlistname, COUNT(PARTOF.SongID) AS NumSongs
FROM PLAYLIST JOIN PARTOF ON PLAYLIST.PlaylistID=PARTOF.PlaylistID
GROUP BY Playlistname
;

-- 7
SELECT COUNT(ARTIST.Name) as NumArtists
FROM ARTIST
;

-- 8 
SELECT SONG.Title, SONG.Duration
FROM SONG
ORDER BY Duration
;

-- 9 count the number of songs i have for each artist 
SELECT ARTIST.Name, COUNT(SONG.Title) AS NumSongs
FROM ARTIST JOIN ALBUM on ARTIST.AlbumID=ALBUM.AlbumID JOIN SONG ON ALBUM.AlbumID=SONG.AlbumID
GROUP BY ARTIST.Name
;

-- 10
SELECT SUM(SONG.Duration) AS TotalMinutes
FROM SONG
;
*/
/* 
I think I want to edit the songs that are inserted into my database, so we will have to redo some of the SS on the powerpoint pres 
*/

/*
DROP PROCEDURE IF EXISTS Extra1;
DELIMITER //
CREATE PROCEDURE Extra1()
BEGIN
    SELECT SONG.Title AS SongTitle, ARTIST.Name AS ArtistName, PLAYLIST.Playlistname
    FROM ARTIST JOIN SONG ON ARTIST.AlbumID=SONG.AlbumID JOIN PARTOF ON SONG.SongID=PARTOF.SongID JOIN PLAYLIST ON PARTOF.PlaylistID=PLAYLIST.PlaylistID
    ;
END //
DELIMITER ;
CALL Extra1;
*/
DROP PROCEDURE IF EXISTS Find;
DELIMITER //
CREATE PROCEDURE Find(IN ArtistName CHAR(30))
BEGIN
SELECT COUNT(SONG.Title) NumSongs
FROM ARTIST JOIN SONG ON ARTIST.AlbumID=SONG.AlbumID
WHERE ARTIST.Name= ArtistName
;
END //
DELIMITER ;
CALL Find('Taylor Swift');
CALL Find('MGMT');
