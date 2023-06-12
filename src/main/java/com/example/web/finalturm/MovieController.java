package com.example.web.finalturm;

import com.example.web.HttpUtils;
import com.example.web.dao.*;
import com.example.web.finalturm.Movie;
import com.example.web.finalturm.MovieDao;
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
public class MovieController {

    private static final String CURRENT_MOVIE_LIST = "CURRENT_MOVIE_LIST";
    private final MovieDao movieDao;

    @GetMapping("/movie/movieForm")
    public void mapDefault() {
    }

    @GetMapping("/movie/movieList")
    public void movieList(HttpServletRequest req, Limit limit, Model model) {
        // 현재 목록을 세션에 저장
        req.getSession().setAttribute(CURRENT_MOVIE_LIST,
                HttpUtils.getRequestURLWithQueryString(req));

        req.setAttribute("movieList", movieDao.listMovies(limit));
    }

    @GetMapping("/movie/movie")
    public void movie(int movieId, Model model) {

        model.addAttribute("movie", movieDao.getMovie(movieId));
    }

    @PostMapping("/movie/addMovie")
    public String addMovie(Movie movie) {
        movieDao.addMovie(movie);
        return "redirect:/app/movie/movieList";
    }

    @GetMapping("/movie/movieEdit")
    public void articleEdit(int movieId, Model model) {
        Movie movie = movieDao.getMovie(movieId);
        model.addAttribute("movie", movie);
    }

    @PostMapping("/movie/updateMovie")
    public String updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
        return "redirect:/app/movie/movie?movieId=" + movie.getMovieId();
    }

    @GetMapping("/movie/deleteMovie")
    public String deleteArticle(int movieId, @SessionAttribute(CURRENT_MOVIE_LIST) String currentMovieList){
        movieDao.deleteMovie(movieId);
        return "redirect:" + currentMovieList;
    }
}