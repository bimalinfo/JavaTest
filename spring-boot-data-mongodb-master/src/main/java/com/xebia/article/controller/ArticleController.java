package com.xebia.article.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.article.exception.RecordNotFoundException;
import com.xebia.article.exception.TitleInvalidException;
import com.xebia.article.model.Article;
import com.xebia.article.repository.ArticleRepository;

@RestController
@RequestMapping("/api")
public class ArticleController {

	@Autowired
	ArticleRepository articleRepository;

	@GetMapping("/articles")
	public ResponseEntity<List<Article>> getAllArticles(@RequestParam(required = false) String title) {
		System.out.println("Changes Done");
		try {
			List<Article> articles = new ArrayList<Article>();

			if (title == null)
				articleRepository.findAll().forEach(articles::add);
			else
				articleRepository.findByTitleContaining(title).forEach(articles::add);

			if (articles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(articles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/articles/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") String id) {
		Optional<Article> articleData = articleRepository.findById(id);

		if (!articleData.isPresent()) {
			throw new RecordNotFoundException("Invalid article id : " + id);
		} else {
			return new ResponseEntity<>(articleData.get(), HttpStatus.OK);
		}
	}

	@PostMapping("/articles")
	public ResponseEntity<Article> createArticle(@Valid @RequestBody Article article) {
		System.out.println("createArticle is called");
		
		if ("".equalsIgnoreCase(article.getTitle())) {
			throw new TitleInvalidException("Invalid Title : " + article.getTitle());
		}

		Article _article = articleRepository.save(
				new Article(article.getId(), article.getTitle(), article.getDescription(), new Date(), new Date()));
		return new ResponseEntity<>(_article, HttpStatus.CREATED);
		
	}

	@PutMapping("/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable("id") String id, @RequestBody Article article) {
		Optional<Article> articleData = articleRepository.findById(id);

		if (articleData.isPresent()) {
			Article _article = articleData.get();
			_article.setTitle(article.getTitle());
			_article.setDescription(article.getDescription());
			_article.setUpdatedAt(new Date());
			return new ResponseEntity<>(articleRepository.save(_article), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/articles/{id}")
	public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") String id) {
		try {
			articleRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/articles")
	public ResponseEntity<HttpStatus> deleteAllArticles() {
		try {
			articleRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from Article";
	}

}
