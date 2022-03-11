package io.leofalves.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.leofalves.dsmovie.dto.MovieDTO;
import io.leofalves.dsmovie.dto.ScoreDTO;
import io.leofalves.dsmovie.entities.Movie;
import io.leofalves.dsmovie.entities.Score;
import io.leofalves.dsmovie.entities.User;
import io.leofalves.dsmovie.repositories.MovieRepository;
import io.leofalves.dsmovie.repositories.ScoreRepository;
import io.leofalves.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;

	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		User user = userRepository.findByEmail(dto.getEmail());
		if (user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);
		}
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
		
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for(Score s : movie.getScores()) {
			sum =+ s.getValue();
		}		
		// Calcula a média das avaliações do filme
		double avg = sum / movie.getScores().size();
		
		// Atualiza o objeto com a média e a quantidade
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
	
}
