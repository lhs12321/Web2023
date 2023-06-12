package com.example.web.finalturm;

import com.example.web.dao.Limit;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MovieDao {

    private static final String LIST_MOVIES = """
           select movieId, title, director from movie order by movieId desc limit ?, ?
            """;
    private static final String GET_MOVIE = """
            select * from movie where movieId=?
            """;
    private static final String ADD_MOVIE = """
            insert movie(title, director) values(:title,:director)
            """;
    private static final String UPDATE_MOVIE = """
            update movie set title=:title, director=:director where movieId=:movieId
            """;
    private static final String DELETE_MOVIE = """
            delete from movie where movieId=?
            """;

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Movie> movieRowMapper = new BeanPropertyRowMapper<>(Movie.class);

    public List<Movie> listMovies(Limit limit) {
        return jdbcTemplate.query(LIST_MOVIES, movieRowMapper, limit.getOffset(), limit.getCount());
    }

    public Movie getMovie(int movieId) {

        return jdbcTemplate.queryForObject(GET_MOVIE, movieRowMapper, movieId);
    }

    public void addMovie(Movie movie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new BeanPropertySqlParameterSource(movie);
        namedParameterJdbcTemplate.update(ADD_MOVIE, params, keyHolder);
        movie.setMovieId(keyHolder.getKey().intValue());

    }
    public int updateMovie(Movie movie) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(movie);
        return namedParameterJdbcTemplate.update(UPDATE_MOVIE, params);
    }

    public int deleteMovie(int movieId) {
        return jdbcTemplate.update(DELETE_MOVIE, movieId);
    }

}
