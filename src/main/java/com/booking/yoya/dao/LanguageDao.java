package com.booking.yoya.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.yoya.model.Language;

@Repository
public interface LanguageDao extends JpaRepository<Language, Long> {

	public static final String FINDBYLANGUAGENAME = "SELECT * from language where language_name=:languageName";

	@Query(value = FINDBYLANGUAGENAME, nativeQuery = true)
	Optional<Language> findByLanguageName(String languageName);

}
