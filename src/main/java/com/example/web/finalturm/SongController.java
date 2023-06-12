package com.example.web.finalturm;

import com.example.web.HttpUtils;
import com.example.web.dao.Limit;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@AllArgsConstructor
@Slf4j
public class SongController {

    private static final String CURRENT_SONG_LIST = "CURRENT_SONG_LIST";
    private final SongDao songDao;

    @GetMapping("/song/songForm")
    public void mapDefault() {
    }

    @GetMapping("/song/songList")
    public void songList(HttpServletRequest req, Limit limit, Model model) {
        // 현재 목록을 세션에 저장
        req.getSession().setAttribute(CURRENT_SONG_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        req.setAttribute("songList", songDao.listSongs(limit));
    }

    @GetMapping("/song/song")
    public void song(int songId, Model model) {

        model.addAttribute("song", songDao.getSong(songId));
    }

    @PostMapping("/song/addSong")
    public String addSong(Song song) {
        songDao.addSong(song);
        return "redirect:/app/song/songList";
    }

    @GetMapping("/song/songEdit")
    public void songEdit(int songId, Model model) {
        Song song = songDao.getSong(songId);
        model.addAttribute("song", song);
    }

    @PostMapping("/song/updateSong")
    public String updateSong(Song song) {
        songDao.updateSong(song);
        return "redirect:/app/song/song?songId=" + song.getSongId();
    }

    @GetMapping("/song/deleteSong")
    public String deleteArticle(int songId, @SessionAttribute(CURRENT_SONG_LIST) String currentSongList){
        songDao.deleteSong(songId);
        return "redirect:" + currentSongList;
    }
}