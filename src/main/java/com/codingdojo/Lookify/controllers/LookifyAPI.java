package com.codingdojo.Lookify.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.Lookify.models.Song;
import com.codingdojo.Lookify.services.SongService;

@RestController
public class LookifyAPI {
	private final SongService songService;
	public LookifyAPI(SongService songService) {
		this.songService = songService;
	}
	@RequestMapping("/api/dashboard")
	public List<Song> dash(){
		return songService.getAllSongs();
	}
	@RequestMapping("/api/search/{artists}")
	public List<Song> showSearch(@PathVariable("artist") String artist){
		return songService.getSearchSongs(artist);
	}
	@RequestMapping(value="/api/songs/new", method=RequestMethod.POST)
	public Song create(@RequestParam(value="title") String title, @RequestParam(value="artist") String artist, @RequestParam(value="rating") Integer rating) {
		Song song = new Song(title, artist, rating);
		
	    return songService.addSong(song);
	}
	@RequestMapping("/api/songs/{id}")
    public Song show(@PathVariable("id") Long id) {
        Song song = songService.getSong(id);
        return song;
    }
	
	@RequestMapping(value="/api/songs/delete/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        songService.deleteSong(id);
    }
	@RequestMapping(value="/api/songs/delete/{artist}/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("artist") String artist, @PathVariable("id") Long id) {
        songService.deleteSong(id);
    }
}
