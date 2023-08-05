package manuall.dao;

import manuall.dto.Actor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import manuall.config.JdbcConfig;

import java.util.List;
import java.util.Optional;

public class ActorJdbcDAO implements DAO<Actor> {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcConfig.dataSource());

    @Override
    public Optional<Actor> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Actor> getAll() {
        return jdbcTemplate.query("SELECT * from sakila.actor", new BeanPropertyRowMapper<>(Actor.class));
    }

    @Override
    public void save(Actor actor) {

    }

    @Override
    public void update(Actor actor, String[] params) {

    }

    @Override
    public void delete(Actor actor) {

    }
}
