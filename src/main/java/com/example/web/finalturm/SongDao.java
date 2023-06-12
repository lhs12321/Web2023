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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SongDao{
    private static final String LIST_SONGS = """
           select * from song order by songId desc limit ?, ?
            """;
    private static final String GET_SONG = """
            select * from song where songId=?
            """;
    private static final String ADD_SONG = """
            insert song(title, name) values(:title,:name)
            """;
    private static final String UPDATE_SONG = """
            update song set title=:title, name=:name where songId=:songId
            """;
    private static final String DELETE_SONG = """
            delete from song where songId=?
            """;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Song> songRowMapper = new BeanPropertyRowMapper<>(Song.class);
    public List<Song> listSongs(Limit limit) {
        return jdbcTemplate.query(LIST_SONGS, songRowMapper, limit.getOffset(), limit.getCount());
    }

    public void addSong(Song song) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new BeanPropertySqlParameterSource(song);
        namedParameterJdbcTemplate.update(ADD_SONG, params, keyHolder);
        song.setSongId(keyHolder.getKey().intValue());
    }

    public Song getSong(int songId) {
        return jdbcTemplate.queryForObject(GET_SONG, songRowMapper, songId);
    }

    public int updateSong(Song song) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(song);
        return namedParameterJdbcTemplate.update(UPDATE_SONG, params);
    }

    public int deleteSong(int songId) {
        return jdbcTemplate.update(DELETE_SONG, songId);
    }
}