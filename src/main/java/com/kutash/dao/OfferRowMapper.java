package com.kutash.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferRowMapper implements RowMapper<Offer> {


	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setAuthority(rs.getString("authority"));
        user.setEmail(rs.getString("email"));
        user.setEnabled(true);
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));

        Offer offer = new Offer();
        offer.setId(rs.getInt("id"));
        offer.setName(rs.getString("name"));
        offer.setText(rs.getString("text"));
        offer.setUser(user);

        return offer;
	}

}
