package com.codingdojo.Lookify.services;

import java.util.List;


import org.springframework.stereotype.Service;


import com.codingdojo.Lookify.models.Song;
import com.codingdojo.Lookify.repositories.SongRepository;



@Service
public class SongService {
	
	private SongRepository songRepository;
	
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	public Song addSong(Song song) {
		return  (Song) songRepository.save(song);
	}
	public List<Song> getAllSongs() {
		return (List<Song>) songRepository.findAll();
	}
	public Song getSong(Long id) {
		Song songInDB = songRepository.findById(id).get();
		return songInDB;
	}
	public void deleteSong(Long id) {
		songRepository.deleteById(id);
	}
	public List<Song> getSearchSongs(String artist){
		return songRepository.findByArtist(artist);
	}
	public List<Song> getTopSongs(){
		return songRepository.findTop10ByOrderByRatingDesc();
	} 
    
}
